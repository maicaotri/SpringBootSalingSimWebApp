package com.app.controller;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.app.model.SimView;
import com.app.model.entitymodel.Netword;
import com.app.model.entitymodel.Sim;
import com.app.model.entitymodel.SimType;
import com.app.service.NetwordService;
import com.app.service.SimService;
import com.app.service.SimTypeService;
import com.app.util.PageProcessing;

@Controller
public class SimController {
	@Autowired
	private SimService simService;
	@Autowired
	private SimTypeService simTypeService;
	@Autowired
	private NetwordService networdService;

	@RequestMapping(value = "/sim/findSimDetail", method = RequestMethod.POST)
	public @ResponseBody SimView findSimDetail(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @RequestParam(name = "networkId", required = false) Integer networkId,
			@RequestParam(name = "simTypeId", required = false) Integer simTypeId,
			@RequestParam(name = "priceFrom", required = false, defaultValue = "0") double priceFrom,
			@RequestParam(name = "priceTo", required = false, defaultValue = "10000000") double priceTo,
			@RequestParam(name = "score", required = false) Integer score,
			@RequestParam(name = "totalNumbers", required = false) Integer totalNumbers,
			@RequestParam(name = "simFind", required = false) String number,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "size", required = false, defaultValue = "10") int size,
			@RequestParam(name = "notContainNumbers", required = false) List<Integer> notContainNumbers,
			@RequestHeader(name = "content-type", required = false, defaultValue = "UTF-8") String contentype) {

		List<Sim> listSim = simService.findSim(networkId, priceFrom, priceTo, score, totalNumbers, number,
				notContainNumbers, page, size, null, 0, simTypeId);
		List<Integer> listPage = PageProcessing.getListPage(page, size, getTotalRecords(request, response, session,
				networkId, simTypeId, priceFrom, priceTo, score, totalNumbers, number, page, size, notContainNumbers));
		return new SimView(listSim, listPage);
	}

	@RequestMapping(value = "/sim/findSimView", method = RequestMethod.POST)
	public @ResponseBody SimView findSimView(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @RequestParam(name = "networdId", required = false) Integer networdId,
			@RequestParam(name = "simTypeId", required = false) Integer simTypeId,
			@RequestParam(name = "priceFrom", required = false, defaultValue = "0") double priceFrom,
			@RequestParam(name = "priceTo", required = false, defaultValue = "10000000") double priceTo,
			@RequestParam(name = "score", required = false) Integer score,
			@RequestParam(name = "totalNumbers", required = false) Integer totalNumbers,
			@RequestParam(name = "simFind", required = false) String number,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "size", required = false, defaultValue = "10") int size,
			@RequestParam(name = "notContainNumbers", required = false) List<Integer> notContainNumbers,
			@RequestHeader(name = "content-type", required = false, defaultValue = "UTF-8") String contentype) {

		List<Sim> listSim = simService.findSim(networdId, priceFrom, priceTo, score, totalNumbers, number,
				notContainNumbers, page, size, null, null, simTypeId);
		List<Integer> listPage = PageProcessing.getListPage(page, size, getTotalRecords(request, response, session,
				networdId, simTypeId, priceFrom, priceTo, score, totalNumbers, number, page, size, notContainNumbers));
		return new SimView(listSim, listPage);
	}

	@RequestMapping(value = "/sim/getTotalRecords", method = RequestMethod.POST)
	public @ResponseBody int getTotalRecords(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @RequestParam(name = "networdId", required = false) Integer networdId,
			@RequestParam(name = "simTypeId", required = false) Integer simTypeId,
			@RequestParam(name = "priceFrom", required = false, defaultValue = "0") double priceFrom,
			@RequestParam(name = "priceTo", required = false, defaultValue = "10000000") double priceTo,
			@RequestParam(name = "score", required = false) Integer score,
			@RequestParam(name = "totalNumbers", required = false) Integer totalNumbers,
			@RequestParam(name = "simFind", required = false) String number,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "size", required = false, defaultValue = "10") int size,
			@RequestParam(name = "notContainNumbers", required = false) List<Integer> notContainNumbers) {

		return simService.countAll(networdId, priceFrom, priceTo, score, totalNumbers, number, notContainNumbers,
				simTypeId);
	}

	@RequestMapping(value = "admin/sim/addNewSim", method = RequestMethod.POST)
	public String addNewSim(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(name = "networdId", required = true) String networdId,
			@RequestParam(name = "simTypeId", required = true) String simTypeId,
			@RequestParam(name = "realNumber", required = true) String realNumber,
			@RequestParam(name = "price", required = true) double price) {

		// validate phone number
		String patternPhone = "\\d{10}";
		String patternNumber = "\\d+";
		boolean isValidSimNumber = Pattern.compile(patternPhone).matcher(realNumber).matches();
		boolean isValidSimTypeId = Pattern.compile(patternNumber).matcher(simTypeId).matches();
		boolean simExist = simService.simIsExist(realNumber);

		if (isValidSimNumber && isValidSimTypeId && !simExist) {
			byte netId = Byte.parseByte(networdId);
			Netword netword = networdService.getById(netId);
			SimType simType = simTypeService.getById(Short.parseShort(simTypeId + ""));
			int sumOfNumbers = sumOfNumbers(Integer.parseInt(realNumber));
			int score = sumOfNumbers % 10;

			Sim sim = new Sim();
			sim.setEnabled((byte) 1);
			sim.setNetword(netword);
			sim.setPrice(price);
			sim.setRealNumber(realNumber);
			sim.setScore(Short.parseShort(score + ""));
			sim.setSimType(simType);
			sim.setSold((byte) 0);
			sim.setSumOfNumbers(Short.parseShort(sumOfNumbers + ""));

			simService.add(sim);
		}
		return "redirect: " + request.getContextPath() + "/admin/sim/table";
	}

	@RequestMapping(value = "admin/sim/updateSim", method = RequestMethod.POST)
	public String updateSim(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(name = "simId", required = true) Integer simId,
			@RequestParam(name = "networdId", required = true) String networdId,
			@RequestParam(name = "simTypeId", required = true) String simTypeId,
			@RequestParam(name = "realNumber", required = true) String realNumber,
			@RequestParam(name = "price", required = true) double price,
			@RequestParam(name = "dealPrice", required = false) Double dealPrice,
			@RequestParam(name = "enabled", required = true) Integer status) {

		Sim sim = simService.getById(simId);
		if (sim != null) {

			// validate
			String patternPhone = "\\d{10}";
			String patternNumber = "\\d+";
			boolean isValidSimNumber = Pattern.compile(patternPhone).matcher(realNumber).matches();
			boolean isValidSimTypeId = Pattern.compile(patternNumber).matcher(simTypeId).matches();
			boolean isValidNetwordId = Pattern.compile(patternNumber).matcher(networdId).matches();
			boolean simExist = simService.simIsExist(realNumber);
			byte enabled = 1;
			if (status == 0)
				enabled = 0;
			if (isValidSimNumber && isValidSimTypeId && isValidNetwordId) {
				byte netId = Byte.parseByte(networdId);
				Netword netword = networdService.getById(netId);
				SimType simType = simTypeService.getById(Short.parseShort(simTypeId + ""));
				if (netword != null && simType != null) {
					int sumOfNumbers = sumOfNumbers(Integer.parseInt(realNumber));
					int score = sumOfNumbers % 10;

					sim.setEnabled(enabled);
					sim.setNetword(netword);
					sim.setPrice(price);
					sim.setDealPrice(dealPrice);
					sim.setRealNumber(realNumber);
					sim.setScore(Short.parseShort(score + ""));
					sim.setSimType(simType);
					sim.setSold((byte) 0);
					sim.setSumOfNumbers(Short.parseShort(sumOfNumbers + ""));
				}
			simService.update(sim);
			}
		}
		return "redirect: " + request.getContextPath() + "/admin/sim/table";
	}

	@RequestMapping(value = "/checksimIsExist", method = RequestMethod.POST)
	public @ResponseBody boolean checksimIsExist(HttpServletRequest request,
			@RequestParam(name = "simNumber", required = true) String simNumber) {
		return simService.simIsExist(simNumber);
	}

	@RequestMapping(value = "/sim/loadSim")
	public ModelAndView loadSimPost(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return new ModelAndView("loadsim");
	}

	@RequestMapping(value = "/admin")
	public ModelAndView admin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return new ModelAndView("admin/admin");
	}

	@RequestMapping(value = "/admin/sim/table")
	public ModelAndView getAdminTable(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return new ModelAndView("admin/table_sim");
	}

	@RequestMapping(value = "/admin/sim/addsim")
	public ModelAndView addSimPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return new ModelAndView("admin/sim_add");
	}

	@RequestMapping(value = { "/admin/sim/editsim/{id}", "/admin/sim/detail/{id}" })
	public ModelAndView editSim(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@PathVariable(name = "id", required = true) Integer id) {
		Sim sim = simService.getById(id);
		request.setAttribute("sim", sim);
		return new ModelAndView("admin/sim_edit");
	}


	public int sumOfNumbers(int numbers) {
		int sum = 0;
		while (numbers > 0) {
			sum = sum + numbers % 10;
			numbers = numbers / 10;
		}
		return sum;
	}

}
