package com.menu.menu.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
@Embeddable
public class Link {

    final static int MIN_NAME_LENGTH = 6;
    final static int MAX_NAME_LENGTH = 128;
    final static String URL_REGEX = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";
    final static String LENGTH_ERROR_MESSAGE = "사이트 주소는 6-128자 이내로 입력해 주세요.";
    final static String RULE_ERROR_MESSAGE = "올바른 사이트 주소를 입력해 주세요.";

    @Column(nullable = false)
    private String link;


    public Link(final String link) {
        validateLength(link);
        validateUrlRule(link);
        this.link = link;
    }

    private void validateLength(final String link) {
        if (link == null || link.equals("") || link.length() < MIN_NAME_LENGTH || link.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(LENGTH_ERROR_MESSAGE);
        }
    }

    private void validateUrlRule(final String link) {
        Pattern pattern = Pattern.compile(URL_REGEX);
        Matcher matcher = pattern.matcher(link);
        if (!matcher.find()) {
            throw new IllegalArgumentException(RULE_ERROR_MESSAGE);
        }
    }

    public String getValue() {
        return link;
    }

}
