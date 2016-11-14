package net.tatans.iapetus.android.rest.util;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * json对象映射工具类之jackson封装
 * 
 * @author ljh
 * 
 */
@Component
public class JsonMapper {
    private ObjectMapper objectMapper=new ObjectMapper();

	public JsonMapper() {
        // 设置默认日期格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        //提供其它默认设置
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,    false);
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
    }

    /**
     * 将对象转换成json字符串格式（默认将转换所有的属性）
     * 
     * @param o
     * @return
     */
    public String toJsonStr(Object value) throws JsonGenerationException, JsonMappingException, IOException {
        return objectMapper.writeValueAsString(value);
    }

    /**
     * 将对象转换成json字符串格式
     * 
     * @param value 需要转换的对象(注意，需要在要转换的对象中定义JsonFilter注解)
     * @param properties 需要转换的属性
     */
    public String toJsonStr(Object value, String[] properties) throws JsonGenerationException, JsonMappingException, IOException {
    	Class<? extends Object> valueClass;
    	if(value instanceof java.util.List){
    		List<?> li=(List<?>)value;
    		if(li.size()==0){return "";}
    		valueClass=li.get(0).getClass();
    	}else{
    		valueClass=value.getClass();
    	}
        return objectMapper.writer(
                new SimpleFilterProvider().addFilter(
                        AnnotationUtils.getValue(
                                AnnotationUtils.findAnnotation(valueClass, JsonFilter.class))
                                .toString(), SimpleBeanPropertyFilter
                                .filterOutAllExcept(properties)))
                .writeValueAsString(value);

    }

    /**
     * 将对象转换成json字符串格式
     * 
     * @param value 需要转换的对象(注意，需要在要转换的对象中定义JsonFilter注解)
     * @param properties2Exclude  需要排除的属性
     */
    public String toJsonStrWithExcludeProperties(Object value,    String[] properties2Exclude) throws JsonGenerationException,
            JsonMappingException, IOException {
        return objectMapper.writer(
                new SimpleFilterProvider().addFilter(
                        AnnotationUtils.getValue(
                                AnnotationUtils.findAnnotation(value.getClass(), JsonFilter.class))
                                .toString(), SimpleBeanPropertyFilter
                                .serializeAllExcept(properties2Exclude)))
                .writeValueAsString(value);

    }

    /**
     * 将对象json格式直接写出到流对象中（默认将转换所有的属性）
     * 
     * @param o
     * @return
     */
    public void writeJsonStr(OutputStream out, Object value)
            throws JsonGenerationException, JsonMappingException, IOException {
        objectMapper.writeValue(out, value);
    }
    
    /**
     * 将对象json格式直接写出到流对象中
     * 
     * @param value 需要转换的对象(注意，需要在要转换的对象中定义JsonFilter注解)
     * @param properties 需要转换的属性
     */
    public void writeJsonStr(OutputStream out, Object value, String[] properties)
            throws JsonGenerationException, JsonMappingException, IOException {

         objectMapper.writer(
                new SimpleFilterProvider().addFilter(
                        AnnotationUtils.getValue(AnnotationUtils.findAnnotation(
                                        value.getClass(), JsonFilter.class))
                                .toString(), SimpleBeanPropertyFilter
                                .filterOutAllExcept(properties)))
                .writeValue(out, value);

    }
    
    /**
     * 将对象转换成json字符串格式
     * 
     * @param value 需要转换的对象
     * @param properties2Exclude 需要排除的属性(注意，需要在要转换的对象中定义JsonFilter注解)
     */
    public void writeJsonStrWithExcludeProperties(OutputStream out, Object value, String[] properties2Exclude) throws JsonGenerationException,
            JsonMappingException, IOException {
        objectMapper.writer(
                new SimpleFilterProvider().addFilter(
                        AnnotationUtils.getValue(
                                AnnotationUtils.findAnnotation(value.getClass(), JsonFilter.class))
                                .toString(), SimpleBeanPropertyFilter
                                .serializeAllExcept(properties2Exclude)))
                .writeValue(out, value);

    }

    /**
     * 得到jackson原始ObjectMapper进行本类未开放api的调用
     * 
     * @return
     */
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
    
    public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}


}