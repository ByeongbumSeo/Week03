package com.sparta.week03.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemoResponseDto {
    private final Long id;
    private final String title;
    private final String username;
    private final String contents;
    private final LocalDateTime modifiedAt;

    public MemoResponseDto(Memo memo){
        this.id = memo.getId();
        this.title = memo.getTitle();
        this.username = memo.getUsername();
        this.contents = memo.getContents();
        this.modifiedAt = memo.getModifiedAt();
    }
}