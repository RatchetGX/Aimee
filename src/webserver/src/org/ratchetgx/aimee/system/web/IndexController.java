package org.ratchetgx.aimee.system.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ratchetgx.aimee.common.ProcePublic;
import org.ratchetgx.aimee.common.webbase.BaseController;
import org.ratchetgx.aimee.system.service.ZxkfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class IndexController extends BaseController {
	@Autowired
	private ZxkfService zxkfService;
	
	@RequestMapping(value = "index")
    public String showIndexNavigator(ModelMap model, HttpServletRequest request) {
		
		model.put("welcomeMsg", "index!!!");
		return "/system/index";
		
		
	}
}
