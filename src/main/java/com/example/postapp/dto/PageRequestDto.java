package com.example.postapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PageRequestDto {


    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

}
