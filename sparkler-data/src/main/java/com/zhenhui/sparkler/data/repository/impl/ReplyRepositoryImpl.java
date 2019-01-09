package com.zhenhui.sparkler.data.repository.impl;

import com.zhenhui.sparkler.data.model.core.reply.Reply;
import com.zhenhui.sparkler.data.model.core.reply.ReplyStats;
import com.zhenhui.sparkler.data.model.mapper.ReplyMapper;
import com.zhenhui.sparkler.data.model.mapper.ReplyStatsMapper;
import com.zhenhui.sparkler.data.repository.DataException;
import com.zhenhui.sparkler.data.repository.ReplyRepository;
import com.zhenhui.sparkler.data.utils.ValidationUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ReplyRepositoryImpl implements ReplyRepository {

    @Resource
    private ReplyMapper replyMapper;
    @Resource
    private ReplyStatsMapper statsMapper;
    @Resource
    private TxSupport txSupport;

    @Override
    public Reply createReply(Reply reply) throws DataException {

        ValidationUtils.validate(reply);

        final ReplyStats stats = new ReplyStats(reply.getPostId());

        try {
            statsMapper.insertOrIgnore(stats);
        } catch (Exception e) {
            throw new DataException(e);
        }

        return txSupport.createReply(reply);
    }

    @Override
    public List<Reply> queryReplies(long postId, int startNo, int count, boolean asc) {
        return replyMapper.selectBySeq(postId, startNo, count, asc);
    }

    @Component
    public static class TxSupport {

        @Resource
        private ReplyMapper replyMapper;
        @Resource
        private ReplyStatsMapper statsMapper;

        @Transactional(rollbackFor = Exception.class, timeout = 1)
        public Reply createReply(Reply reply) throws DataException {
            try {
                ReplyStats stats = statsMapper.selectForUpdate(reply.getPostId());
                statsMapper.updateCount(reply.getPostId(), stats.getVersion());

                // new floor
                reply.setNumber(stats.getCount() + 1);
                replyMapper.insert(reply);

                return reply;

            } catch (Exception e) {
                throw new DataException(e);
            }
        }

    }
}
