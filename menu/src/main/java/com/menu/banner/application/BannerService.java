package com.menu.banner.application;

import com.menu.banner.domain.Banner;
import com.menu.banner.dto.BannerRequest;
import com.menu.banner.dto.BannerResponse;
import org.springframework.stereotype.Service;

/**
 * 배너 서비스
 */
@Service
public class BannerService {

    private Banner banner;

    public BannerService(final Banner banner) {
        this.banner = banner;
    }

    /**
     * 최상위 메뉴에 배너 추가
     *
     * @param bannerRequest 배너 요청 DTO
     */
    public void addBanner(final BannerRequest bannerRequest) {
        this.banner = bannerRequest.toEntity();
    }

    /**
     * 최상위 메뉴의 배너 조회
     *
     * @return
     */
    public BannerResponse getBanner() {
        return BannerResponse.of(this.banner);
    }

}
