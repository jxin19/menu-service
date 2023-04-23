package com.menu.menu;

import com.menu.AcceptanceTest;
import com.menu.banner.dto.BannerRequest;
import com.menu.menu.dto.MenuRequest;
import com.menu.menu.dto.MenuResponse;
import com.menu.menu.dto.MenuResponses;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("메뉴 관련 기능")
public class MenuAcceptableTests extends AcceptanceTest {

    @Test
    public void 메뉴_생성() {
        // given
        final MenuRequest 새메뉴 = MenuRequest.builder()
                .title("상의")
                .link("https://www.musinsa.com/dp/newin")
                .build();

        // when
        final ExtractableResponse<Response> 새메뉴_요청 = post("/menu", 새메뉴);

        // then
        final MenuResponse 새메뉴_조회 = 새메뉴_요청.as(MenuResponse.class);
        assertAll(
                () -> assertThat(새메뉴_요청.statusCode())
                        .isEqualTo(HttpStatus.CREATED.value()),
                () -> assertThat(새메뉴_요청.header("Location"))
                        .isNotBlank(),
                () -> assertThat(새메뉴_조회)
                        .isNotNull()
                        .isInstanceOf(MenuResponse.class)
        );
    }

    @Test
    public void 메뉴_수정() {
        // given
        메뉴_생성();
        final MenuRequest 메뉴 = MenuRequest.builder()
                .title("new 상의")
                .link("https://musinsa.com/dp/newin")
                .build();

        // when
        final String uri = String.format("/menu/%s", 1L);
        final ExtractableResponse<Response> 메뉴_수정_요청 = put(uri, 메뉴);

        // then
        assertThat(메뉴_수정_요청.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void 메뉴_삭제() {
        // given
        메뉴_생성();

        // when
        final String uri = String.format("/menu/%s", 1L);
        final ExtractableResponse<Response> 메뉴_삭제_요청 = delete(uri);

        // then
        assertThat(메뉴_삭제_요청.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void 하위메뉴_추가() {
        // given
        메뉴_생성();

        // when
        final MenuRequest 니트스웨터 = MenuRequest.builder()
                .title("니트스웨터")
                .link("https://www.musinsa.com/dp/newin?categoryCode=001006")
                .build();
        final String uri = String.format("/menu/%s/sub-menu", 1L);
        final ExtractableResponse<Response> 하위메뉴_추가_요청 = post(uri, 니트스웨터);

        // then
        assertThat(하위메뉴_추가_요청.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void 하위메뉴_삭제() {
        // given
        하위메뉴_추가();

        // when
        final String uri = String.format("/menu/%s/sub-menu/%s", 1L, "니트스웨터");
        final ExtractableResponse<Response> 하위메뉴_삭제_요청 = delete(uri);

        // then
        assertThat(하위메뉴_삭제_요청.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void 배너_추가() {
        // given
        final MenuRequest 바지_생성 = MenuRequest.builder()
                .title("바지")
                .link("https://www.musinsa.com/dp/newin?categoryCode=003")
                .build();
        final ExtractableResponse<Response> 새메뉴_요청 = post("/menu", 바지_생성);
        final MenuResponse 바지 = 새메뉴_요청.as(MenuResponse.class);

        // when
        final BannerRequest 배너_추가 = BannerRequest.builder()
                .image("https://image.msscdn.net/images/event_banner/2023041009491100000095973.jpg")
                .link("https://www.musinsa.com/app/campaign/index/critic_brandweek")
                .build();
        final String uri = String.format("/menu/%s/banner", 바지.getId());
        final ExtractableResponse<Response> 배너_추가_요청 = post(uri, 배너_추가);

        // then
        assertThat(배너_추가_요청.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void 모든메뉴_조회() {
        // given
        하위메뉴_추가();
        배너_추가();

        // when
        final ExtractableResponse<Response> 모든메뉴_조회_요청 = get("/menu");

        // then
        final MenuResponses 모든메뉴_조회 = 모든메뉴_조회_요청.as(MenuResponses.class);
        assertAll(
                () -> assertThat(모든메뉴_조회_요청.statusCode())
                        .isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(모든메뉴_조회)
                        .isNotNull()
                        .isInstanceOf(MenuResponses.class),
                () -> assertThat(모든메뉴_조회.size())
                        .isGreaterThanOrEqualTo(1)
        );
    }

    @Test
    public void 하위메뉴_조회() {
        // given
        하위메뉴_추가();

        // when
        final ExtractableResponse<Response> 하위메뉴_조회_요청 = get("/menu/상의");

        // then
        final MenuResponses 하위메뉴_조회 = 하위메뉴_조회_요청.as(MenuResponses.class);
        assertAll(
                () -> assertThat(하위메뉴_조회_요청.statusCode())
                        .isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(하위메뉴_조회)
                        .isNotNull()
                        .isInstanceOf(MenuResponses.class),
                () -> assertThat(하위메뉴_조회.size())
                        .isGreaterThanOrEqualTo(1)
        );
    }

}
