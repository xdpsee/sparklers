package com.zhenhui.sparkler.data.misc;

import com.zhenhui.sparkler.data.model.mapper.SequenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class DbSequenceGenerator implements SequenceGenerator {
    @Autowired
    private SequenceDAO sequenceDAO;

    private final Lock lock = new ReentrantLock();

    private volatile SequenceRange range;


    @Override
    public long next(char identifier) {

        if (range == null) {
            lock.lock();
            try {
                if (range == null) {
                    range = sequenceDAO.nextRange(identifier);
                }
            } finally {
                lock.unlock();
            }
        }

        long value = range.getAndIncrement();
        if (value < 0) {
            lock.lock();
            try {
                while (true) {
                    if (range.isOver()) {
                        range = sequenceDAO.nextRange(identifier);
                    }

                    value = range.getAndIncrement();
                    if (value < 0) {
                        continue;
                    }

                    break;
                }
            } finally {
                lock.unlock();
            }
        }

        return value;
    }

    @Component
    public static class SequenceDAO {

        @Resource
        private SequenceMapper sequenceMapper;

        public SequenceRange nextRange(char identifier) {

            final Sequence sequence = new Sequence(identifier);
            sequenceMapper.upsert(sequence);

            long min = (sequence.getId() - 1) * 1000 + 1;
            long max = sequence.getId() * 1000;

            return new SequenceRange(min, max);
        }

//        @Autowired
//        private DataSource dataSource;
//        public SequenceRange nextRange(char identifier) {
//
//            Connection connection = null;
//            PreparedStatement statement = null;
//            try {
//                connection = dataSource.getConnection();
//                connection.setAutoCommit(false);
//                statement = connection.prepareStatement("REPLACE INTO sequences (`name`) VALUES (?);", PreparedStatement.RETURN_GENERATED_KEYS);
//                statement.setString(1, String.valueOf(identifier));
//                statement.executeUpdate();
//
//                ResultSet rs = statement.getGeneratedKeys();
//                if (rs.next()) {
//                    long value = rs.getLong(1);
//
//                    long min = (value - 1) * 1000 + 1;
//                    long max = value * 1000;
//                    connection.commit();
//                    return new SequenceRange(min, max);
//                }
//
//                throw new RuntimeException("can't generate sequence");
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            } finally {
//                if (statement != null) {
//                    try {
//                        statement.close();
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                if (connection != null) {
//                    try {
//                        connection.close();
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
    }

}


