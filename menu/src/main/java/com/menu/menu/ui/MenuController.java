package com.menu.menu.ui;

import com.menu.menu.application.MenuService;
import com.menu.menu.dto.MenuRequest;
import com.menu.menu.dto.MenuResponses;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 메뉴 컨트롤러
 */
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@RequestMapping("/menu")
@RestController
public class MenuController {

    private final MenuService menuService;

    /**
     * 메뉴 등록 API
     *
     * @param menuRequest 메뉴 요청 DTO
     */
    @PostMapping
    public void addMenu(final MenuRequest menuRequest) {
        menuService.addMenu(menuRequest);
    }

    /**
     * 메뉴 수정 API
     *
     * @param oldTitle 변경될 타이틀
     * @param menuRequest 메뉴 요청 DTO
     */
    @PutMapping("/{oldTitle}")
    public void updateMenu(@PathVariable final String oldTitle, final MenuRequest menuRequest) {
        menuService.updateMenu(oldTitle, menuRequest);
    }

    /**
     * 메뉴 삭제 API
     *
      * @param id 삭제할 타이틀 ID
     */
    @DeleteMapping("/{id}")
    public void removeMenu(@PathVariable final Long id) {
        menuService.removeMenu(id);
    }

    /**
     * 모든 메뉴 조회 API
     *
      * @return 모든 메뉴 목록
     */
    @GetMapping
    public MenuResponses getAllMenus() {
        return menuService.getAllMenus();
    }

    /**
     * 해당 메뉴의 모든 하위 메뉴 조회 API
     *
      * @param title 조회할 하위메뉴 목록의 상위 메뉴 타이틀
     * @return 하위메뉴 목록
     */
    @GetMapping("/{title}")
    public MenuResponses getSubMenus(@PathVariable final String title) {
        return menuService.getSubMenus(title);
    }

}
