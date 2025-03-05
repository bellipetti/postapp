package com.example.postapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.example.postapp.domain.Post;
import com.example.postapp.dto.PostSearchCondition;

public interface CustomPostRepository {

    List<Post> findAllByTitle(@Param("title") String titleStr);

    List<Object[]> findAllByTitle1(String titleStr);

    long getTotalCount();

    // List<Post> paging(PageRequestDto pageRequestDto);

    Page<Post> paging(PostSearchCondition condition,  Pageable pageable);


    List<Object[]> findPostAll();
    

}
