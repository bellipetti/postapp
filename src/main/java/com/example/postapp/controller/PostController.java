package com.example.postapp.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.postapp.dto.PageRequestDto;
import com.example.postapp.dto.PageResponseDto;
import com.example.postapp.dto.PostDto;
import com.example.postapp.dto.PostSearchCondition;
import com.example.postapp.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class PostController {
    // DI
    private final PostService postService;

    // 게시글 상세조회
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable("postId") Long id) {

        PostDto postDto = postService.retrivePostById(id);

        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    // 게시글 등록
    @PostMapping("/posts")
    public ResponseEntity<Map<String, Long>> postPost(@RequestBody PostDto postDto) {

        Long id = postService.registerPost(postDto);

        log.info("id : {}", id);

        return new ResponseEntity<>(Map.of("id", id), HttpStatus.CREATED);
    }

    // // 페이징 처리
    // @GetMapping("/posts")
    // public ResponseEntity<PageResponseDto<PostDto>> getPosts(PageRequestDto
    // pageRequestDto) {
    // log.info("page : {}, size : {}", pageRequestDto.getPage(),
    // pageRequestDto.getSize());

    // PageResponseDto<PostDto> result = postService.paging(pageRequestDto);

    // return new ResponseEntity<>(result, HttpStatus.OK);
    // }

    // 검색과 페이징 처리
    @GetMapping("/posts")
    public ResponseEntity<PageResponseDto<PostDto>> getPosts(
            @RequestParam(value = "keyfield", required = false) String keyfield,
            @RequestParam(value = "keyword", required = false) String keyword,
            PageRequestDto pageRequestDto) {

        log.info("page : {}, size : {}", pageRequestDto.getPage(), pageRequestDto.getSize());

        PostSearchCondition condition = new PostSearchCondition();

        if (keyfield != null && keyword != null) {
            if (keyfield.equals("title")) {
                condition.setTitle(keyword);
            } else if (keyfield.equals("contents")) {
                condition.setContents(keyword);
            } else if (keyfield.equals("writer")) {
                condition.setWriter(keyword);
            }
        }

        PageResponseDto<PostDto> result = postService.paging(condition, pageRequestDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
