package com.movieland.cinema.exception;

import lombok.Getter;

@Getter
public class EmptyCacheException extends RuntimeException {
    private final String message;

    public EmptyCacheException(String message) {
        super(message);
        this.message = message;
    }
}