package com.kgc.house.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.mapper.TypeMapper;
import com.kgc.house.pojo.Type;
import com.kgc.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    public List<Type> selectTypeAll() {


        //封装实体类
        List<Type> types = typeMapper.selectByExample(null);


        return types;
    }

    public PageInfo<Type> GetTypeByPage(int page, int pagesize) {

        //开启分页
        PageHelper.startPage(page, pagesize);

        //查询所有的信息
        List<Type> list = typeMapper.selectByExample(null);

        //获取分页的相关信息
        PageInfo pageInfo = new PageInfo(list);

        return pageInfo;
    }

    public int AddType(Type type) {

        int i = typeMapper.insertSelective(type);

        return i;
    }

    public Type SelectTypeOne(Integer id) {

        Type type = typeMapper.selectByPrimaryKey(id);

        return type;
    }


    public int updata(Type type) {

        int i = typeMapper.updateByPrimaryKeySelective(type);

        return i;
    }

    public int DeleteType(Integer id) throws Exception {

        int i = typeMapper.deleteByPrimaryKey(id);

        return i;
    }

    public int delMoreType(List<Integer> ids) {

        int i = typeMapper.delMoreType(ids);

        return i;
    }
}
