package com.otakijae.tagit.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.otakijae.tagit.constants.ResponseCodes;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteContentResponseDto {
    private int code = ResponseCodes.SUCCESS.value();
    private String message;
}
