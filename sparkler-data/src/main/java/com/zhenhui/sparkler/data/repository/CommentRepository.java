package com.zhenhui.sparkler.data.repository;

import com.zhenhui.sparkler.data.model.core.comment.Comment;

import java.util.List;

public interface CommentRepository {

    Comment createComment(Comment comment) throws DataException;

    List<Comment> queryReplyComments(long replyId, int startNouence, int count, boolean desc);

    List<Comment> queryCommentReplies(long commentId, int startNouence, int count, boolean desc);
}


