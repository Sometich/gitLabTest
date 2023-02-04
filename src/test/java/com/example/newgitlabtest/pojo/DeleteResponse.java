package com.example.newgitlabtest.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class DeleteResponse {
    private int code;
    private String type;
    private String message;
}