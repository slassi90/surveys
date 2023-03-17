package com.in28minutes.springboot.firstrestapi.hworld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldResource {
	//hello-world --> hello world
	@RequestMapping("hello-world")
	 @ResponseBody
	public String helloworld() {
		return "hello world";
	}
	
	@RequestMapping("/hello-world-bean")
	public HelloWorldBean helloworldbean() {
		return new HelloWorldBean("helloWorld");
	}

	@RequestMapping("/hello-world-path-param/{name}")
	public HelloWorldBean helloworldbeanPathParam(@PathVariable String name) {
		return new HelloWorldBean("helloWorld" +name);
	}
	
	@RequestMapping("/hello-world-path-param/{name}/message/{message}")
	public HelloWorldBean helloworldbeanPathParam(@PathVariable String name,@PathVariable String message) {
		return new HelloWorldBean("helloWorld" +name);
	}
	
}
