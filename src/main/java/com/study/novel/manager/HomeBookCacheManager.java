package com.study.novel.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.novel.core.common.constant.CacheConsts;
import com.study.novel.dao.entity.BookInfo;
import com.study.novel.dao.entity.HomeBook;
import com.study.novel.dao.mapper.BookInfoMapper;
import com.study.novel.dao.mapper.HomeBookMapper;
import com.study.novel.dto.resp.HomeBookRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 首页推荐小说 缓存管理类
 * @author 597
 */
@Component
@RequiredArgsConstructor
public class HomeBookCacheManager {

    private final HomeBookMapper homeBookMapper;
    private final BookInfoMapper bookInfoMapper;

    /**
     * 查询首页小说推荐，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.CAFFEINE_CACHE_MANAGER
    , value = CacheConsts.HOME_BOOK_CACHE_NAME)
    public List<HomeBookRespDto> listHomeBooks() {
        // 从首页小说推荐表中获取需要推荐的小说
        List<HomeBook> homeBooks = homeBookMapper.selectList(null);
        // 若小说集合不为空
        if (!CollectionUtils.isEmpty(homeBooks)) {
            // 获取小说的id
            List<Long> ids = new ArrayList<>();
            homeBooks.forEach(homeBook -> {
                ids.add(homeBook.getBookId());
            });
            // 获取小说的信息
            QueryWrapper<BookInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("id", ids);
            List<BookInfo> bookInfos = bookInfoMapper.selectList(queryWrapper);

            // 组装成dto数据
            if (!CollectionUtils.isEmpty(bookInfos)) {
                // 创建map对象，以实现根据小说id快速找到小说的信息
                Map<Long, BookInfo> bookInfoMap = bookInfos.stream()
                        .collect(Collectors.toMap(BookInfo::getId, Function.identity()));
                return homeBooks.stream().map(homeBook -> {
                    BookInfo bookInfo = bookInfoMap.get(homeBook.getBookId());
                    HomeBookRespDto homeBookRespDto = new HomeBookRespDto();
                    homeBookRespDto.setType(homeBook.getType());
                    BeanUtils.copyProperties(bookInfo, homeBookRespDto);
                    return homeBookRespDto;
                }).collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }

}
