package com.menu.menu.domain;

import com.menu.banner.domain.Banner;
import com.menu.menu.exception.FailedRemovedException;
import com.menu.menu.exception.FailedSavedException;
import lombok.Builder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 메뉴
 */
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Title title; // 메뉴 타이틀

    @Embedded
    private Link link; // 메뉴 링크

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> subMenus; // 하위 메뉴 리스트

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private Menu parent; // 상위 메뉴

    @OneToOne(targetEntity = Banner.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "menuBannerId", unique = true)
    private Banner banner; // 배너

    public Menu() {
    }

    @Builder
    public Menu(final Title title, final Link link) {
        this.title = title;
        this.link = link;
        this.subMenus = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public Title getTitle() {
        return title;
    }

    public String getTitleValue() {
        return title.getValue();
    }

    public Link getLink() {
        return link;
    }

    public String getLinkValue() {
        return link.getValue();
    }

    public List<Menu> getSubMenus() {
        return subMenus;
    }

    public Menu getParent() {
        return parent;
    }

    public Banner getBanner() {
        return banner;
    }

    public void update(final Menu menu) {
        this.title = menu.getTitle();
        this.link = menu.getLink();
    }

    public void addSubMenu(final Menu subMenu) {
        this.validateTitleForSave(subMenu);

        subMenus.add(subMenu);
        subMenu.setParent(this);
    }

    private void validateTitleForSave(final Menu subMenu) {
        boolean exist = this.subMenus
                .stream()
                .anyMatch(menu -> menu.getTitleValue().equals(subMenu.getTitleValue()));

        if (exist) {
            throw new FailedSavedException();
        }
    }

    public void removeSubMenu(final Menu subMenu) {
        this.validateTitleForRemove(subMenu);

        subMenus.remove(subMenu);
        subMenu.setParent(null);
    }

    private void validateTitleForRemove(final Menu subMenu) {
        boolean notExist = this.subMenus
                .stream()
                .noneMatch(menu -> menu.getTitleValue().equals(subMenu.getTitleValue()));

        if (notExist) {
            throw new FailedRemovedException();
        }
    }

    public void addBanner(final Banner banner) {
        if (this.parent == null && this.banner == null) {
            this.banner = banner;
        } else {
            throw new FailedSavedException();
        }
    }

    private void setParent(final Menu parent) {
        this.parent = parent;
    }

}
