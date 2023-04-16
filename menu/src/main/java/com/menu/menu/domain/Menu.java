package com.menu.menu.domain;

import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 메뉴
 */
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // 메뉴 타이틀

    private String link; // 메뉴 링크

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> subMenus; // 하위 메뉴 리스트

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private Menu parent; // 상위 메뉴

    public Menu() {
    }

    @Builder
    public Menu(final String title, final String link) {
        this.title = title;
        this.link = link;
        this.subMenus = new ArrayList<>();
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

    public List<Menu> getSubMenus() {
        return subMenus;
    }

    public Menu getParent() {
        return parent;
    }

    public void update(final Menu menu) {
        this.title = menu.getTitle();
        this.link = menu.getLink();
    }

    public void addSubMenu(final Menu subMenu) {
        subMenus.add(subMenu);
        subMenu.setParent(this);
    }

    public void removeSubMenu(final Menu subMenu) {
        subMenus.remove(subMenu);
        subMenu.setParent(null);
    }

    private void setParent(Menu parent) {
        this.parent = parent;
    }

}
