package com.menu.banner.ui;

import com.menu.banner.application.BannerService;
import com.menu.banner.dto.BannerRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@RequestMapping("/banner")
@RestController
public class BannerController {

    private final BannerService bannerService;

    /**
     * 최상위 메뉴에 배너 추가 API
     *
     * @param bannerRequest 배너 요청 DTO
     */
    @PostMapping
    public void addBanner(final BannerRequest bannerRequest) {
        bannerService.addBanner(bannerRequest);
    }

}
