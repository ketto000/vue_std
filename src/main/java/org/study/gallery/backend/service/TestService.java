package org.study.gallery.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.gallery.backend.dao.TestMapper;
import org.study.gallery.backend.dto.portfolioDto;

import java.util.List;



public interface TestService{
    public List<portfolioDto> getAllDataList();
}
