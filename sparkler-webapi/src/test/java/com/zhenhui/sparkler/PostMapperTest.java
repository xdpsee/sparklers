package com.zhenhui.sparkler;

import com.zhenhui.sparkler.data.model.core.common.misc.Mixed;
import com.zhenhui.sparkler.data.model.core.common.misc.content.Image;
import com.zhenhui.sparkler.data.model.core.common.misc.content.Text;
import com.zhenhui.sparkler.data.model.core.post.Content;
import com.zhenhui.sparkler.data.model.core.post.Post;
import com.zhenhui.sparkler.data.model.core.post.Type;
import com.zhenhui.sparkler.data.model.mapper.PostMapper;
import com.zhenhui.sparkler.data.utils.JsonUtils;
import com.zhenhui.sparkler.webapi.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PostMapperTest {

    @Resource
    private PostMapper postMapper;

    @Test
    public void testSave() {

        Post post = new Post();
        post.setId(1L);
        post.setCreateAt(new Date());
        post.setUpdateAt(new Date());
        post.setCreatorId(9527L);
        post.setTitle("this a post title");
        post.setType(Type.ARTICLE);

        Content content = new Content();

        Mixed mixture = new Mixed();
        List<Mixed.Content> composites = new ArrayList<>();

        Text text = new Text();
        text.setValue("一句话、一辈子。");
        composites.add(text);

        Image image = new Image();
        image.setUrl("http://i0.hdslb.com/bfs/archive/ec1cf8d6758d34cd186fbccdd1a1599a1d59eef4.png");
        image.setWidth(1718);
        image.setHeight(1072);
        composites.add(image);

        mixture.setItems(composites);
        content.setMixed(mixture);

        post.setContent(content);

        postMapper.insert(post);
    }

    @Test
    public void testFindById() {

        Post post = new Post();
        post.setId(1L);
        post.setCreateAt(new Date());
        post.setUpdateAt(new Date());
        post.setCreatorId(9527L);
        post.setTitle("this a post title");
        post.setType(Type.ARTICLE);

        Content content = new Content();

        Mixed mixture = new Mixed();
        List<Mixed.Content> composites = new ArrayList<>();

        Text text = new Text();
        text.setValue("一句话、一辈子。");
        composites.add(text);

        mixture.setItems(composites);

        content.setMixed(mixture);
        post.setContent(content);
        post.setStatus(1);

        postMapper.insert(post);

        Post r = postMapper.selectById(post.getId());
        assertEquals(post, r);

        System.out.println(JsonUtils.toJsonString(r));
    }

}
