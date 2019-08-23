package com.kgc.house.utils;

public class Page {

    private Integer page = 1;  //页码
    private Integer rows = 3;   //页大小

    public Page() {
    }

    public Page(int page, int rows) {
        this.page = page;
        this.rows = rows;
    }


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
