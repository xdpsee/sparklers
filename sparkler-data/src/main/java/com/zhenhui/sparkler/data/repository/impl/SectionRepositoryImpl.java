package com.zhenhui.sparkler.data.repository.impl;

import com.zhenhui.sparkler.data.model.core.post.Section;
import com.zhenhui.sparkler.data.model.mapper.SectionMapper;
import com.zhenhui.sparkler.data.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SectionRepositoryImpl implements SectionRepository {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private SectionMapper sectionMapper;

    @Override
    public Section createSection(Section section) {

        sectionMapper.insert(section);

        return section;
    }

    @Override
    public List<Section> queryAll(long categoryId) {
        return sectionMapper.selectAll(categoryId);
    }

    @Override
    public Section queryById(long sectionId) {
        return sectionMapper.selectById(sectionId);
    }

    @Override
    public boolean updateSection(long sectionId, String title, String description) {
        return sectionMapper.update(sectionId, title, description) > 0;
    }

    @Override
    public boolean updateStatus(long sectionId, Integer status) {
        return sectionMapper.updateStatus(sectionId, status) > 0;
    }
}




