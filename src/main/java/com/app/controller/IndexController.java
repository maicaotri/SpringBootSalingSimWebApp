package com.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.model.entitymodel.Sim;
import com.app.service.SimService;

@Controller
public class IndexController {
	@Autowired
	private SimService simService;

	@RequestMapping("/trangchu")
	public ModelAndView getTrangChu(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		List<Sim> list = simService.getAll();
		request.setAttribute("list", list);
		return new ModelAndView("trangchu");
	}
	
	@RequestMapping("/footer")
	public ModelAndView getFooter(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return new ModelAndView("footer");
	}
	
	@RequestMapping("/menu_top")
	public ModelAndView getMenuTop(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return new ModelAndView("menu_top");
	}
	
	@RequestMapping("/listCheckbox")
	public String listCheckbox(@RequestParam(name="listCheckbox") List<Integer> listCheckbox) {
		for (Integer i : listCheckbox) {
			System.out.println(i);
		}
		return "wellcome";
	}
	
}
