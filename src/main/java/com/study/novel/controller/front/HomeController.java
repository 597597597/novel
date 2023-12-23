package com.study.novel.controller.front;

import com.study.novel.core.common.constant.ApiRouterConsts;
import com.study.novel.core.common.resp.RestResp;
import com.study.novel.dto.resp.HomeBookRespDto;
import com.study.novel.dto.resp.HomeFriendLinkRespDto;
import com.study.novel.service.HomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 首页模块 API接口
 * @author 597
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRouterConsts.API_FRONT_HOME_URL_PREFIX)
@Tag(name = "HomeController", description = "前台门户-首页模块")
public class HomeController {

    private final HomeService homeService;

    /**
     * 首页小说推荐查询接口
     */
    @GetMapping("books")
    @Operation(summary = "首页小说推荐查询接口")
    public RestResp<List<HomeBookRespDto>> listHomeBooks(){
        return homeService.listHomeBooks();
    }

    /**
     * 首页友情链接列表查询接口
     */
    @Operation(summary = "首页友情链接列表查询接口")
    @GetMapping("friend_Link/list")
    public RestResp<List<HomeFriendLinkRespDto>> listHomeFriendLinks() {
        return homeService.listHomeFriendLinks();
    }
}
