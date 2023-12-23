package com.study.novel.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.novel.core.common.constant.CacheConsts;
import com.study.novel.core.common.constant.DatabaseConsts;
import com.study.novel.dao.entity.HomeFriendLink;
import com.study.novel.dao.mapper.HomeFriendLinkMapper;
import com.study.novel.dto.resp.HomeFriendLinkRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 友情链接 缓存管理类
 * @author 597
 */
@Component
@RequiredArgsConstructor
public class FriendLinkCacheManager {

    private final HomeFriendLinkMapper homeFriendLinkMapper;

    @Cacheable(cacheManager = CacheConsts.REDIS_CACHE_MANAGER,
    cacheNames = CacheConsts.HOME_FRIEND_LINK_CACHE_NAME)
    public List<HomeFriendLinkRespDto> listFriendLinks() {
        // 从友情链接表中查询出友情链接列表
        QueryWrapper<HomeFriendLink> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc(DatabaseConsts.CommonColumnEnum.SORT.getName());
        return homeFriendLinkMapper.selectList(queryWrapper).stream().map(homeFriendLink -> {
            HomeFriendLinkRespDto homeFriendLinkRespDto = new HomeFriendLinkRespDto();
            BeanUtils.copyProperties(homeFriendLink, homeFriendLinkRespDto);
            return homeFriendLinkRespDto;
        }).collect(Collectors.toList());
    }

}
