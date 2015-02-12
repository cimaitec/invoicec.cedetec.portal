package com.cimait.invoicec.portal.core.filter;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.minidev.json.JSONObject;

import org.joda.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cimait.invoicec.core.config.GlobalConfig;
import com.cimait.invoicec.portal.core.security.AuthHelper;

@Component
public class AuthFilter implements Filter {

	@Autowired
	protected AuthHelper authHelper;
	
	@Autowired
	protected GlobalConfig globalConfig;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		boolean isLogin = request.getRequestURI().endsWith("login"); 
		boolean isApi = false;
		
		//si son llamadas las apis rest aplicar seguridad
		//TODO reemplazar por metodo de config.
		if (request.getRequestURI().contains("api/v1")) {
			isApi = true;
		}
		
		if (!globalConfig.getSecurity().equals("0") && !request.getMethod().trim().equals("OPTIONS") && !isLogin && isApi) {
			String token = request.getHeader("Authorization");
			if (token == null) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"No Autorizado");
			} else {
				try {
					if (tokenInvalid(token)) {
						response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"No Autorizado");
					}
				} catch (Exception e) {
					e.printStackTrace();
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"No Autorizado");
				}
			}
		}
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	} 

	public boolean tokenInvalid(String token) {
		try {
			JSONObject jo = authHelper.verify(token);
			if (jo == null ) return true;
			Calendar cal = Calendar.getInstance();
			String sExpiredAt = (String) jo.get("expiresAt");
			Instant today = new org.joda.time.Instant(cal.getTimeInMillis());
			Instant expiredAt = new org.joda.time.Instant();
			expiredAt = Instant.parse(sExpiredAt);
			if (today.isAfter(expiredAt)) return true;
		} catch (Exception e) {
			System.err.println("Error al verificar token");
			e.printStackTrace();
			return true;
		}
		return false;
	}
}
