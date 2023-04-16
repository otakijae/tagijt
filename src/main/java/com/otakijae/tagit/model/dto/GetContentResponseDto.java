package com.otakijae.tagit.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.otakijae.tagit.constants.ResponseCodes;
import com.otakijae.tagit.model.vo.ContentVo;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetContentResponseDto {
    private ContentVo content;
    private int code = ResponseCodes.SUCCESS.value();
    private String message;
}
