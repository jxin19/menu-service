package com.menu.menu.application;

import com.menu.banner.domain.Banner;
import com.menu.banner.dto.BannerRequest;
import com.menu.menu.domain.Menu;
import com.menu.menu.domain.MenuRepository;
import com.menu.menu.domain.Title;
import com.menu.menu.dto.MenuRequest;
import com.menu.menu.dto.MenuResponse;
import com.menu.menu.dto.MenuResponses;
import com.menu.menu.exception.FailedReadException;
import com.menu.menu.exception.FailedSavedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 메뉴 서비스
 */
@Transactional
@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(final MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    /**
     * 메뉴 등록
     *
     * @param menuRequest 메뉴 요청 DTO
     * @return MenuResponse
     */
    public MenuResponse addMenu(final MenuRequest menuRequest) {
        final Menu menu = menuRequest.toEntity();
        final Menu saved = menuRepository.save(menu);

        return MenuResponse.of(saved);
    }

    /**
     * 메뉴 수정
     *
     * @param id 변경할 메뉴 ID
     * @param menuRequest 메뉴 요청 DTO
     * @return MenuResponse
     * @throws FailedSavedException
     */
    public MenuResponse updateMenu(final Long id, final MenuRequest menuRequest) throws FailedSavedException {
        final Menu menu = this.findById(id);
        menu.update(menuRequest.toEntity());

        return MenuResponse.of(menu);
    }

    /**
     * 메뉴 삭제
     *
     * @param id 삭제할 메뉴의 타이틀 ID
     */
    public void removeMenu(final Long id) {
        menuRepository.deleteById(id);
    }

    /**
     * 하위메뉴 추가
     *
     * @param parentId 상위메뉴 아이디
     * @param subMenuRequest 하위메뉴 요청 DTO
     */
    public void addSubMenu(final Long parentId, final MenuRequest subMenuRequest) {
        final Menu menu = this.findById(parentId);
        final Menu subMenu = subMenuRequest.toEntity();
        menu.addSubMenu(subMenu);
    }

    /**
     * 하위메뉴 삭제
     *
     * @param parentId 상위메뉴 아이디
     * @param subMenuTitle 하위메뉴 타이틀
     */
    public void removeSubMenu(final Long parentId, final String subMenuTitle) {
        final Menu menu = this.findById(parentId);
        final Optional<Menu> subMenu = menuRepository.findByTitle(new Title(subMenuTitle));

        menu.removeSubMenu(subMenu.get());
    }

    /**
     * 배너 추가
     *
     * @param parentId 상위메뉴 아이디
     * @param bannerRequest 배너 요청 DTO
     */
    public void addBanner(final Long parentId, final BannerRequest bannerRequest) {
        final Menu menu = this.findById(parentId);
        final Banner banner = bannerRequest.toEntity();

        menu.addBanner(banner);
    }

    /**
     * 메뉴 조회
     *
     * @param id 메뉴 아이디
     * @return Menu
     */
    private Menu findById(final Long id) {
        return menuRepository.findById(id)
                .orElseThrow(FailedReadException::new);
    }

    /**
     * 모든 메뉴 조회
     *
     * @return 모든 메뉴 목록
     */
    public MenuResponses getAllMenus() {
        final List<Menu> menus = menuRepository.findAll();
        return MenuResponses.of(menus);
    }

    /**
     * 상위 메뉴를 이용하여 해당 메뉴의 모든 하위 메뉴 조회
     *
     * @param title 조회할 하위메뉴 목록의 상위 메뉴 타이틀
     * @return 하위메뉴 목록
     */
    public MenuResponses getSubMenus(final String title) {
        final Optional<Menu> menu = menuRepository.findByTitle(new Title(title));

        if (menu.isPresent()) {
            return MenuResponses.of(menu.get().getSubMenus());
        } else {
            return new MenuResponses();
        }
    }

}
