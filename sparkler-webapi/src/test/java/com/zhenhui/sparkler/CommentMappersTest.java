package com.zhenhui.sparkler;

import com.zhenhui.sparkler.data.model.core.comment.Comment;
import com.zhenhui.sparkler.data.model.core.comment.CommentRelation;
import com.zhenhui.sparkler.data.model.core.comment.CommentStats;
import com.zhenhui.sparkler.data.model.mapper.CommentMapper;
import com.zhenhui.sparkler.data.model.mapper.CommentRelationMapper;
import com.zhenhui.sparkler.data.model.mapper.CommentStatsMapper;
import com.zhenhui.sparkler.webapi.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.zhenhui.sparkler.TestHelper.newComment;
import static org.junit.Assert.*;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CommentMappersTest {

    @Resource
    private CommentMapper commentMapper;
    @Resource
    private CommentRelationMapper relationMapper;
    @Resource
    private CommentStatsMapper statsMapper;

    @Test
    public void testSave() {

        Comment comment = newComment(1L, 1L, 0L, "好");
        commentMapper.insert(comment);
    }

    @Test
    public void testSave_withParentId() {

        Comment comment = newComment(2L, 1L, 1L, "好");
        commentMapper.insert(comment);


        CommentRelation relation = new CommentRelation(1L, comment.getId(), 1);
        relationMapper.insertOrIgnore(relation);
    }

    @Test
    public void testRelationSelect_withParentId() {
        CommentRelation relation = new CommentRelation(1L, 1L, 1);
        relationMapper.insertOrIgnore(relation);

        List<CommentRelation> r = relationMapper.selectByRefId(1L, 0, 10, true);
        assertTrue(!r.isEmpty());
    }

    @Test
    public void testRelationBatchSelect_withParentId() {
        CommentRelation relation = new CommentRelation(1L, 1L, 1);
        relationMapper.insertOrIgnore(relation);
        relation = new CommentRelation(1L, 2L, 2);
        relationMapper.insertOrIgnore(relation);
        relation = new CommentRelation(1L, 3L, 3);
        relationMapper.insertOrIgnore(relation);

        List<CommentRelation> r = relationMapper.selectByRefId(1L, 0, 10, true);
        assertEquals(3, r.size());

        r = relationMapper.selectByRefId(1L, 10, 10, false);
        assertEquals(3, r.size());
    }

    @Test
    public void testCount() {

        Comment comment = newComment(2L, 1L, 1L, "好");
        commentMapper.insert(comment);

        CommentStats count = new CommentStats();
        count.setReplyId(2L);
        count.setRefId(1L);
        count.setCount(1);
        statsMapper.insertOrIgnore(count);

        comment = newComment(2L, 1L, 1L, "好");
        commentMapper.insert(comment);

        count = statsMapper.selectForUpdate(2L, 1L);
        assertNotNull(count);

        int rows = statsMapper.updateCount(2L, 1L, count.getVersion());
        assertEquals(1, rows);

        count = statsMapper.selectForTest(2L, 1L);
        assertEquals(2, count.getCount());
    }


}
