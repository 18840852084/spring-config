package com.zgl.spring.environment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zgl
 * @date 2019/3/27 下午6:02
 */
@RestController
@RequestMapping("/spring")
public class HelloController {

	@GetMapping("/hello")
	public String helloSpring(@RequestParam String str){
		return "hello spring";
	}
}