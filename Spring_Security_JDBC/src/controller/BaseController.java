package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {
	@RequestMapping(path= {"/","home/index.html"})
	public String index() {
		return "home/index";
	}
	@RequestMapping(path= "/member/hello.html")
	public String hello() {
		return "hello";
	}
}
