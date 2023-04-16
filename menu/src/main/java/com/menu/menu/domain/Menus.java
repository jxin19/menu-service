package com.menu.menu.domain;

import com.menu.menu.exception.FailedSavedException;

import java.util.Map;
import java.util.Optional;

/**
 * 메뉴 목록
 */
public class Menus {

    private Map<String, Menu> menus;

    public Menus() {
    }

    public Menus(final Map<String, Menu> menus) {
        this.menus = menus;
    }

    public void update(final String oldTitle, final Menu menu) throws FailedSavedException {
        final Optional<Menu> oldMenu = this.fetchByTitle(oldTitle);

        if (oldMenu.isPresent()) {
            this.menus.remove(oldTitle);
            this.add(menu);
        } else {
            throw new FailedSavedException(oldTitle);
        }
    }

    public void add(final Menu menu) {
        this.menus.put(menu.getTitle(), menu);
    }

    public void remove(final String title) {
        this.menus.remove(title);
    }

    public Optional<Menu> fetchByTitle(final String title) {
        final Menu menu = this.menus.get(title);
        return menu == null ? Optional.empty() : Optional.of(menu);
    }

    public Map<String, Menu> fetchAll() {
        return menus;
    }

}
