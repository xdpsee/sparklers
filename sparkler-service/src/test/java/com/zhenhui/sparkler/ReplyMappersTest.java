package com.zhenhui.sparkler;

import com.zhenhui.sparkler.api.domain.common.misc.Mixed;
import com.zhenhui.sparkler.api.domain.common.misc.content.Text;
import com.zhenhui.sparkler.data.model.core.reply.Reply;
import com.zhenhui.sparkler.data.model.mapper.ReplyMapper;
import com.zhenhui.sparkler.data.model.mapper.ReplyStatsMapper;
import com.zhenhui.sparkler.service.bootstrap.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ReplyMappersTest {

    @Resource
    private ReplyMapper replyMapper;
    @Resource
    private ReplyStatsMapper statsMapper;

    @Test
    public void testSave_andSelectById() {

        Reply reply = newReply(1L, 9527L, 1, "很棒!");

        replyMapper.insert(reply);

        Reply r = replyMapper.selectById(reply.getId());
        assertEquals(reply, r);
    }

    @Test
    public void testSelectBySeq() {

        Reply reply = newReply(1L, 1L, 1,"不错不错!");
        replyMapper.insert(reply);

        reply = newReply(1L, 1L, 2,"很棒很棒!");
        replyMapper.insert(reply);

        List<Reply> replies = replyMapper.selectBySeq(1L, 0, 10, true);
        assertTrue(!replies.isEmpty());

        replies = replyMapper.selectBySeq(1L, 10, 10, false);
        assertEquals(2, replies.size());
    }

    private Reply newReply(long postId, long creator, int number, String content) {

        Reply reply = new Reply();
        reply.setPostId(postId);
        reply.setCreateAt(new Date());
        reply.setCreatorId(creator);

        Mixed mixture = new Mixed();
        Text text = new Text();
        text.setValue(content);
        mixture.getItems().add(text);
        reply.setContent(mixture);

        reply.setNumber(number);
        reply.setStatus(1);

        return reply;
    }

}
