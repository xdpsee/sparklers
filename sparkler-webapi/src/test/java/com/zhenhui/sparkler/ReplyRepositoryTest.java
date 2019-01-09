package com.zhenhui.sparkler;

import com.zhenhui.sparkler.data.model.core.reply.Reply;
import com.zhenhui.sparkler.data.repository.DataException;
import com.zhenhui.sparkler.data.repository.ReplyRepository;
import com.zhenhui.sparkler.webapi.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.zhenhui.sparkler.TestHelper.newReply;
import static org.junit.Assert.assertEquals;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ReplyRepositoryTest {

    @Resource
    private ReplyRepository repository;

    @Test
    public void testCreateReply() throws DataException {

        Reply reply = newReply(1L, 8767L, "田地有梨-1");
        repository.createReply(reply);

        reply = newReply(1L, 8797L, "田地有梨-2");
        repository.createReply(reply);

        List<Reply> replies = repository.queryReplies(1L, 0, 3, true);
        assertEquals(2, replies.size());
    }

    @Test
    public void testCreateReply_withConcurrency() throws InterruptedException {

//        ForkJoinPool pool = new ForkJoinPool(100);
//
//        final Reply[] replies = new Reply[100];
//        for (long i = 1; i <= replies.length; ++i) {
//            replies[(int) i - 1] = newReply(9L, i, "common," + i);
//        }
//
//        Runnable[] tasks = new Runnable[replies.length];
//        for (int i = 0; i < tasks.length; ++i) {
//            final int idx = i;
//            tasks[i] = () -> {
//                try {
//                    repository.createReply(replies[idx]);
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
//        List<Reply> result = repository.queryReplies(9L, 0, 200, true);
//        assertEquals(replies.length, result.size());
    }

}
