package com.herion.everyknow.seims.facade.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.herion.everyknow.web.request.http.CommonHttpPageRequest;
import com.herion.everyknow.web.response.EKnowPageResponse;
import com.herion.everyknow.web.util.ResultUtils;

/**
 * @Description 分页工具
 * @auther wubo25320
 * @create 2020-04-02 23:02
 */
public class PageUtil {

    /**
     * mybatis-plus 的分页 转 EKnowPageResponse
     * @param page
     * @return
     */
    public static EKnowPageResponse plus2EKnow(IPage page) {
        return ResultUtils.getSuccessPageResponse(page.getRecords(), page.getCurrent(), (int)page.getSize(), page.getTotal());
    }

    /**
     * CommonHttpPageRequest 转 mybatis-plus 的分页
     * @param request
     * @return
     */
    public static Page eKnow2Plus(CommonHttpPageRequest request) {
        return new Page(request.geteKnowRequest().getCurrent(), request.geteKnowRequest().getPageSize());
    }

    /**
     * CommonHttpPageRequest 转 mybatis-plus 的分页
     * @param request
     * @return
     */
    public static Page eKnow2Plus(CommonHttpPageRequest request, boolean isSearchCount) {
        return new Page(request.geteKnowRequest().getCurrent(), request.geteKnowRequest().getPageSize(),isSearchCount);
    }
}
