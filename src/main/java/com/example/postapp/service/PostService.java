package com.example.postapp.service;

import com.example.postapp.domain.Post;
import com.example.postapp.dto.PageRequestDto;
import com.example.postapp.dto.PageResponseDto;
import com.example.postapp.dto.PostDto;
import com.example.postapp.dto.PostSearchCondition;

public interface PostService {

    // 게시글 등록
    Long registerPost(PostDto postDto);

    // 게시글 목록 조회 (검색, 페이징 처리)
    PageResponseDto<PostDto> paging(PostSearchCondition condition, PageRequestDto pageRequestDto);

    // 게시글 상세 조회
    PostDto retrivePostById(Long id);

    // PostDto => Post 엔티티 변환
    default Post dtoToEntity(PostDto dto) {

        return Post.builder()

                .title(dto.getTitle())
                .contents(dto.getContents())
                .writer(dto.getWriter())
                .regDate(dto.getRegDate())
                .build();

    }

    // Post => PostDto 엔티티 변환
    default PostDto entityToDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .contents(post.getContents())
                .writer(post.getWriter())
                .regDate(post.getRegDate())
                .build();

    }

}
