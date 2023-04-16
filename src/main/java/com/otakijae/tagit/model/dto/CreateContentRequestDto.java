package com.otakijae.tagit.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.otakijae.tagit.constants.ResponseCodes;

import lombok.Data;

@Data
public class CreateContentRequestDto {
    private String title;
    private String description;
}
