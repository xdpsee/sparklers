package com.zhenhui.sparkler;

import com.zhenhui.sparkler.data.model.core.comment.Comment;
import com.zhenhui.sparkler.api.domain.common.misc.Mixed;
import com.zhenhui.sparkler.api.domain.common.misc.content.Text;
import com.zhenhui.sparkler.data.model.core.reply.Reply;

import java.util.Date;

public class TestHelper {

    public static Comment newComment(long replyId, long creator, long refId, String content) {

        Comment comment = new Comment();
        comment.setReplyId(replyId);
        comment.setRefId(refId);
        comment.setCreateAt(new Date());
        comment.setCreatorId(creator);
        comment.setNumber(0);

        Mixed mixture = new Mixed();
        Text text = new Text();
        text.setValue(content);
        mixture.getItems().add(text);
        comment.setContent(mixture);

        comment.setStatus(1);

        return comment;
    }

    public static Reply newReply(long postId, long createId, String content) {

        Reply reply = new Reply();
        reply.setPostId(postId);
        reply.setCreatorId(createId);
        reply.setCreateAt(new Date());

        Mixed mixture = new Mixed();
        Text text = new Text();
        text.setValue(content);
        mixture.getItems().add(text);
        reply.setContent(mixture);

        reply.setNumber(0);
        reply.setStatus(1);

        return reply;
    }

}
