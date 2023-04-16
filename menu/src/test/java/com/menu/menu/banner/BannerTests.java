package com.menu.menu.banner;

import com.menu.banner.domain.Banner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("배너 기능")
public class BannerTests {

    @DisplayName("배너 추가")
    @Test
    public void 배너_추가() {
        // given
        final Banner banner = Banner.builder()
                .image("https://image.msscdn.net/images/event_banner/2023041009491100000095973.jpg")
                .link("https://www.musinsa.com/app/campaign/index/critic_brandweek")
                .build();

        // then
        assertThat(banner)
                .isNotNull();
    }

}
