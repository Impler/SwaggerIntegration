package com.study.swagger.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import io.swagger.models.Path;
import io.swagger.models.Swagger;

/**
 * 将接口url中追加模式后缀.do
 * @author impler
 * @date 2017年9月30日
 */
@Aspect
@EnableAspectJAutoProxy
@Component
public class SwaggerApiSuffixAspect {
	
    @AfterReturning(pointcut="execution(public io.swagger.models.Swagger springfox.documentation.swagger2.mappers.ServiceModelToSwagger2MapperImpl.mapDocumentation(..))",
            returning="swagger")
	public void doBeforeBussinessCheck(Swagger swagger){
        Map<String, Path> paths = swagger.getPaths();
        if(null != paths){
            Map<String, Path> newPaths = new HashMap<String, Path>(paths);
            paths.clear();
            Iterator<String> it = newPaths.keySet().iterator();
            while(it.hasNext()){
                String oldKey = it.next();
                String newKey = oldKey  + ".do";
                paths.put(newKey, newPaths.get(oldKey));
            }
            newPaths = null;
        }
	}
	
	
	
}
