package com.cisco.security.authserverauthentication.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("oauth2authSuccessHandler")
public class Oauth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();;
	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;

	
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		OAuth2AuthenticationToken oToken = (OAuth2AuthenticationToken) authentication;

		System.out.println("Principle : "+oToken.getPrincipal().getName());
		System.out.println("Registration ID : "+oToken.getAuthorizedClientRegistrationId());
		System.out.println("Autorities : ");
		oToken.getAuthorities().stream().forEach((x)->{
			System.out.println(x);
		});
		System.out.println("Attributes : ");
		oToken.getPrincipal().getAttributes().forEach((key,value)->{
			System.out.println(key+" ---> "+value);
		});
		this.redirectStrategy.sendRedirect(request, response,"/user/greeting/"+oToken.getPrincipal().getAttribute("name"));

	}
}
