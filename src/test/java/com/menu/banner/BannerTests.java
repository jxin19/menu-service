package com.menu.banner;

import com.menu.banner.domain.Banner;
import com.menu.banner.domain.Image;
import com.menu.banner.domain.Link;
import com.menu.menu.MenuTests;
import com.menu.menu.exception.FailedSavedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("배너 관련 기능")
public class BannerTests {

    @Test
    public void 배너_추가() {
        // given
        final Banner 새배너 = Banner.builder()
                .image(new Image("https://image.msscdn.net/images/event_banner/2023041009491100000095973.jpg"))
                .link(new Link("https://www.musinsa.com/app/campaign/index/critic_brandweek"))
                .build();

        // when
        MenuTests.상의.addBanner(새배너);

        // then
        assertAll(
                () -> assertThat(MenuTests.상의.getBanner()).isNotNull(),
                () -> assertThat(새배너).isNotNull().isInstanceOf(Banner.class),
                () -> assertThat(새배너.getLink()).isNotNull().isInstanceOf(Link.class),
                () -> assertThat(새배너.getImage()).isNotNull().isInstanceOf(Image.class)
        );
    }

    @Test
    public void 배너_추가_실패() {
        // given
        final Banner 새배너2 = Banner.builder()
                .image(new Image("https://image.msscdn.net/images/event_banner/2023041009491100000095972.jpg"))
                .link(new Link("https://www.musinsa.com/app/campaign/index/critic_brandweek2"))
                .build();
        MenuTests.서브메뉴_추가();

        // then
        assertThatThrownBy(() -> MenuTests.니트_스웨터.addBanner(새배너2))
                .isInstanceOf(FailedSavedException.class)
                .hasMessage("저장을 실패했습니다.");
    }

}
