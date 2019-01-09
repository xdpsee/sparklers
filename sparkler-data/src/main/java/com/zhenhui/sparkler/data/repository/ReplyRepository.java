package com.zhenhui.sparkler.data.repository;

import com.zhenhui.sparkler.data.model.core.reply.Reply;

import java.util.List;

public interface ReplyRepository {

    Reply createReply(Reply reply) throws DataException;

    List<Reply> queryReplies(long postId, int startNo, int count, boolean asc);


}
