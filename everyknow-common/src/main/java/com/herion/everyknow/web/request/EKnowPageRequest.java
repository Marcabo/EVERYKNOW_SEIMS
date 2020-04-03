package com.herion.everyknow.web.request;

import java.io.Serializable;

/**
 * @Description 公共分页请求报文
 * @auther wubo25320
 * @create 2020-03-18 23:03
 */
public class EKnowPageRequest extends EKnowRequest implements Serializable {
    private static final long serialVersionUID = 7272640550926560362L;

    /**
     * 默认每页行数
     */
    private static int DEFAULT_PAGE_SIZE = 20;

    /**
     * 默认页面
     */
    private static int DEFAULT_PAGE = 1;

    /**
     * 页数
     */
    private long page;
    /**
     * 每页行数
     */
    private int pageSize;
    /**
     * 当前页第一条数据在 list 中的位置, 从 0 开始
     */
    private long current;
    /**
     * 总记录数
     */
    private long totalCount;

    public EKnowPageRequest() {
        this.page = DEFAULT_PAGE;
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.current = 1L;
        this.totalCount = 0L;
    }

    /**
     *
     * @param current 当前页码
     */
    public EKnowPageRequest(int current) {
        this(current, 0L, DEFAULT_PAGE_SIZE);
    }

    /**
     *
     * @param current    当前页码
     * @param pageSize
     */
    public EKnowPageRequest(long current, int pageSize) {
        this(current,0L, pageSize);
    }

    public EKnowPageRequest(long current, long totalCount) {
        this(current,totalCount,DEFAULT_PAGE_SIZE);
    }

    /**
     * 默认构造方法.
     *
     * @param current     本页数据在数据库中的起始位置
     * @param totalCount 数据库中总记录条数
     * @param pageSize  本页容量
     */
    public EKnowPageRequest(long current, long totalCount, int pageSize) {
        this.page = DEFAULT_PAGE;
        this.pageSize = DEFAULT_PAGE_SIZE;
        if (pageSize > 0 && pageSize <= 500) {
            this.pageSize = pageSize;
        }
        this.current = 1L;
        if (current > 1) {
            this.current = current;
        }
        this.totalCount = totalCount;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 取总记录数
     * @return
     */
    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    /**
     * 取当前页码, 页码从 1 开始
     * @return
     */
    public long getCurrent() {
        return this.current;
    }

    /**
     * 该页是否有下一页
     * @return
     */

    public boolean isHasNextPage() {
        return (long)this.getCurrent() < this.getPage();
    }

    /**
     * 该页是否有上一页
     * @return
     */
    public boolean isHasPreviousPage() {
        return this.getCurrent() > 1;
    }

    /**
     * 获取任一页第一条数据在数据集的位置.
     * @param currentPageNo
     * @param pageSize
     * @return
     */
    public static int getStartOfPage(int currentPageNo, int pageSize) {
        return (currentPageNo -1) * pageSize;
    }

    /**
     * 获取任一页第一条数据在数据集的位置，每页条数使用默认值.
     * @param currentPageNo
     * @return
     */
    protected static int getStartOfPage(int currentPageNo) {
        return getStartOfPage(currentPageNo, DEFAULT_PAGE_SIZE);
    }
}
