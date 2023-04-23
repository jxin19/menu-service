package com.menu.menu.ui;

import com.menu.banner.dto.BannerRequest;
import com.menu.menu.dto.MenuRequest;
import com.menu.menu.application.MenuService;
import com.menu.menu.dto.MenuResponse;
import com.menu.menu.dto.MenuResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * 메뉴 컨트롤러
 */
@RequestMapping("/menu")
@RestController
public class MenuController {

    private final MenuService menuService;

    public MenuController(final MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 메뉴 등록 API
     *
     * @param menuRequest 메뉴 요청 DTO
     */
    @PostMapping
    public ResponseEntity<MenuResponse> addMenu(@RequestBody final MenuRequest menuRequest) {
        final MenuResponse menuResponse = menuService.addMenu(menuRequest);

        return ResponseEntity
                .created(URI.create("/" + menuResponse.getId()))
                .body(menuResponse);
    }

    /**
     * 메뉴 수정 API
     *
     * @param id 변경할 메뉴 ID
     * @param menuRequest 메뉴 요청 DTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<MenuResponse> updateMenu(@PathVariable final Long id, @RequestBody final MenuRequest menuRequest) {
        final MenuResponse menuResponse = menuService.updateMenu(id, menuRequest);

        return ResponseEntity
                .ok()
                .body(menuResponse);
    }

    /**
     * 메뉴 삭제 API
     *
      * @param id 삭제할 타이틀 ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeMenu(@PathVariable final Long id) {
        menuService.removeMenu(id);

        return ResponseEntity
                .ok()
                .build();
    }

    /**
     * 하위메뉴 추가 API
     *
     * @param parentId 상위메뉴 아이디
     * @param subMenuRequest 하위메뉴 요청 DTO
     */
    @PostMapping("/{parentId}/sub-menu")
    public ResponseEntity<Void> addSubMenu(@PathVariable final Long parentId, @RequestBody final MenuRequest subMenuRequest) {
        menuService.addSubMenu(parentId, subMenuRequest);

        return ResponseEntity
                .ok()
                .build();
    }

    /**
     * 하위메뉴 삭제 API
     *
     * @param parentId 상위메뉴 아이디
     * @param subMenuTitle 하위메뉴 타이틀
     */
    @DeleteMapping("/{parentId}/sub-menu/{subMenuTitle}")
    public ResponseEntity<Void> removeSubMenu(@PathVariable final Long parentId, @PathVariable final String subMenuTitle) {
        menuService.removeSubMenu(parentId, subMenuTitle);

        return ResponseEntity
                .ok()
                .build();
    }

    /**
     * 배너 추가
     *
     * @param parentId 상위메뉴 아이디
     * @param bannerRequest 배너 요청 DTO
     * @return
     */
    @PostMapping("/{parentId}/banner")
    public ResponseEntity<Void> addBanner(@PathVariable final Long parentId, @RequestBody final BannerRequest bannerRequest) {
        menuService.addBanner(parentId, bannerRequest);

        return ResponseEntity
                .ok()
                .build();
    }

    /**
     * 모든 메뉴 조회 API
     *
     * @return 모든 메뉴 목록
     */
    @GetMapping
    public ResponseEntity<MenuResponses> getAllMenus() {
        final MenuResponses menuResponses = menuService.getAllMenus();
        return ResponseEntity
                .ok()
                .body(menuResponses);
    }

    /**
     * 해당 메뉴의 모든 하위 메뉴 조회 API
     *
     * @param title 조회할 하위메뉴 목록의 상위 메뉴 타이틀
     * @return 하위메뉴 목록
     */
    @GetMapping("/{title}")
    public ResponseEntity<MenuResponses> getSubMenus(@PathVariable final String title) {
        final MenuResponses menuResponses = menuService.getSubMenus(title);
        return ResponseEntity
                .ok()
                .body(menuResponses);
    }

}
