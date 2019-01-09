package com.zhenhui.sparkler.data.repository.impl;

import com.zhenhui.sparkler.data.model.core.comment.Comment;
import com.zhenhui.sparkler.data.model.core.comment.CommentRelation;
import com.zhenhui.sparkler.data.model.core.comment.CommentStats;
import com.zhenhui.sparkler.data.model.mapper.CommentMapper;
import com.zhenhui.sparkler.data.model.mapper.CommentRelationMapper;
import com.zhenhui.sparkler.data.model.mapper.CommentStatsMapper;
import com.zhenhui.sparkler.data.repository.CommentRepository;
import com.zhenhui.sparkler.data.repository.DataException;
import com.zhenhui.sparkler.data.utils.ValidationUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@Component
public class CommentRepositoryImpl implements CommentRepository {

    @Resource
    private CommentMapper commentMapper;
    @Resource
    private TxSupport txSupport;
    @Resource
    private CommentStatsMapper statsMapper;
    @Resource
    private CommentRelationMapper relationMapper;

    @Override
    public Comment createComment(Comment comment) throws DataException {

        ValidationUtils.validate(comment);

        final CommentStats stats = new CommentStats(comment.getReplyId(), comment.getRefId());

        try {
            statsMapper.insertOrIgnore(stats);
        } catch (Exception e) {
            throw new DataException(e);
        }

        return txSupport.createComment(comment);
    }

    @Override
    public List<Comment> queryReplyComments(long replyId, int startNo, int count, boolean desc) {

        return desc ?
                commentMapper.selectBySeq(replyId, 0L, startNo, count, false) :
                commentMapper.selectBySeq(replyId, 0L, startNo, count, true);

    }

    @Override
    public List<Comment> queryCommentReplies(long commentId, int startNo, int count, boolean desc) {

        final List<Comment> results = new ArrayList<>(count);

        final List<CommentRelation> relations = relationMapper.selectByRefId(commentId, startNo, count, !desc);

        final Map<Long, Comment> commentMap = commentMapper.selectByIds(relations.stream()
                .map(CommentRelation::getCommentId)
                .collect(Collectors.toList())
        ).stream().collect(toMap(Comment::getId, Function.identity()));

        relations.forEach(r -> {
            if (commentMap.containsKey(r.getCommentId())) {
                results.add(commentMap.get(r.getCommentId()));
            }
        });

        return results;
    }

    @Component
    public static class TxSupport {

        @Resource
        private CommentMapper commentMapper;
        @Resource
        private CommentStatsMapper statsMapper;
        @Resource
        private CommentRelationMapper relationMapper;

        @Transactional(rollbackFor = Exception.class, timeout = 2)
        public Comment createComment(Comment comment) throws DataException {
            try {
                CommentStats stats = statsMapper.selectForUpdate(comment.getReplyId(), comment.getRefId());
                statsMapper.updateCount(comment.getReplyId(), stats.getRefId(), stats.getVersion());

                // new floor
                comment.setNumber(stats.getCount() + 1);
                commentMapper.insert(comment);

                if (comment.getRefId() > 0) {
                    CommentRelation relation = new CommentRelation(comment.getRefId(), comment.getId(), comment.getNumber());
                    relationMapper.insertOrIgnore(relation);
                }

                return comment;
            } catch (Exception e) {
                throw new DataException(e);
            }
        }
    }
}

