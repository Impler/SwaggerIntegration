# SpringMVC集成Swagger2

## 1 建立基本SpringMVC工程

###　1.1建立SpringMVC Maven工程
web.xml  
```xml
<servlet>
	<servlet-name>spring</servlet-name>
	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	<init-param>
		<description>Servlet Context</description>
		<param-name>contextConfigLocation</param-name>
		<param-value>classpath:spring-config/applicationContext.xml</param-value>
	</init-param>
	<load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
	<servlet-name>spring</servlet-name>
	<url-pattern>/</url-pattern>
</servlet-mapping>
```
### 1.2创建一个TestController：  
```java
package com.study.swagger.control;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value="/hello", method=RequestMethod.GET)
    @ResponseBody
    public String sayHello(String name){
        return "hello " + name;
    }
}
```
### 1.3 配置spring bean
applicatonContext.xml
```xml
<mvc:annotation-driven />
<mvc:default-servlet-handler/>
<context:component-scan base-package="com.study.swagger.control" />
```
确保该Controller能够正常访问：  
![确保能够正常访问](resources/images/正常访问.png)  

## 2 引入Swagger2
### 2.1 引入Swagger2依赖
```xml
<!-- Swagger2 Dependency -->
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger2</artifactId>
	<version>2.7.0</version>
</dependency>
<dependency>
	<groupId>com.fasterxml.jackson.core</groupId>
	<artifactId>jackson-databind</artifactId>
	<version>2.8.7</version>
</dependency>
```

### 2.2 新增Swagger配置类
```java
package com.study.swagger.config;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("impler")
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Impler Apis")
                .description("Impler Apis details")
                .license("copyright©impler.cn")
                .version("1.0")
                .build();
    }
}

```

### 2.3 配置swagger bean
```xml
<context:component-scan base-package="com.study.swagger.config" />
```

### 2.4 部署启动
访问http://localhost:8080/swagger/v2/api-docs：  
![api-docs](resources/images/api-docs.png)  
返回的JSON信息中，paths对应Controller中RequestMapping配置的路径  

## 3 引入Swagger UI
上面的配置保证了Swagger后台运作正常。Swagger UI实际就是一套完整的操作页面。可以到[https://github.com/swagger-api/swagger-ui](https://github.com/swagger-api/swagger-ui)将这些静态文件下载下来，然后放到webapp根目录。但是这样显然会增加项目结构复杂度。这里采用另外一种方式，即以依赖jar包的方式引入这些静态文件。  

### 3.1 引入Swagger UI依赖
```xml
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger-ui</artifactId>
	<version>2.7.0</version>
</dependency>
```
该jar包的结构如下：  
![swagger-ui-structure](resources/images/swagger-ui-structure.png "swagger-ui-structure")  
这种方式的好处就是不会对现有项目结构造成污染，配置方便。  

### 3.2 部署启动
直接访问Swagger UI的首页：[http://localhost:8080/swagger/swagger-ui.html](http://localhost:8080/swagger/swagger-ui.html)。  
![swagger-ui](resources/images/swagger-ui.png)  
