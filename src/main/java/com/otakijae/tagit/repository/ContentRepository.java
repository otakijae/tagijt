package com.otakijae.tagit.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.otakijae.tagit.model.vo.ContentVo;

@Mapper
public interface ContentRepository {
    List<ContentVo> getContentList();
    ContentVo getContentById(Long id);
    void insertContent(ContentVo contentVo);
    int updateContent(ContentVo contentVo);
    int deleteContent(Long id);
}
