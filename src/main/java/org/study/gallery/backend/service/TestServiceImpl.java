package org.study.gallery.backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.gallery.backend.dao.TestMapper;
import org.study.gallery.backend.dto.portfolioDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService{

    @Autowired
    private TestMapper testMapper;

    @Override
    public List<portfolioDto> getAllDataList() {
        return testMapper.getAllDataList();
    }
}
