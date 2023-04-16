package com.otakijae.tagit.controller;

import java.time.LocalDateTime;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.otakijae.tagit.constants.ResponseCodes;
import com.otakijae.tagit.model.dto.CreateContentRequestDto;
import com.otakijae.tagit.model.dto.CreateContentResponseDto;
import com.otakijae.tagit.model.dto.DeleteContentResponseDto;
import com.otakijae.tagit.model.dto.GetContentListResponseDto;
import com.otakijae.tagit.model.dto.GetContentResponseDto;
import com.otakijae.tagit.model.dto.UpdateContentRequestDto;
import com.otakijae.tagit.model.dto.UpdateContentResponseDto;
import com.otakijae.tagit.exception.NoSuchDataException;
import com.otakijae.tagit.service.ContentService;
import com.otakijae.tagit.model.vo.ContentVo;

@Slf4j
@RestController
public class ContentController {

	@Autowired
	private ContentService contentService;

	@GetMapping("/v1/contents")
	public GetContentListResponseDto getContentList() {
		GetContentListResponseDto getContentListResponseDto = new GetContentListResponseDto();
		try {
			getContentListResponseDto.setContentList(contentService.getContentList());
		} catch (NoSuchDataException e) {
			getContentListResponseDto.setCode(ResponseCodes.NO_SUCH_DATA.value());
			getContentListResponseDto.setMessage("No such content exists.");
		} catch (Exception e) {
			log.error("[ContentController getContentList]", e);
			getContentListResponseDto.setCode(ResponseCodes.UNKNOWN.value());
			getContentListResponseDto.setMessage(e.getLocalizedMessage());
		}
		return getContentListResponseDto;
	}

	@GetMapping("/v1/contents/{contentId}")
	public GetContentResponseDto getPost(@PathVariable Long contentId) {
		GetContentResponseDto getContentResponseDto = new GetContentResponseDto();
		try {
			getContentResponseDto.setContent(contentService.getContentById(contentId));
		} catch (NoSuchDataException e) {
			getContentResponseDto.setCode(ResponseCodes.NO_SUCH_DATA.value());
			getContentResponseDto.setMessage("No such post exists.");
		} catch (Exception e) {
			log.error("[ContentController getContent]", e);
			getContentResponseDto.setCode(ResponseCodes.UNKNOWN.value());
			getContentResponseDto.setMessage(e.getLocalizedMessage());
		}
		return getContentResponseDto;
	}

	@PostMapping("/v1/contents")
	public CreateContentResponseDto createPost(@RequestBody CreateContentRequestDto createContentRequestDto) {
		CreateContentResponseDto createContentResponseDto = new CreateContentResponseDto();
		try {
			ContentVo contentVo = new ContentVo(createContentRequestDto.getTitle(), createContentRequestDto.getDescription());
			contentService.createContent(contentVo);
		} catch (DataIntegrityViolationException e) {
			createContentResponseDto.setCode(ResponseCodes.NULL_VALUE.value());
			createContentResponseDto.setMessage("'userId', 'title', 'body' are required.");
		} catch (Exception e) {
			log.error("[ContentController createContent]", e);
			createContentResponseDto.setCode(ResponseCodes.UNKNOWN.value());
			createContentResponseDto.setMessage(e.getLocalizedMessage());
		}
		return createContentResponseDto;
	}

	@PutMapping("/v1/contents/{contentId}")
	public UpdateContentResponseDto updatePost(@PathVariable Long contentId, @RequestBody UpdateContentRequestDto updateContentRequestDto) {
		UpdateContentResponseDto updateContentResponseDto = new UpdateContentResponseDto();
		try {
			ContentVo postVo = new ContentVo(contentId, updateContentRequestDto.getTitle(), updateContentRequestDto.getDescription(), LocalDateTime.now());
			contentService.updateContent(postVo);
		} catch (NoSuchDataException e) {
			updateContentResponseDto.setCode(ResponseCodes.NO_SUCH_DATA.value());
			updateContentResponseDto.setMessage("No such post exists.");
		} catch (Exception e) {
			log.error("[ContentController updateContent]", e);
			updateContentResponseDto.setCode(ResponseCodes.UNKNOWN.value());
			updateContentResponseDto.setMessage(e.getLocalizedMessage());
		}
		return updateContentResponseDto;
	}

	@DeleteMapping("/v1/contents/{contentId}")
	public DeleteContentResponseDto deleteContent(@PathVariable Long contentId) {
		DeleteContentResponseDto deleteContentResponseDto = new DeleteContentResponseDto();
		try {
			contentService.deleteContent(contentId);
		} catch (NoSuchDataException e) {
			deleteContentResponseDto.setCode(ResponseCodes.NO_SUCH_DATA.value());
			deleteContentResponseDto.setMessage("No such content exists.");
		} catch (Exception e) {
			log.error("[ContentController deleteContent]", e);
			deleteContentResponseDto.setCode(ResponseCodes.UNKNOWN.value());
			deleteContentResponseDto.setMessage(e.getLocalizedMessage());
		}
		return deleteContentResponseDto;
	}
}
