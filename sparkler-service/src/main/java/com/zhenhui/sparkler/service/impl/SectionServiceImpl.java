package com.zhenhui.sparkler.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhenhui.sparkler.api.domain.SectionDto;
import com.zhenhui.sparkler.api.service.SectionService;
import com.zhenhui.sparkler.data.model.core.post.Section;
import com.zhenhui.sparkler.data.repository.SectionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Service(version = "1.0.0")
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public List<SectionDto> getCategorySections(long categoryId) {
        return sectionRepository.queryAll(categoryId)
                .stream()
                .map(e -> {
                    SectionDto section = new SectionDto();
                    BeanUtils.copyProperties(e, section);
                    return section;
                })
                .collect(Collectors.toList());
    }

    @Override
    public SectionDto createSection(SectionDto section) {

        Section sec = new Section();
        BeanUtils.copyProperties(section, sec);

        sec = sectionRepository.createSection(sec);

        BeanUtils.copyProperties(sec, section);

        return section;
    }

    @Override
    public boolean updateSection(long sectionId, String title, String description) {
        return sectionRepository.updateSection(sectionId, title, description);
    }

    @Override
    public boolean removeSection(long sectionId) {
        return sectionRepository.updateStatus(sectionId, 0);
    }

    @Override
    public boolean recoverSection(long sectionId) {
        return sectionRepository.updateStatus(sectionId, 1);
    }
}


