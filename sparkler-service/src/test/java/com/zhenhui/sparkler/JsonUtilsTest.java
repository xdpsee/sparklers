package com.zhenhui.sparkler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhenhui.sparkler.api.domain.common.misc.Mixed;
import com.zhenhui.sparkler.api.domain.common.misc.content.Image;
import com.zhenhui.sparkler.api.domain.common.misc.content.Text;
import com.zhenhui.sparkler.api.domain.common.Content;
import com.zhenhui.sparkler.data.model.core.post.Post;
import com.zhenhui.sparkler.data.model.core.post.Type;
import com.zhenhui.sparkler.data.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class JsonUtilsTest {

    @Test
    public void testJsonUtils() {

        Post post = new Post();
        post.setId(1L);
        post.setCreateAt(new Date());
        post.setUpdateAt(new Date());
        post.setCreatorId(9527L);
        post.setTitle("this a post title");
        post.setType(Type.ARTICLE);

        Content content = new Content();

        Mixed mixed = new Mixed();
        List<Mixed.Content> items = new ArrayList<>();

        Text text = new Text();
        text.setValue("一句话、一辈子。");
        items.add(text);

        Image image = new Image();
        image.setUrl("http://i0.hdslb.com/bfs/archive/ec1cf8d6758d34cd186fbccdd1a1599a1d59eef4.png");
        image.setWidth(1718);
        image.setHeight(1072);
        items.add(image);

        mixed.setItems(items);
        content.setMixed(mixed);

        post.setContent(content);

        String json = JsonUtils.toJsonString(post);

        Post r = JsonUtils.fromJsonString(json, new TypeReference<Post>() {
        });

        assertEquals(post, r);
    }

}
