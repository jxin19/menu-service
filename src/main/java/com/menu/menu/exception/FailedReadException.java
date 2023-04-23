package com.menu.menu.exception;

public class FailedReadException extends RuntimeException {

    final static String DEFAULT_MESSAGE = "조회를 실패했습니다.";

    public FailedReadException() {
        super(DEFAULT_MESSAGE);
    }

    public FailedReadException(final String message) {
        super(String.format("%s - %s", DEFAULT_MESSAGE, message));
    }

}
