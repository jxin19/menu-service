package com.menu.menu.exception;

public class FailedRemovedException extends RuntimeException {

    final static String DEFAULT_MESSAGE = "삭제를 실패했습니다.";

    public FailedRemovedException() {
        super(DEFAULT_MESSAGE);
    }

    public FailedRemovedException(final String message) {
        super(String.format("%s - %s", DEFAULT_MESSAGE, message));
    }

}
