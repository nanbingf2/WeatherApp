package com.rogercw.weather.service.impl;

import com.rogercw.weather.pojo.City;
import com.rogercw.weather.pojo.CityList;
import com.rogercw.weather.service.CityDataService;
import com.rogercw.weather.util.Xml2ObjectUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @Author: rogercw
 * @Date: 2018/7/23 21:21
 * @Version 1.0
 */
@Service
public class CityDataServiceImpl implements CityDataService {

    @Override
    public List<City> getCityList() throws Exception {
        //1：从类路径读取配置文件
        Resource resource=new ClassPathResource("cityList.xml");
        BufferedReader br=new BufferedReader(new InputStreamReader(resource.getInputStream()));
        StringBuffer buffer=new StringBuffer();
        String line="";
        while((line=br.readLine())!=null){
            buffer.append(line);
        }
        //2：使用工具类将读取到的xml内容转换为Java对象
        CityList cityList= (CityList) Xml2ObjectUtil.xml2Object(buffer.toString(),CityList.class);
        return cityList.getCityList();
    }
}
