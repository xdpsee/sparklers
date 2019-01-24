package com.zhenhui.sparkler.service.converter;

import com.zhenhui.sparkler.api.domain.PostDto;
import com.zhenhui.sparkler.data.model.core.post.Post;
import com.zhenhui.sparkler.data.model.core.post.Type;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class PostConverter {

    @Mappings({
            @Mapping(target = "type", ignore = true)
    })
    public abstract PostDto to(Post post);

    @Mappings({
            @Mapping(target = "type", ignore = true)
    })
    public abstract Post from(PostDto post);

    @AfterMapping
    protected void after(Post source, @MappingTarget PostDto target) {

        target.setType(source.getType().code);

    }

    @AfterMapping
    protected void after(PostDto source, @MappingTarget Post target) {

        target.setType(Type.valueOf(source.getType()));

    }
}
