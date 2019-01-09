package com.zhenhui.sparkler;

import com.zhenhui.sparkler.data.misc.SequenceGenerator;
import com.zhenhui.sparkler.webapi.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SequenceGeneratorTest {

    @Autowired
    private SequenceGenerator sequenceGenerator;

    @Test
    public void testSimple() throws Exception {

        System.out.println(sequenceGenerator.next('a'));

    }

    @Test
    public void testNext() throws Exception {

        TreeSet<Long> numbers = new TreeSet<>();
        for (int i = 1; i <= 100000; ++i) {
            numbers.add(sequenceGenerator.next('a'));
        }

        assertEquals(100000, numbers.size());
        assertEquals(99999, numbers.last() - numbers.first());
    }

    @Test
    public void testNext_withConcurrency() throws Exception {

        List<Long> list = Collections.synchronizedList(new ArrayList());
        Set<Long> numbers = Collections.synchronizedSet(new HashSet<>());

        ForkJoinPool pool = new ForkJoinPool(10);

        for (int i = 0; i < 10; ++i) {
            pool.submit(() -> {
                for (int j = 0; j < 1000; ++j) {
                    try {
                        Long v = sequenceGenerator.next('$');
                        numbers.add(v);
                        list.add(v);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);

        assertEquals(10 * 1000, numbers.size());
    }
}


