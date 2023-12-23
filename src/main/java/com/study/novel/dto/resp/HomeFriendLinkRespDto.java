package com.study.novel.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 首页友情链接 响应DTO
 * @author 597
 */
@Data
public class HomeFriendLinkRespDto implements Serializable {

    /**
     * 链接名
     */
    @Schema(description = "链接名")
    private String linkName;

    /**
     * 链接url
     */
    @Schema(description = "链接url")
    private String linkUrl;
}
