package com.study.swagger.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/test")
public class TestController {

    @ApiOperation(value = "sayHello")
    @RequestMapping(value="/hello", method=RequestMethod.GET)
    @ResponseBody
    public String sayHello(String name){
        return "hello " + name;
    }
}
