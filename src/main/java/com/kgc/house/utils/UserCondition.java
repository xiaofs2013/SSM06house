package com.kgc.house.utils;


//继承Page 只是为了添加页码和页大小 方便使用PageHelp进行分页

//User类的查询工具类
public class UserCondition extends Page {

    //类型全用包装类、封装类
    private String name;    //名称

    private String telephone;  //电话

    private Integer startAge;  //开始年龄

    private Integer endAge;    //结束年龄


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getStartAge() {
        return startAge;
    }

    public void setStartAge(Integer startAge) {
        this.startAge = startAge;
    }

    public Integer getEndAge() {
        return endAge;
    }

    public void setEndAge(Integer endAge) {
        this.endAge = endAge;
    }
}
