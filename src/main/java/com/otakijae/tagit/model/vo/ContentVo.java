package com.otakijae.tagit.model.vo;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.otakijae.tagit.validator.UserNoConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContentVo {
    private Long contentId;
    private String title;
    private String description;
    private LocalDateTime createYmdt;
    private LocalDateTime updateYmdt;

    public ContentVo(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public ContentVo(Long contentId, String title, String description, LocalDateTime updateYmdt) {
        this.contentId = contentId;
        this.title = title;
        this.description = description;
        this.updateYmdt = updateYmdt;
    }

    @Getter
    @Setter
    @ToString
    @RequiredArgsConstructor
    public static class User {

        @NonNull
        @NotNull(message = "userNo is null")
        private int userNo;

        @NonNull
        @NotNull(message = "userName is null")
        private String userName;

        @NonNull
        @UserNoConstraint
        private String userId;

        @NonNull
        @NotNull(message = "userPassword is null")
        private String userPassword;

    }
}
