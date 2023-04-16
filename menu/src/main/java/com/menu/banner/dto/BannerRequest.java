package com.menu.banner.dto;

import com.menu.banner.domain.Banner;

/**
 * 배너 요청 DTO
 */
public class BannerRequest {

    private String image; // 배너 이미지 링크
    private String link; // 배너 클릭 시 이동할 링크

    public BannerRequest() {
    }

    public BannerRequest(final String title, final String image, final String link) {
        this.image = image;
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }

    public Banner toEntity() {
        return Banner.builder()
                .image(image)
                .link(link)
                .build();
    }

}
