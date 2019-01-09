package com.zhenhui.sparkler.webapi.converter;

import com.zhenhui.demo.uic.api.domain.UserDto;
import com.zhenhui.sparkler.data.model.core.post.Post;
import com.zhenhui.sparkler.webapi.manager.UserManager;
import com.zhenhui.sparkler.webapi.model.response.post.PostVO;
import com.zhenhui.sparkler.webapi.model.response.post.UserVO;
import org.mapstruct.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;


@Mapper(componentModel = "spring")
public abstract class PostVOConverter {

    @Autowired
    private UserManager userManager;

    @Mappings({
            @Mapping(target = "type", source = "type.code"),
            @Mapping(target = "createAt", expression = "java(source.getCreateAt().getTime())")
    })
    public abstract PostVO to(Post source);

    @InheritConfiguration
    public abstract Collection<PostVO> to(Collection<Post> sources);

    @AfterMapping
    void after(Post source, @MappingTarget PostVO target) {
        final UserDto userDto = userManager.getUser(source.getCreatorId());
        target.setUser(convert(userDto));
    }

    @AfterMapping
    void after(Collection<Post> sources, @MappingTarget Collection<PostVO> targets) {
        Set<Long> userIds = sources.stream().map(Post::getCreatorId).collect(Collectors.toSet());

        Map<Long, UserDto> userMap = userManager.getUsers(userIds);

        Map<Long, Long> postUserMap = sources.stream().collect(toMap(Post::getId, Post::getCreatorId));

        targets.forEach(postVO -> {
            Long userId = postUserMap.get(postVO.getId());
            if (userId != null) {
                UserDto userDto = userMap.get(userId);
                postVO.setUser(convert(userDto));
            }
        });
    }

    private UserVO convert(UserDto userDto) {
        if (userDto != null) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(userDto, userVO);
            return userVO;
        }

        return null;
    }
}






