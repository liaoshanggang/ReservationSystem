package com.myworld.reservation.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myworld.reservation.annotation.Controller;
import com.myworld.reservation.annotation.RequestMapping;
import com.myworld.reservation.dao.UserInfoDao;
import com.myworld.reservation.vo.UserInfo;

@Controller
public class UserInfoController {
	
	@RequestMapping("/user/login")
	public String login(UserInfo uInfo,HttpSession session){
		System.out.println("login:"+uInfo.getLoginName()+"=="+uInfo.getLoginPass());
		uInfo = new UserInfoDao().login(uInfo);
		if(uInfo!=null){
			session.setAttribute("userInfo", uInfo);
			return "redirect:/success.jsp";
		}else{
			return "redirect:/login.html";
		}
	}
	
	@RequestMapping("/user/reg")
	public String reg(UserInfo uInfo,HttpServletResponse response){
		System.out.println("reg");
		return null;
	}
}
