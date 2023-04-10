package com.otakijae.tagit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otakijae.tagit.exception.NoSuchDataException;
import com.otakijae.tagit.repository.ContentRepository;
import com.otakijae.tagit.model.vo.ContentVo;

@Service
public class ContentService {
	@Autowired
	private ContentRepository contentRepository;

	public List<ContentVo> getContentList() {
		List<ContentVo> list = contentRepository.getContentList();
		if (list.isEmpty())
			throw new NoSuchDataException("No such data exists.");
		return list;
	}

	public ContentVo getContentById(Long contentId) {
		ContentVo contentVo = contentRepository.getContentById(contentId);
		if (contentVo == null)
			throw new NoSuchDataException("No such data exists.");
		return contentVo;
	}

	public void createContent(ContentVo contentVo) {
		contentRepository.insertContent(contentVo);
	}

	public void updateContent(ContentVo contentVo) {
		int result = contentRepository.updateContent(contentVo);
		if (result == 0)
			throw new NoSuchDataException("No such data exists.");
	}

	public void deleteContent(Long id) {
		int result = contentRepository.deleteContent(id);
		if (result == 0)
			throw new NoSuchDataException("No such data exists.");
	}

}
