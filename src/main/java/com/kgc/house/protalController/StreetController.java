package com.kgc.house.protalController;

import com.kgc.house.pojo.Street;
import com.kgc.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/page")
public class StreetController {

    @Autowired
    private StreetService streetService;

    @RequestMapping("/getStreetByDid")
    @ResponseBody
    public List<Street> getStreetById(Integer did) {

        List<Street> list = streetService.SelectStreetAllById(did);
        //这个地方did参数一定要注意 这里写了是did 网页那边也必须是did

        return list;

    }

}
