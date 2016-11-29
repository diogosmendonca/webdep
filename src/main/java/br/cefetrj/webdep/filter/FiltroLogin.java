package br.cefetrj.webdep.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class FiltroLogin implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {    
        
    	HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        
        String path = ((HttpServletRequest) request).getRequestURI();
        if (path.equals("/webdep/FrontControllerServlet")) {
            chain.doFilter(request, response);
        }
        
        else {
        	String loginURL = request.getContextPath() + "/index.jsp";
            boolean loggedIn = session != null && session.getAttribute("id") != null;
            boolean loginRequest = request.getRequestURI().equals(loginURL);
             
            if (loggedIn || loginRequest){
            	chain.doFilter(request, response);
            } else {
            	Locale currentLocale = request.getLocale();
            	@SuppressWarnings("unused")
				String msg = "";
            	if(currentLocale.getDisplayCountry().equals("Brazil")) {
        			msg += "Login ou senha incorretos!";
        		} else msg += "Incorrect username or password!";
             	response.sendRedirect(loginURL);
            }
        }
    }
    
    @Override
    public void init(FilterConfig config) throws ServletException {
    }
    
    @Override
    public void destroy() {
    }
}
