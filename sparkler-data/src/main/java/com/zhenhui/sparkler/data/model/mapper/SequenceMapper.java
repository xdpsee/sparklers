package com.zhenhui.sparkler.data.model.mapper;

import com.zhenhui.sparkler.data.misc.Sequence;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SequenceMapper {

    Long upsert(Sequence sequence);

}
