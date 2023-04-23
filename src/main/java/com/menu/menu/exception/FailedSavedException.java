package com.menu.menu.exception;

public class FailedSavedException extends RuntimeException {

    final static String DEFAULT_MESSAGE = "저장을 실패했습니다.";

    public FailedSavedException() {
        super(DEFAULT_MESSAGE);
    }

    public FailedSavedException(final String message) {
        super(String.format("%s - %s", DEFAULT_MESSAGE, message));
    }

}
