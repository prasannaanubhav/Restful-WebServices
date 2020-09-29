package com.web;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.model.PasswordResetModel;
import com.model.UserLoginRequest;

@Controller
public class WebController {

	@RequestMapping(value = "/home")
	public ModelAndView displayHome(@RequestParam("token") String token) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8081/restful/email-verification?token=" + token;
		ResponseEntity<String> resp = restTemplate.getForEntity(url, String.class);
		if (resp.getBody().equals("SUCCESS")) {
			return new ModelAndView("success");
		}
		return new ModelAndView("unsuccess");
	}

	@RequestMapping(value = "/password")
	public ModelAndView resetPasswordToken(@ModelAttribute("PasswordResetModel") PasswordResetModel passwordResetModel,
			@RequestParam("token") String token, Model model) {
		passwordResetModel.setToken(token);
		model.addAttribute("passwordResetModel", passwordResetModel);

		return new ModelAndView("resetpassword", "passwordResetModel", passwordResetModel);

	}

	@RequestMapping(value = "/passwordreset")
	public ModelAndView resetPasswordConfirmation(
			@ModelAttribute("PasswordResetModel") PasswordResetModel passwordResetModel) {

		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8081/restful/password-verification?token=" + passwordResetModel.getToken();
		UserLoginRequest userLoginRequest = new UserLoginRequest();
		userLoginRequest.setPassword(passwordResetModel.getConfirmpassword());
		HttpHeaders headers= new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<UserLoginRequest> entity = new HttpEntity<UserLoginRequest>(userLoginRequest, headers);
		try {
		ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		if(resp.getBody().equals("SUCCESS")) {
			return new ModelAndView("success");
		}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return new ModelAndView("unsuccess");

	}

}
