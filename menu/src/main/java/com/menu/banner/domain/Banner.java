package com.menu.banner.domain;

import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 배너
 */
@Entity
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image; // 배너 이미지 링크

    private String link; // 배너 클릭 시 이동할 링크

    public Banner() {
    }

    @Builder
    public Banner(final String image, final String link) {
        this.image = image;
        this.link = link;
    }

    // 배너 이미지 링크 조회
    public String getImage() {
        return this.image;
    }

    // 배너 클릭 시 이동할 링크 조회
    public String getLink() {
        return this.link;
    }

}

