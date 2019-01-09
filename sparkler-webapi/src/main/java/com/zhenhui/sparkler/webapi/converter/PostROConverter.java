package com.zhenhui.sparkler.webapi.converter;

import com.zhenhui.sparkler.data.model.core.post.Post;
import com.zhenhui.sparkler.data.model.core.post.Type;
import com.zhenhui.sparkler.webapi.model.request.post.PostRO;
import org.mapstruct.*;

import java.util.Date;

@Mapper(componentModel = "spring")
public interface PostROConverter {

    @Mappings(
            @Mapping(target = "type", ignore = true)
    )
    Post to(PostRO source);

    @AfterMapping
    default void after(PostRO source, @MappingTarget Post post) {
        post.setType(Type.valueOf(source.getType()));
        post.setCreateAt(new Date());
        post.setUpdateAt(new Date());
        post.setStatus(1);
    }
}





