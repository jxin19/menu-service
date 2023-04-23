package com.menu.banner.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
@Embeddable
@Access(AccessType.FIELD)
public class Image {

    final static String FILENAME_REGEX = "^(gif|jpe?g|png|webp)$";
    final static String EMPTY_ERROR_MESSAGE = "상품이미지를 업로드해 주세요.";
    final static String EXTENSION_ERROR_MESSAGE = "상품이미지는 jpg, jpeg, png, gif, webp 파일로 업로드해 주세요.";

    @Column(unique = true, nullable = false)
    private String image;

    public Image(final String image) {
        validateExist(image);
        validateExtension(image);
        this.image = image;
    }

    private void validateExist(final String filename) {
        if (filename == null || filename.equals("")) {
            throw new IllegalArgumentException(EMPTY_ERROR_MESSAGE);
        }
    }

    private void validateExtension(final String filename) {
        Pattern pattern = Pattern.compile(FILENAME_REGEX);
        Matcher matcher = pattern.matcher(getExtension(filename));
        if (!matcher.find()) {
            throw new IllegalArgumentException(String.format("%s - %s", EXTENSION_ERROR_MESSAGE, filename));
        }
    }

    private String getExtension(final String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    }

    public boolean isEmpty() {
        return this.image == null;
    }

    public String getValue() {
        return this.image;
    }

}
