package com.zhenhui.sparkler;

import com.zhenhui.sparkler.data.model.core.comment.Comment;
import com.zhenhui.sparkler.data.repository.CommentRepository;
import com.zhenhui.sparkler.data.repository.DataException;
import com.zhenhui.sparkler.webapi.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.zhenhui.sparkler.TestHelper.newComment;
import static org.junit.Assert.assertEquals;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository repository;

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void testCreateComment() throws DataException {

        Comment comment = newComment(1L, 9527L, 0L, "哈哈哈哈-1");
        Comment r = repository.createComment(comment);
        assertEquals(1, r.getNumber().intValue());

        comment = newComment(1L, 9527L, 0L, "哈哈哈哈-2");
        Comment r1 = repository.createComment(comment);
        assertEquals(2, r1.getNumber().intValue());

        List<Comment> comments = repository.queryReplyComments(1L, 0, 5, false);
        assertEquals(2, comments.size());
    }

    @Test
    public void testQueryComments() throws DataException {

        Comment comment = newComment(1L, 9521L, 0L, "11111");
        repository.createComment(comment);

        comment = newComment(1L, 9522L, 0L, "22222");
        repository.createComment(comment);

        comment = newComment(1L, 9523L, 0L, "33333");
        repository.createComment(comment);

        comment = newComment(1L, 9524L, 0L, "44444");
        repository.createComment(comment);

        comment = newComment(1L, 9525L, 0L, "55555");
        repository.createComment(comment);


        List<Comment> comments = repository.queryReplyComments(1L, 0, 3, false);
        assertEquals(3, comments.size());


        comments = repository.queryReplyComments(1L, 100, 10, true);
        assertEquals(5, comments.size());

        comments = repository.queryReplyComments(3L, 0, 10, false);
        assertEquals(0, comments.size());
    }

    @Test
    public void testCreateComment_withConcurrency() throws InterruptedException {

//        ForkJoinPool pool = new ForkJoinPool(100);
//
//        final Comment[] comments = new Comment[100];
//        for (long i = 1; i <= comments.length; ++i) {
//            comments[(int) i - 1] = newComment(9L, i, 0L, "common," + i);
//        }
//
//        Runnable[] tasks = new Runnable[comments.length];
//        for (int i = 0; i < tasks.length; ++i) {
//            final int idx = i;
//            tasks[i] = () -> {
//                try {
//                    repository.createComment(comments[idx]);
//                } catch (DataException e) {
//                    e.printStackTrace();
//                }
//            };
//            pool.submit(tasks[i]);
//        }
//
//        pool.shutdown();
//        pool.awaitTermination(3, TimeUnit.MINUTES);
//
//        List<Comment> result = repository.queryReplyComments(9L, 0, 200, false);
//        assertEquals(comments.length, result.size());
    }
}
