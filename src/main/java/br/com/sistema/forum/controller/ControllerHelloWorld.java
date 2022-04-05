package br.com.sistema.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerHelloWorld {
	
	@RequestMapping("/")
	@ResponseBody
	public String controllerHello() {
		return "Hello World!";		
	}

}
