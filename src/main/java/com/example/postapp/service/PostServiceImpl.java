package com.example.postapp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.postapp.domain.Post;
import com.example.postapp.dto.PageRequestDto;
import com.example.postapp.dto.PageResponseDto;
import com.example.postapp.dto.PostDto;
import com.example.postapp.dto.PostSearchCondition;
import com.example.postapp.repository.Postrepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

    // DI
    private final Postrepository postrepository;

    @Transactional(readOnly = false)
    @Override
    public Long registerPost(PostDto postDto) {

        Post post = dtoToEntity(postDto);

        Post savedPost = postrepository.save(post);

        return savedPost.getId();

    }

    @Override
    public PageResponseDto<PostDto> paging(PostSearchCondition condition, PageRequestDto pageRequestDto) {

        Pageable pageable = PageRequest.of(pageRequestDto.getPage() - 1, pageRequestDto.getSize());

        Page<Post> page = postrepository.paging(condition, pageable);
        List<PostDto> posts = page.get().map(post -> entityToDto(post)).collect(Collectors.toList());

        long totalCount = page.getTotalElements();

        return PageResponseDto.<PostDto>builder()
                .dtoList(posts)
                .pageRequestDto(pageRequestDto)
                .totalCount(totalCount)
                .build();

    }

    // 게시글 상세조회
    @Override
    public PostDto retrivePostById(Long id) {

        Optional<Post> result = postrepository.findById(id.intValue());

        Post post = result.orElseThrow();

        PostDto postDto = entityToDto(post);

        return postDto;
    }

}

// @Override
// public PageResponseDto<PostDto> paging(PageRequestDto pageRequestDto) {

// PageRequest pageable = PageRequest.of(pageRequestDto.getPage() - 1,
// pageRequestDto.getSize(),
// Sort.by("id").descending());

// Page<Post> page = postrepository.findAll(pageable);

// // List<Post> post = page.getContent();

// List<PostDto> posts = page.get().map(post ->
// EntityToDto(post)).collect(Collectors.toList());

// long totalCount = page.getTotalElements();

// return PageResponseDto.<PostDto>builder()
// .dtoList(posts)
// .pageRequestDto(pageRequestDto)
// .totalCount(totalCount)
// .build();

// }
