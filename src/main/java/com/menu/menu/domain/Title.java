package com.menu.menu.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor
@Embeddable
public class Title {

    final static int MIN_NAME_LENGTH = 2;
    final static int MAX_NAME_LENGTH = 32;
    final static String LENGTH_ERROR_MESSAGE = "메뉴명은 2-32글자 이내로 입력해 주세요.";

    @Column(nullable = false, length = 32)
    private String name;

    public Title(final String name) {
        validate(name);
        this.name = name;
    }

    private void validate(final String name) {
        if (name == null || name.equals("") || name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(LENGTH_ERROR_MESSAGE);
        }
    }

    public String getValue() {
        return name;
    }

}
