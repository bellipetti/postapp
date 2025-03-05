package com.example.postapp.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//  Value Object : Collection
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {

    private String filename;
    private String filepath;
    private long size;

}
