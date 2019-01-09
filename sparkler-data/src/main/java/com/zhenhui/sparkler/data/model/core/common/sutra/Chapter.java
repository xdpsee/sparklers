package com.zhenhui.sparkler.data.model.core.common.sutra;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Chapter implements Serializable {

    private static final long serialVersionUID = 8413598758345288706L;

    private String title = "";

    @NotEmpty
    private List<Paragraph> paragraphs = new ArrayList<>();

}
