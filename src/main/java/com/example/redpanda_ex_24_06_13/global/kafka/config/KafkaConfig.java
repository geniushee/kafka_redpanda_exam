package com.example.redpanda_ex_24_06_13.global.kafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.backoff.FixedBackOff;

@Component
public class KafkaConfig {

    // 컨슈머가 받은 json메세지를 객체로 변환하기 위해서 빈으로 등록할 필요가 있다.
    @Bean
    public RecordMessageConverter converter(){
        return new JsonMessageConverter();
    }

    @Bean
    public CommonErrorHandler errorHandler(KafkaTemplate<Object, Object> template){
        // 에러 핸들러 반환
        // backoff는 에러가 발생했을 때, 인터벌 간격으로 최대 3번 재시도를 한다.
        // DLT(DeadLetterPublish...)를 만들어 두면, 에러 또는 실패한 메세지만 모아서 로그를 남겨둔다.
        return new DefaultErrorHandler(
                new DeadLetterPublishingRecoverer(template), new FixedBackOff(1000L, 3)
        );
    }
}
