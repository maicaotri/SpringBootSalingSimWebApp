package com.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.model.entitymodel.MainUser;
import com.app.service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;

	@RequestMapping("/dangnhap")
	public String loginToSystem(HttpServletRequest request, HttpServletResponse response, HttpSession session, 
			@RequestParam(name = "error", required = false) String error) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication.getName().equals("anonymousUser")) {
			System.out.println(error);
			request.setAttribute("mess", "Nhập tên đăng nhập và mật khẩu.");
			return "login";
		} else {
			MainUser u = userService.getByUsername(authentication.getName());
			if (u.getRole().equals("ROLE_ADMIN"))
				return "redirect:/admin/danhsach";
			return "redirect:/admin/cart/";
		}
	}

	@RequestMapping("/dangnhapweb")
	public String login(@RequestParam(name = "error", required = false) String error) {
		System.out.println(error);
		return "login";
	}

	@RequestMapping("/dangxuat")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		SecurityContextHolder.getContext().setAuthentication(null);
		return "redirect:/trangchu";
	}

	@RequestMapping("/")
	public ModelAndView getListUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return new ModelAndView("redirect:/trangchu");
	}
}
