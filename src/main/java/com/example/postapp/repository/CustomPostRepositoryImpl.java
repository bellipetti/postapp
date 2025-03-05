package com.example.postapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import com.example.postapp.domain.Post;
import com.example.postapp.domain.QPost;
import com.example.postapp.dto.PageRequestDto;
import com.example.postapp.dto.PostSearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CustomPostRepositoryImpl implements CustomPostRepository {

    @PersistenceContext
    private EntityManager em;

    private final JPAQueryFactory jpaQueryFactory;

    public CustomPostRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    QPost post = QPost.post;

    @Override
    public List<Object[]> findAllByTitle1(String titleStr) {

        String qlString = "SELECT p.id, p.title, p.writer FROM Post AS p WHERE p.title LIKE :title";

        @SuppressWarnings("unchecked")
        List<Object[]> posts = em.createQuery(qlString)
                .setParameter("title", "%" + titleStr + "%")
                .getResultList();

        return posts;
    }

    @Override
    public List<Post> findAllByTitle(String titleStr) {

        String qlString = "SELECT p FROM Post AS p WHERE p.title LIKE :title";

        List<Post> posts = em.createQuery(qlString, Post.class)
                .setParameter("title", "%" + titleStr + "%")
                .getResultList();

        return posts;

    }

    @Override
    public long getTotalCount() {

        String qlString = "SELECT COUNT(p) FROM Post AS p";

        return em.createQuery(qlString, Long.class)
                .getSingleResult().longValue();

    }

    // @Override
    // public List<Post> paging(PageRequestDto pageRequestDto) {

    // String qlString = "SELECT p FROM Post As p ORDER BY p.id DESC";

    // return em.createQuery(qlString, Post.class)
    // .setFirstResult((pageRequestDto.getPage() - 1) * pageRequestDto.getSize())
    // .setMaxResults(pageRequestDto.getSize())
    // .getResultList();

    // }

    // QueryDSL
    @Override
    public Page<Post> paging(PostSearchCondition condition,  Pageable pageable) {

        // 데이터 조회

        List<Post> posts = jpaQueryFactory
                .select(post)
                .from(post)
                .where(writerLike(condition.getWriter()),
                        contentsLike(condition.getContents()),
                        titleLike(condition.getTitle()))
                .orderBy(post.id.desc())
                .offset(pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .fetch();

          // 총 데이터 개수

          long totalCount = jpaQueryFactory
          .select(post)
          .from(post)
          .where(writerLike(condition.getWriter()),
                  contentsLike(condition.getContents()),
                  titleLike(condition.getTitle()))
          .fetchCount();
            



        return PageableExecutionUtils.getPage(posts, pageable, () -> {
            return totalCount;
        });

        // return new PageImpl<>(posts, pageable, totalCount);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findPostAll() {

        String qlString = "SELECT p.id, p.title, size(p.files), f.filename FROM Post p LEFT OUTER JOIN p.files f";

        return em.createQuery(qlString)
                .getResultList();

    }

    private BooleanExpression writerLike(String writer) {

        return writer == null ? null : post.writer.like("%" + writer + "%");

    }

    private BooleanExpression contentsLike(String contents) {

        return contents == null ? null : post.writer.like("%" + contents + "%");

    }

    private BooleanExpression titleLike(String title) {

        return title == null ? null : post.writer.like("%" + title + "%");

    }

}
