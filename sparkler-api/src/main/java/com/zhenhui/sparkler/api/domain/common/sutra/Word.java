package com.zhenhui.sparkler.api.domain.common.sutra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Word implements Serializable {

    private static final long serialVersionUID = 898376425672088997L;
    @NotNull
    private String pinyin;
    @NotEmpty
    private List<String> allPinyin = new ArrayList<>();

    private char word;

}

