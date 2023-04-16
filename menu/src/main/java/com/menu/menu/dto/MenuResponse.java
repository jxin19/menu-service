package com.menu.menu.dto;

import com.menu.menu.domain.Menu;
import lombok.Builder;

/**
 * 메뉴 응답 DTO
 */
public class MenuResponse {

    private String title; // 메뉴 타이틀
    private String link; // 메뉴 링크

    private MenuResponse() {
    }

    @Builder
    private MenuResponse(final String title, final String link) {
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public static MenuResponse of(final Menu menu) {
        return MenuResponse.builder()
                .title(menu.getTitle())
                .link(menu.getLink())
                .build();
    }

}
