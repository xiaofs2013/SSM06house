package com.kgc.house.protalController;

import com.kgc.house.pojo.District;
import com.kgc.house.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller(value = "districtController2")
@RequestMapping("/page")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @RequestMapping("/getAllDistrict")
    @ResponseBody
    public List<District> SelectDistrictAll() {

        List<District> list = districtService.selectDistrictAll();

        return list;

    }

}
