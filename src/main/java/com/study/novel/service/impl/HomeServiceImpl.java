package com.study.novel.service.impl;

import com.study.novel.core.common.resp.RestResp;
import com.study.novel.dto.resp.HomeBookRespDto;
import com.study.novel.dto.resp.HomeFriendLinkRespDto;
import com.study.novel.manager.FriendLinkCacheManager;
import com.study.novel.manager.HomeBookCacheManager;
import com.study.novel.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 首页模块 服务实现类
 * @author 597
 */
@RequiredArgsConstructor
@Service
public class HomeServiceImpl implements HomeService {

    private final HomeBookCacheManager homeBookCacheManager;
    private final FriendLinkCacheManager friendLinkCacheManager;

    @Override
    public RestResp<List<HomeBookRespDto>> listHomeBooks() {
        return RestResp.ok(homeBookCacheManager.listHomeBooks());
    }

    @Override
    public RestResp<List<HomeFriendLinkRespDto>> listHomeFriendLinks() {
        return RestResp.ok(friendLinkCacheManager.listFriendLinks());
    }
}
