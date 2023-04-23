package com.menu.menu.dto;

import com.menu.menu.domain.Menu;
import com.menu.menu.domain.Menus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 메뉴 목록 응답 DTO
 */
public class MenuResponses {

    private Map<String, MenuResponse> menus = new HashMap<>();

    public MenuResponses() {
    }

    private MenuResponses(final Map<String, MenuResponse> menus) {
        this.menus = menus;
    }

    public Map<String, MenuResponse> getMenus() {
        return menus;
    }

    public static MenuResponses of(final Menus menus) {
        return new MenuResponses(menus.fetchAll().values()
                .stream()
                .collect(Collectors.toMap(Menu::getTitleValue, MenuResponse::of)));
    }

    public static MenuResponses of(final List<Menu> menus) {
        return new MenuResponses(menus
                .stream()
                .collect(Collectors.toMap(Menu::getTitleValue, MenuResponse::of)));
    }

    public long size() {
        return menus.size();
    }

}
