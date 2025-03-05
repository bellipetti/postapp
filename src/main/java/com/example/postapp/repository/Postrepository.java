package com.example.postapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.postapp.domain.Post;

@Repository
public interface Postrepository extends JpaRepository<Post, Integer>, CustomPostRepository {

    // JPQL
    // @Query("SELECT p FROM Post AS p WHERE p.title LIKE %:title% ")
    // List<Post> findAllByTitle(@Param("title") String titleStr);

    // @Query("SELECT COUNT(p) FROM Post AS p")
    // int getTotalCount();

    @Query("SELECT p FROM Post p JOIN p.files f WHERE index(f) = 0")
    List<Post> findAlls();

    @Query("SELECT SIZE(p.files) FROM Post p WHERE p.id = :id")
    int getFileCount(@Param(value = "id") long id);

    

}
