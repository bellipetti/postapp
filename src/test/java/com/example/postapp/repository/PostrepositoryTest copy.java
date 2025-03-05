// package com.example.postapp.repository;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.data.domain.Page;
// import org.springframework.test.annotation.Rollback;
// import org.springframework.transaction.annotation.Transactional;

// import com.example.postapp.domain.Attachment;
// import com.example.postapp.domain.Post;
// import com.example.postapp.dto.PageRequestDto;

// import lombok.extern.slf4j.Slf4j;

// import static org.junit.jupiter.api.Assertions.*;

// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.Optional;

// import javax.print.attribute.standard.PageRanges;

// @Slf4j
// @SpringBootTest
// @Transactional
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// public class PostrepositoryTest {

//     // DI : 의존성 주입
//     @Autowired
//     private Postrepository postrepository;

//     @Test
//     public void test() {

//         assertNotNull(postrepository);
//     }

//     // @BeforeEach
//     @Test
//     @Rollback(false)
//     public void test10() {

//     // public void addFiles(Attachment attachment) {
//     //     files.add(attachment);

//     Post post = new Post();
//             post.changeTitle("title1");
//             post.changeContents("contents1");
//             post.changeWriter("writer1");
//             post.changeRegDate(LocalDateTime.now());
//             post.addFiles(new Attachment("file1.txt", "uploads", 50L));
//             post.addFiles(new Attachment("bing.txt", "uploads", 100L));


//             postrepository.save(post);
    
    
//     Post post1 = new Post();
//             post1.changeTitle("title2");
//             post1.changeContents("contents2");
//             post1.changeWriter("writer2");
//             post1.changeRegDate(LocalDateTime.now());
//             post1.addFiles(new Attachment("file2.txt", "uploads", 5332L));
//             post1.addFiles(new Attachment("bing2.txt", "uploads", 1022L));
    

//             postrepository.save(post1); 
            
//     Post post2 = new Post();
//             post2.changeTitle("title3");
//             post2.changeContents("contents3");
//             post2.changeWriter("writer3");
//             post2.changeRegDate(LocalDateTime.now());
//             post2.addFiles(new Attachment("file3.txt", "uploads", 32L));
//             post2.addFiles(new Attachment("bing3.txt", "uploads", 322L));
    

//             postrepository.save(post2); 
            
//     }

//     @Test
//     @Rollback(false)
//     public void testSave() {
//         // given
//         for (int i = 1; i <= 30; i++) {

//             Post post = new Post();
//             post.changeTitle("title" + i);
//             post.changeContents("contents" + i);
//             post.changeWriter("writer" + i);
//             post.changeRegDate(LocalDateTime.now());

//             postrepository.save(post);

//         }

//         // // when
//         // postrepository.save(post);

//         // // then
//         // assertTrue(post.getId() > 0);

//         // log.info("postId : {}", post.getId());

//     }
// }
// //     @Test
// //     public void testFindById() {

// //         // given
// //         int id = 4;

// //         // when
// //         Optional<Post> result = postrepository.findById(id);

// //         Post post = result.orElseThrow();

// //         log.info("id : {}, title : {}", post.getId(), post.getTitle());

// //         // assertThrows(IllegalArgumentException.class, () -> {
// //         // Post post = result.orElseThrow(() -> {

// //         // throw new IllegalArgumentException("게시글 정보가 존재하지 않습니다.");

// //         // });

// //         // log.info("id : {}, title : {}", post.getId(), post.getTitle());

// //         // });

// //         // then

// //     }

// //     @Test
// //     public void testFindAll() {
// //         // given

// //         // when
// //         List<Post> postes = postrepository.findAll();

// //         // then
// //         assertTrue(postes.size() > 0);

// //         postes.forEach(post -> {

// //             log.info("id : {}, title : {}", post.getId(), post.getTitle());
// //         });
// //     }

// //     @Test
// //     @Rollback(false)
// //     public void testUpdate() {
// //         // given
// //         int id = 2;

// //         Optional<Post> result = postrepository.findById(id);

// //         Post post = result.orElseThrow();

// //         // when
// //         post.changeWriter("writer수정");
// //         post.changeTitle("title수정");
// //         post.changeContents("contents수정");
// //         post.changeRegDate(LocalDateTime.now());

// //         // then
// //         assertEquals(post.getTitle(), "title수정");

// //     }

// //     @Test
// //     @Rollback(false)
// //     public void testDelete() {
// //         // given
// //         int id = 4;

// //         Optional<Post> result = postrepository.findById(id);
// //         Post post = result.orElseThrow();

// //         // when
// //         postrepository.delete(post);

// //         // then
// //         // assertThrows(IllegalArgumentException.class, () -> {

// //         // Optional<Post> result1 = postrepository.findById(id);
// //         // result1.orElseThrow(() -> {
// //         // throw new IllegalArgumentException("게시글 정보가 존재하지 않습니다.");

// //         // });

// //         // });

// //     }

// //     @Test
// //     @Rollback(false)
// //     public void testFindAllByTitle() {
// //         // given
// //         String titleStr = "title1";

// //         // when
// //         List<Post> posts = postrepository.findAllByTitle(titleStr);

// //         // then
// //         assertTrue(posts.size() > 0);

// //         posts.forEach(post -> {
// //             log.info("id: {}, title: {}", post.getId(), post.getTitle());
// //         });

// //     }

// //     @Test
// //     @Rollback(false)
// //     public void testGetTotalCount() {

// //         long count = postrepository.getTotalCount();

// //         log.info("count : {}", count);

// //     }

// //     @Test
// //     @Rollback(false)
// //     public void testPaging() {
// //         // given
// //         PageRequestDto pageRequestDto = PageRequestDto.builder()
// //                 .page(2)
// //                 .size(10)
// //                 .build();

// //         log.info("page : {}, size : {}", pageRequestDto.getPage(), pageRequestDto.getSize());

// //         // when
// //         List<Post> posts = postrepository.paging(pageRequestDto);

// //         // then
// //         assertEquals(posts.size(), 10);

// //         posts.forEach(post -> {
// //             log.info("id : {}. title : {}", post.getId(), post.getTitle());
// //         });

// //     }

// //     @Test
// //     public void testFindAlls() {
// //         //given



// //         //when
// //         List<Post> posts = postrepository.findAlls();


// //         //then 
// //         posts.forEach(post -> {

// //             log.info("id : {}, title: {}, filename : {}", post.getId(), post.getTitle(), post.getFiles().get(0).getFilename());

// //         });

// //     }

// //     @Test
// //     public void testgetFileCount() {
// //         //given
// //         long id = 1;


// //         //when
// //         long count = postrepository.getFileCount(id);


// //         //then
// //         assertTrue(count > 0);

// //         log.info("count : {}", count);
// //     }


// //     @Test
// //     public void testfindPostAll() {
// //         //given
        


// //         //when
// //         List<Object[]> posts = postrepository.findPostAll();
        
        
// //         //then
// //         for (Object[] post : posts) {

// //             log.info("id : {}, title : {}, findCount : {}", post[0], post[1], post[2]);

// //         }

// //     }


// // }
