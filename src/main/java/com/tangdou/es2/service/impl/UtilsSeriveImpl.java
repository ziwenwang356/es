package com.tangdou.es2.service.impl;

import com.tangdou.es2.entity.City;
import com.tangdou.es2.entity.Province;
import com.tangdou.es2.repository.CityRepository;
import com.tangdou.es2.repository.ProvinceRepository;
import com.tangdou.es2.service.UtilsSerive;
import com.tangdou.es2.utils.Convert;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UtilsSeriveImpl implements UtilsSerive {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CityRepository cityRepos;
    @Autowired
    private ProvinceRepository provinceRepos;


    @Override
    public int getCityByName(String name) {
        if (StringUtils.isBlank(name)) {
            return 0;
        }
        Integer id = null;
        City city = cityRepos.findByName(name);
        if (null != city) {
            id = city.getId();
        }
        if (null != id) {
            return id;
        }
        return Convert.toInt(id, 0);
    }

    @Override
    public int getProvinceByName(String name) {
        if (StringUtils.isBlank(name)) {
            return 0;
        }
        Integer id = null;
        Province province = provinceRepos.findByName(name);
        if (null != province) {
            id = province.getId();
        }
        if (null != id) {
            return id;
        }
        return Convert.toInt(id, 0);
    }


}
