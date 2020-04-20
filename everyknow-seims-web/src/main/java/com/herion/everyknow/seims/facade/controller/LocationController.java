package com.herion.everyknow.seims.facade.controller;

import com.herion.everyknow.seims.dao.entity.Location;
import com.herion.everyknow.seims.dao.entity.Province;
import com.herion.everyknow.seims.facade.request.LocationRequest;
import com.herion.everyknow.seims.service.LocationService;
import com.herion.everyknow.seims.service.ProvinceService;
import com.herion.everyknow.web.request.http.CommonHttpRequest;
import com.herion.everyknow.web.response.EKnowResponse;
import com.herion.everyknow.web.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description 城市代码表 Controller 层
 * @auther wubo25320
 * @create 2020-03-24 13:40
 */
@RestController
@RequestMapping("/location")
@Api(tags = "location", description = "城市代码获取接口")
public class LocationController {

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private LocationService locationService;

    @ApiOperation(value = "获取全部省份")
    @RequestMapping(path = "/getProvinces", method = RequestMethod.POST)
    public EKnowResponse getProvinces() {
        List<Province> provinces = provinceService.selectAll();
        return ResultUtils.getSuccessResponse(provinces);
    }

    @ApiOperation(value = "通过省份代码获取全部城市")
    @RequestMapping(value = "/getCitys")
    public EKnowResponse getCitys(@RequestBody CommonHttpRequest<LocationRequest> request) {
        List<Location> locations = locationService.selectByProvince(request.getRequest().getProvinceCode());
        return ResultUtils.getSuccessResponse(locations);
    }
}
