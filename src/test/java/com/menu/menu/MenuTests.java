package com.menu.menu;

import com.menu.menu.domain.Link;
import com.menu.menu.domain.Menu;
import com.menu.menu.domain.Title;
import com.menu.menu.exception.FailedRemovedException;
import com.menu.menu.exception.FailedSavedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("메뉴 관련 기능")
public class MenuTests {

    public static final Menu 상의 = Menu.builder()
            .title(new Title("상의"))
            .link(new Link("https://www.musinsa.com/dp/newin"))
            .build();
    public static final Menu 니트_스웨터 = Menu.builder()
            .title(new Title("니트/스웨터"))
            .link(new Link("https://www.musinsa.com/dp/newin?categoryCode=001006"))
            .build();

    @Test
    public void 메뉴_생성() {
        // then
        assertAll(
                () -> assertThat(상의).isNotNull(),
                () -> assertThat(상의.getTitle()).isNotNull().isInstanceOf(Title.class),
                () -> assertThat(상의.getLink()).isNotNull().isInstanceOf(Link.class)
        );
    }

    @Test
    public void 메뉴_업데이트() {
        // when
        final Menu 업데이트_상의 = Menu.builder()
                .title(new Title("new 상의"))
                .link(new Link("https://www.musinsa.com/dp/newin"))
                .build();
        상의.update(업데이트_상의);

        // then
        assertAll(
                () -> assertThat(상의).isNotNull(),
                () -> assertThat(상의.getTitle()).isNotNull().isInstanceOf(Title.class),
                () -> assertThat(상의.getLink()).isNotNull().isInstanceOf(Link.class),
                () -> assertThat(상의.getTitle()).isEqualTo(업데이트_상의.getTitle())
        );
    }

    @Test
    public static void 서브메뉴_추가() {
        // when
        상의.addSubMenu(니트_스웨터);

        // then
        assertThat(상의.getSubMenus().size()).isGreaterThan(0);
    }

    @Test
    public void 서브메뉴_추가_실패() {
        // given
        final Menu 상의 = Menu.builder()
                .title(new Title("상의"))
                .link(new Link("https://www.musinsa.com/dp/newin"))
                .build();
        상의.addSubMenu(니트_스웨터);

        // when
        final Menu 니트_스웨터2 = Menu.builder()
                .title(new Title("니트/스웨터"))
                .link(new Link("https://www.musinsa.com/dp/newin?categoryCode=001006"))
                .build();

        // then
        assertThatThrownBy(() -> 상의.addSubMenu(니트_스웨터2))
                .isInstanceOf(FailedSavedException.class)
                .hasMessage("저장을 실패했습니다.");
    }

    @Test
    public void 서브메뉴_삭제() {
        // given
        final Menu 상의 = Menu.builder()
                .title(new Title("상의"))
                .link(new Link("https://www.musinsa.com/dp/newin"))
                .build();
        상의.addSubMenu(니트_스웨터);

        // when
        상의.removeSubMenu(니트_스웨터);

        // then
        assertThat(상의.getSubMenus().stream()
                .noneMatch(menu -> menu.getTitle().equals(니트_스웨터.getTitle())))
                .isTrue();
    }

    @Test
    public void 서브메뉴_삭제_실패() {
        // given
        final Menu 상의 = Menu.builder()
                .title(new Title("상의"))
                .link(new Link("https://www.musinsa.com/dp/newin"))
                .build();

        // then
        assertThatThrownBy(() -> 상의.removeSubMenu(니트_스웨터))
                .isInstanceOf(FailedRemovedException.class)
                .hasMessage("삭제를 실패했습니다.");
    }

}
