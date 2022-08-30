package com.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TransformUser {

    private long id;

    private String body;

    private long postId;

    private String username;

    private String updatedAt;

    @Override
    public String toString() {
        return String.format("%d | %s | %d | %s | %s", id, body, postId, username, updatedAt);
    }
}
