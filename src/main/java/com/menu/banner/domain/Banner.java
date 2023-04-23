package com.menu.banner.domain;

import lombok.Builder;

import javax.persistence.*;

/**
 * 배너
 */
@Embeddable
@Entity
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Image image; // 배너 이미지 링크

    @Embedded
    private Link link; // 배너 클릭 시 이동할 링크

    public Banner() {
    }

    @Builder
    public Banner(final Image image, final Link link) {
        this.image = image;
        this.link = link;
    }

    // 배너 이미지 링크 조회
    public Image getImage() {
        return this.image;
    }

    public String getImageValue() {
        return this.image.getValue();
    }

    // 배너 클릭 시 이동할 링크 조회
    public Link getLink() {
        return this.link;
    }

    public String getLinkValue() {
        return this.link.getValue();
    }

}

