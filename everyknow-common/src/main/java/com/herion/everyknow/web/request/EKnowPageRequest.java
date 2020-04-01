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
     * 当前页
     */
    private int page;
    /**
     * 每页行数
     */
    private int pageSize;
    /**
     * 当前页第一条数据在 list 中的位置, 从 0 开始
     */
    private long start;
    /**
     * 总记录数
     */
    private long totalCount;

    public EKnowPageRequest() {
        this.page = DEFAULT_PAGE;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    /**
     * 起始行数（默认从0行开始计算）（默认20行一页）
     * 例：首次查询，行数为0，再次查询第2页，行数为20
     *
     * @param start 起始行数   可以使用方法计算：HsjryPageRequest.getStartOfPage(int pageNo)
     */
    public EKnowPageRequest(int start) {
        this(start, 0L, DEFAULT_PAGE_SIZE);
    }

    /**
     * 起始行数（默认从0行开始计算）
     * 例：首次查询，行数为0，再次查询第2页，行数为 HsjryPageRequest.getStartOfPage(2, int pageSize)
     *
     * @param start    可以使用方法计算：HsjryPageRequest.getStartOfPage(int pageNo, int pageSize)
     * @param pageSize
     */
    public EKnowPageRequest(long start, int pageSize) {
        this(start,0L, pageSize);
    }

    public EKnowPageRequest(long start, long totalCount) {
        this(start,totalCount,DEFAULT_PAGE_SIZE);
    }

    /**
     * 默认构造方法.
     *
     * @param start     本页数据在数据库中的起始位置
     * @param totalCount 数据库中总记录条数
     * @param pageSize  本页容量
     */
    public EKnowPageRequest(long start, long totalCount, int pageSize) {
        this.page = DEFAULT_PAGE;
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.pageSize = pageSize;
        this.start = start;
        this.totalCount = totalCount;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
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

    /**
     * 取总页数
     * @return
     */
    public long getTotalPageCount() {
        return  this.pageSize <= 0 ? 0L : (this.totalCount + (long)this.pageSize - 1L) / this.pageSize;
    }

    /**
     * 取当前页码, 页码从 1 开始
     * @return
     */
    public int getCurrentPageNo() {
        return (int)(this.start / (long)this.pageSize + 1L);
    }

    /**
     * 该页是否有下一页
     * @return
     */
    public boolean hasNextPage() {
        return (long)this.getCurrentPageNo() < this.getTotalPageCount();
    }

    /**
     * 该页是否有上一页
     * @return
     */
    public boolean hasPreviousPage() {
        return this.getCurrentPageNo() > 1;
    }

    /**
     * 获取任一页第一条数据在数据集的位置.
     * @param pageNo
     * @param pageSize
     * @return
     */
    public static int getStartOfPage(int pageNo, int pageSize) {
        return (pageNo -1) * pageSize;
    }

    /**
     * 获取任一页第一条数据在数据集的位置，每页条数使用默认值.
     * @param pageNo
     * @return
     */
    protected static int getStartOfPage(int pageNo) {
        return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
    }
}
