package com.example.redpanda_ex_24_06_13.post.post.service;

import com.example.redpanda_ex_24_06_13.global.event.PostCreatedEvent;
import com.example.redpanda_ex_24_06_13.member.member.entity.Member;
import com.example.redpanda_ex_24_06_13.post.post.entity.Author;
import com.example.redpanda_ex_24_06_13.post.post.entity.Post;
import com.example.redpanda_ex_24_06_13.post.post.repository.PostRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    @PersistenceContext
    private final EntityManager entityManager;
    // 이벤트 퍼블리셔를 이용해 스프링에서 이벤트를 생성할 수 있다.
    private final ApplicationEventPublisher publisher;

    public Post creatPost(Author author, String title, String content) {
        author.increasePostCount();

        Post post = postRepository.save(
                Post.builder()
                        .author(author)
                        .title(title)
                        .content(content)
                        .build()
        );

        // 퍼블리셔를 이용해 PostCreatedEvent라는 채팅방에 post라는 새로운 글을 생성한다.
        // 이렇게 생성한 글은 각 이벤트 리스너가 확인하여 이런 저런 기능을 한다.
        // 이벤트 퍼블리셔/리스너를 이용해 Noti모듈과의 종속성을 제거했다.
        publisher.publishEvent(new PostCreatedEvent(this, post));

        return post;
    }

    public long count() {
        return postRepository.count();
    }

    public Author of(Member member) {
        //entityManager의 getReference를 이용해 Lazy상태의 memberId에 입력받은 Author클래스 얻어낸다.
        //DB에서 찾아서 주는 것이 아니라 Lazy 상태 즉, 데이터를 로드하지 않은 상태의 enetity를 만들어서 준 것.
        //만약 데이터를 db에서 검색하게 되면 에러를 발생시키게 될 것이다.(만약 데이터가 없다면, author클래스는 member테이블에 맵핑을 해두어서 괜춘)
        return entityManager.getReference(Author.class, member.getId());
    }

    public Member of(Author author) {
        return entityManager.getReference(Member.class, author.getId());
    }
}
