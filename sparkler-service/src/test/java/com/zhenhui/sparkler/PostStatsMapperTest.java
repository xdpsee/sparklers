package com.zhenhui.sparkler;

import com.zhenhui.sparkler.data.model.mapper.PostStatsMapper;
import com.zhenhui.sparkler.service.bootstrap.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PostStatsMapperTest {

    @Resource
    private PostStatsMapper statsMapper;

    @Test
    public void testInsert() {

//        final int count = 10000000;
//
//        for (int i = 0; i < count; ++i) {
//            PostStats stats = new PostStats(RandomUtils.nextInt(1, 8), RandomUtils.nextInt(1000, 1200), 10000L + i, 1862181L + i);
//            statsMapper.insert(stats);
//            System.out.print(".");
//        }

    }

}
