package com.example.postapp.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.example.postapp.domain.Attachment;
import com.example.postapp.domain.Post;
import com.example.postapp.dto.PageRequestDto;

import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.reset;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.print.attribute.standard.PageRanges;

@Slf4j
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostrepositoryTest {

    // DI : 의존성 주입
    @Autowired
    private Postrepository postrepository;

    @Test
    public void test() {

        assertNotNull(postrepository);
    }

    @Test
    @Rollback(false)
    public void testSave() {

        // given
        // when

        Post post = new Post();
        post.setTitle("title1");
        post.setContents("contents1");
        post.setWriter("writer1");
        post.setRegDate(LocalDateTime.now());

        Post savedPost = postrepository.save(post);

        log.info("savedPost id : {}", savedPost.getId());

        // then

        // assertThrows(NoSuchElementException.class, () -> {

        // Optional<Post> result =
        // postrepository.findById(savedPost.getId().intValue());

        // result.orElseThrow();

        // });

        assertDoesNotThrow(() -> {

            Optional<Post> result = postrepository.findById(savedPost.getId().intValue());
            result.orElseThrow();

        });
    }

    // 게시글 상세조회
    @Test
    public void testFindById() {
        // given
        Long id = 2L;

        // when

        // then
        assertDoesNotThrow(() -> {

            Optional<Post> result = postrepository.findById(id.intValue());

            Post post = result.orElseThrow();

            log.info("post : {}", post);

        });

    }

}
