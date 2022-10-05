package com.sparta.week03.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemoRequestDto {
    private String username;
    private String contents;
    private String title;
    private String password;
}
