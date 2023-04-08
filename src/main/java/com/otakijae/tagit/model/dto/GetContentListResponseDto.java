package com.otakijae.tagit.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.otakijae.tagit.constants.ResponseCodes;
import com.otakijae.tagit.model.vo.ContentVo;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetContentListResponseDto {
    private List<ContentVo> contentList;
    private int code = ResponseCodes.SUCCESS.value();
    private String message;
}
