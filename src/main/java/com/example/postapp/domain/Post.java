package com.example.postapp.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString(of = { "id", "title", "contents"})
public class Post {

    @Id   // 엔티티의 기본 키(Primary Key)를 나타내는 데 지정/사용
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // PK : AUTO_INCREMENT PRIMARY KEY 
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String contents;

    private String writer;

    @Column(name = "reg_date", nullable = false)
    private LocalDateTime regDate;

    @ElementCollection
    @CollectionTable(name = "attachment", joinColumns = @JoinColumn(name = "post_id"))
    @OrderColumn(name = "order_index")
    private List<Attachment> files = new ArrayList<>();


    @PrePersist
    public void prePersist() {
        this.regDate = this.regDate == null ? LocalDateTime.now() : this.regDate;
    }

    // 비즈니스 메소드  ,업데이트용도로 사용 (수정)

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContents(String contents) {
        this.contents = contents;
    }

    public void changeWriter(String writer) {
        this.writer = writer;
    }

    public void changeRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public void addFiles(Attachment attachment) {
        // TODO Auto-generated method stub
        this.files.add(attachment);
    }

}
