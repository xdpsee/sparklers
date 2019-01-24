package com.zhenhui.sparkler.api.domain.common.sutra;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Paragraph implements Serializable {

    private static final long serialVersionUID = -4368357961494057753L;
    @NotEmpty
    private List<Word> words = new ArrayList<>();

}
