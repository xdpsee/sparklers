package com.zhenhui.sparkler.data.repository;

import com.zhenhui.sparkler.data.model.core.post.Section;

import java.util.List;

public interface SectionRepository {

    Section createSection(Section section);

    List<Section> queryAll(long categoryId);

    Section queryById(long sectionId);

    boolean updateSection(long sectionId, String title, String description);

    boolean updateStatus(long sectionId, Integer status);
}
