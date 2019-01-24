package com.zhenhui.sparkler.api.domain.common.sutra;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Sutra implements Serializable {

    private static final long serialVersionUID = 173111563766294607L;
    @NotNull
    private List<String> alias = new ArrayList<>();
    @NotNull
    private String author;
    @NotNull
    private String translator = "";
    @NotEmpty
    private List<Chapter> chapters = new ArrayList<>();
}


