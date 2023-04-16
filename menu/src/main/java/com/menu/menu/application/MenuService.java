package com.menu.menu.application;

import com.menu.menu.domain.Menu;
import com.menu.menu.domain.MenuRepository;
import com.menu.menu.dto.MenuRequest;
import com.menu.menu.dto.MenuResponses;
import com.menu.menu.exception.FailedSavedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 메뉴 서비스
 */
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
     */
    public void addMenu(final MenuRequest menuRequest) {
        final Menu menu = menuRequest.toEntity();
        menuRepository.save(menu);
    }

    /**
     * 메뉴 수정
     *
     * @param oldTitle 변경될 타이틀
     * @param menuRequest 메뉴 요청 DTO
     * @throws FailedSavedException
     */
    public void updateMenu(final String oldTitle, final MenuRequest menuRequest) throws FailedSavedException {
        menuRepository.findByTitle(oldTitle)
                .ifPresentOrElse(
                        menu -> menu.update(menuRequest.toEntity()),
                        () -> new FailedSavedException(oldTitle)
                );
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
        final Optional<Menu> menu = menuRepository.findByTitle(title);

        if (menu.isPresent()) {
            return MenuResponses.of(menu.get().getSubMenus());
        } else {
            return new MenuResponses();
        }
    }

}
