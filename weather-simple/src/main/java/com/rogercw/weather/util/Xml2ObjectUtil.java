package com.rogercw.weather.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.StringReader;

/**
 * @Author: rogercw
 * @Date: 2018/7/23 21:22
 * @Version 1.0
 * 该工具类用来将xml数据转化为对象
 */
public class Xml2ObjectUtil {

    public static Object xml2Object(String xmlData,Class<?> clazz) throws Exception {
        JAXBContext context=JAXBContext.newInstance(clazz);
        Reader reader=new StringReader(xmlData);
        Unmarshaller unmarshaller=context.createUnmarshaller();
        Object object = unmarshaller.unmarshal(reader);
        reader.close();
        return object;
    }

}
