package com.otakijae.tagit.model.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UpdateContentRequestDto {
    private String title;
    private String description;
}
