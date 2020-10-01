package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.Member;
import model.MemberRepository;


@Controller
@RequestMapping("/auth")
public class AuthController {
	MemberRepository repository = new MemberRepository();
	@RequestMapping(value = "register.html", method = RequestMethod.POST)
	public String register(Member obj,HttpServletRequest request) {
		String user,pass,email,tel,address;
		int phai;
		user = request.getParameter("username");
		pass = request.getParameter("password");
		email = request.getParameter("email");
		tel = request.getParameter("tel");
		address = request.getParameter("address");
		phai = Integer.parseInt(request.getParameter("gender"));
		Member mb = new Member();
		mb.setAddress(address);
		mb.setEmail(email);
		mb.setGender(phai);
		mb.setPassWord(pass);
		mb.setTel(tel);
		mb.setUserName(user);
		repository.add(mb);
		return "redirect:/auth/logon.html";
	}
	
	@RequestMapping("logon.html")
	public String logOn() {
		return "auth/logon";
	}
	
	@RequestMapping("register.html")
	public String register() {
		return "auth/register";
	}
	
}
