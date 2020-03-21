package com.herion.everyknow.web.request;

import java.io.Serializable;

/**
 * @Description 公共分页请求报文
 * @auther wubo25320
 * @create 2020-03-18 23:03
 */
public class EKnowPageRequest extends EKnowRequest implements Serializable {
    private static final long serialVersionUID = 7272640550926560362L;

    private static int DEFAULT_PAGE_SIZE = 20;
    private static int DEFAULT_PAGE = 1;
    private int page;
    private int pageSize;
    private long start;
    private long totalCount;

    public EKnowPageRequest() {
        this.page = DEFAULT_PAGE;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    public EKnowPageRequest(int start) {
        this(start, 0L, DEFAULT_PAGE_SIZE);
    }

    public EKnowPageRequest(long start, int pageSize) {
        this(start,0L, pageSize);
    }

    public EKnowPageRequest(long start, long totalSize, int pageSize) {
        this.page = DEFAULT_PAGE;
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.pageSize = pageSize;
        this.start = start;
        this.totalCount = totalSize;
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

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getTotalPageCount() {
        return  this.pageSize <= 0 ? 0L : (this.totalCount + (long)this.pageSize - 1L) / this.pageSize;
    }

    public int getCurrentPageNo() {
        return (int)(this.start / (long)this.pageSize + 1L);
    }

    public boolean hasNextPage() {
        return (long)this.getCurrentPageNo() < this.getTotalPageCount();
    }

    public boolean hasPreviousPage() {
        return this.getCurrentPageNo() > 1;
    }

    public static int getStartOfPage(int pageNo, int pageSize) {
        return (pageNo -1) * pageSize;
    }

    protected static int getStartOfPage(int pageNo) {
        return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
    }
}
