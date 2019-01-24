package com.zhenhui.sparkler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhenhui.sparkler.api.domain.PostDto;
import com.zhenhui.sparkler.api.domain.common.Content;
import com.zhenhui.sparkler.api.domain.common.html.Html;
import com.zhenhui.sparkler.api.domain.common.misc.Mixed;
import com.zhenhui.sparkler.api.domain.common.misc.content.Audio;
import com.zhenhui.sparkler.api.domain.common.misc.content.Image;
import com.zhenhui.sparkler.api.domain.common.misc.content.Text;
import com.zhenhui.sparkler.api.domain.common.misc.content.Video;
import com.zhenhui.sparkler.data.model.core.post.Post;
import com.zhenhui.sparkler.data.model.core.post.Type;
import com.zhenhui.sparkler.data.utils.JsonUtils;
import com.zhenhui.sparkler.data.utils.ValidationUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class PostValidTest {

    @Test
    public void testTypeIsArticle_andContentTypeIsHtml() {
        Post post = new Post();
        post.setId(1L);
        post.setCreateAt(new Date());
        post.setUpdateAt(new Date());
        post.setCreatorId(9527L);
        post.setTitle("this a post title");
        post.setType(Type.ARTICLE);

        Content content = new Content();
        Html html = new Html();
        html.setText("<html><body>hello,world!</body></html>");
        content.setHtml(html);
        post.setContent(content);

        Set<ConstraintViolation<Post>> validations = ValidationUtils.valid(post);
        assertEquals(0, validations.size());
    }

    @Test
    public void testTypeIsSutra_andContentTypeIsHtml() {
        Post post = new Post();
        post.setId(1L);
        post.setCreateAt(new Date());
        post.setUpdateAt(new Date());
        post.setCreatorId(9527L);
        post.setTitle("this a post title");
        post.setType(Type.SUTRA);

        Content content = new Content();
        Html html = new Html();
        html.setText("<html><body>hello,world!</body></html>");
        content.setHtml(html);
        post.setContent(content);

        Set<ConstraintViolation<Post>> validations = ValidationUtils.valid(post);
        assertEquals(1, validations.size());
    }

    @Test
    public void testTypeIsPost_andContentTypeIsHtml() {

        Post post = new Post();
        post.setId(1L);
        post.setCreateAt(new Date());
        post.setUpdateAt(new Date());
        post.setCreatorId(9527L);
        post.setTitle("this a post title");
        post.setType(Type.POST);

        Content content = new Content();
        Html html = new Html();
        html.setText("<html><body>hello,world!</body></html>");
        content.setHtml(html);
        post.setContent(content);

        Set<ConstraintViolation<Post>> validations = ValidationUtils.valid(post);
        assertEquals(0, validations.size());

    }

    public static void main(String[] args) {

        PostDto post = new PostDto();
        post.setType(1);
        post.setCreatorId(1L);
        post.setTitle("haha");
        post.setDescription("");

        Content content = new Content();

        Mixed mixed = new Mixed();

        Text text = new Text();
        text.setValue("TEXT TEXT");

        Image image = new Image();
        image.setUrl("http://pic.58pic.com/58pic/13/80/78/35V58PICrWD_1024.jpg");
        image.setWidth(752);
        image.setHeight(472);

        Audio audio = new Audio();
        audio.setUrl("https://leechikit.github.io/resources/article/AudioContext/song/fingfingxia1.mp3");
        audio.setDuration(29);

        Video video = new Video();
        video.setUrl("http://www.w3school.com.cn/i/movie.ogg");
        video.setWidth(320);
        video.setHeight(240);
        video.setDuration(9);

        video.setPreview(new Video.Preview());
        video.getPreview().setUrl("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3299489531,1121575511&fm=15&gp=0.jpg");
        video.getPreview().setWidth(320);
        video.getPreview().setHeight(240);


        mixed.setItems(Arrays.asList(text, image, audio, video));

        content.setMixed(mixed);

        post.setContent(content);

        String json = JsonUtils.toJsonString(post);
        System.out.println(json);
        PostDto ro = JsonUtils.fromJsonString(json, new TypeReference<PostDto>() {
        });
        System.out.println(JsonUtils.toJsonString(ro));

    }

}
