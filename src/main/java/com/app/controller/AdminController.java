package com.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	@GetMapping("/table")
	public ModelAndView getListUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		request.setAttribute("mess", "Hello");
		return new ModelAndView("admin/basictable");
	}
}
