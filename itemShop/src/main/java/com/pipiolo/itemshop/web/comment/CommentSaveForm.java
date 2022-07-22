package com.pipiolo.itemshop.web.comment;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CommentSaveForm {

    @NotBlank
    private String message;
}
