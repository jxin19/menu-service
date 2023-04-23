package com.menu.menu.dto;

import com.menu.banner.dto.BannerResponse;
import com.menu.menu.domain.Menu;
import lombok.Builder;

/**
 * 메뉴 응답 DTO
 */
public class MenuResponse {

    private Long id;
    private String title; // 메뉴 타이틀
    private String link; // 메뉴 링크
    private BannerResponse banner; // 배너

    private MenuResponse() {
    }

    @Builder
    private MenuResponse(final Long id,
                         final String title,
                         final String link,
                         final BannerResponse banner) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.banner = banner;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public BannerResponse getBanner() {
        return banner;
    }

    public static MenuResponse of(final Menu menu) {
        return MenuResponse.builder()
                .id(menu.getId())
                .title(menu.getTitleValue())
                .link(menu.getLinkValue())
                .banner(menu.getBanner() == null ? BannerResponse.empty() : BannerResponse.of(menu.getBanner()))
                .build();
    }

}
