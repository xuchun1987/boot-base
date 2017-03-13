package boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/index")
	public String index(){
		System.out.println("--------index-----------");
		//logger.error("FDSFDFDSFSDFSDFSDFSD");
		return "index";
	}
	
}
