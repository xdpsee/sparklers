package com.zhenhui.sparkler.api.service;

import com.zhenhui.sparkler.api.domain.SectionDto;

import java.util.List;

public interface SectionService {

    List<SectionDto> getCategorySections(long categoryId);

    SectionDto createSection(SectionDto section);

    boolean updateSection(long sectionId, String title, String description);

    boolean removeSection(long sectionId);

    boolean recoverSection(long sectionId);
}

