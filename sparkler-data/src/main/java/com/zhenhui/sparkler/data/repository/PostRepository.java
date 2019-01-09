package com.zhenhui.sparkler.data.repository;

import com.zhenhui.sparkler.data.model.core.post.Category;
import com.zhenhui.sparkler.data.model.core.post.Post;
import com.zhenhui.sparkler.data.model.core.post.Section;

import java.util.List;

public interface PostRepository {

    Post createPost(Post post, Section section, Long number);

    Post queryPost(long postId);

    List<Post> queryPosts(Section section, long startNo, int count);

    List<Post> queryPosts(Category category, long startNo, int count);
}
