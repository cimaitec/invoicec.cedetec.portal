package com.cimait.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cimait.entity.UserDetails;

@Controller
@RequestMapping("/welcome")
public class HelloController {

	@RequestMapping(value="hello", method = RequestMethod.GET)
	public String welcome(ModelMap model) {

		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hello modificado dfsgsdf";

	}


	@RequestMapping(value="benvenuto", method = RequestMethod.GET)
	public String benvenuto(ModelMap model) {

		model.addAttribute("message", "Benvenuto mio amico");
		return "benvenuto";

	}
	
	@RequestMapping(value="angular", method = RequestMethod.GET)
	public String angular(ModelMap model) {
		model.addAttribute("message", "Benvenuto mio amico");
		return "angular";
	}
	
	
	 @Autowired UserDetails userDetails;
	 
	 @RequestMapping(value="springcontent", method=RequestMethod.GET)
	 @ResponseStatus(HttpStatus.OK)
	 public @ResponseBody UserDetails getUser() {
	 	        UserDetails userDetails = new UserDetails();
	 	        userDetails.setUserName("Praveen");
	 	        userDetails.setEmailId("praveen@gmail.com");
	 	        return userDetails;
	 }
	 
	
	
	
	
	
	
	
}