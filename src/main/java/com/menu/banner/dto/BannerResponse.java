package com.menu.banner.dto;

import com.menu.banner.domain.Banner;
import lombok.Builder;

/**
 * 배너 응답 DTO
 */
public class BannerResponse {

    private String image; // 배너 이미지 링크
    private String link; // 배너 클릭 시 이동할 링크

    public BannerResponse() {
    }

    @Builder
    public BannerResponse(final String image, final String link) {
        this.image = image;
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }

    public static BannerResponse of(final Banner banner) {
        return BannerResponse.builder()
                .image(banner.getImageValue())
                .link(banner.getLinkValue())
                .build();
    }

    public static BannerResponse empty() {
        return new BannerResponse();
    }

}
