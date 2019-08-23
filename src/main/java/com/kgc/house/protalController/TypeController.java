package com.kgc.house.protalController;

import com.kgc.house.pojo.Type;
import com.kgc.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/page")
public class TypeController {

    @Autowired
    private TypeService typeService;


    @RequestMapping("/GetTypeAll")
    @ResponseBody
    public List<Type> SelectTypeAll() {

        List<Type> list = typeService.selectTypeAll();

        return list;
    }

}
