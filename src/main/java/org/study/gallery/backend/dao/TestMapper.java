package org.study.gallery.backend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.study.gallery.backend.dto.portfolioDto;

import java.util.List;

@Repository
@Mapper
public interface TestMapper {
    List<portfolioDto> getAllDataList();
}
