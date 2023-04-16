package com.menu.menu.dto;

import com.menu.menu.domain.Menu;

/**
 * 메뉴 요청 DTO
 */
public class MenuRequest {

    private String title; // 메뉴 타이틀
    private String link; // 메뉴 링크

    private MenuRequest() {
    }

    private MenuRequest(final String title, final String link) {
        this.title = title;
        this.link = link;
    }

    public String getImage() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public Menu toEntity() {
        return Menu.builder()
                .title(title)
                .link(link)
                .build();
    }

}
