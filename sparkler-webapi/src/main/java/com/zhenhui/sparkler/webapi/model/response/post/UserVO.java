package com.zhenhui.sparkler.webapi.model.response.post;

import com.zhenhui.sparkler.webapi.model.response.BaseUserInfo;
import lombok.Data;

@Data
public class UserVO implements BaseUserInfo {

    private Long id;

    private String nickname;

    private String avatar;

}


