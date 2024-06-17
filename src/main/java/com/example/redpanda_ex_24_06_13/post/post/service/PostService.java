package com.example.redpanda_ex_24_06_13.post.post.service;

import com.example.redpanda_ex_24_06_13.member.member.entity.Member;
import com.example.redpanda_ex_24_06_13.post.post.entity.Author;
import com.example.redpanda_ex_24_06_13.post.post.entity.Post;
import com.example.redpanda_ex_24_06_13.post.post.repository.PostRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    @PersistenceContext
    private final EntityManager entityManager;

    public Post creatPost(Author author, String title, String content) {
        author.increasePostCount();
        return postRepository.save(
                Post.builder()
                        .author(author)
                        .title(title)
                        .content(content)
                        .build()
        );
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
}
