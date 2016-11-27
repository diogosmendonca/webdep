package br.cefetrj.webdep.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/app/*")
public class FiltroLogin implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        // If you have any <init-param> in web.xml, then you could get them
        // here by config.getInitParameter("name") and assign it as field.
    	  
        // Get init parameter 
        String testParam = config.getInitParameter("test-param"); 
   
        //Print the init parameter 
        System.out.println("Test Param: " + testParam); 
     }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    	  // Get the IP address of client machine.   
        String ipAddress = req.getRemoteAddr();
   
        // Log the IP address and current timestamp.
        System.out.println("IP "+ ipAddress + ", Time " + new Date().toString());
   
        // Pass request back down the filter chain
        chain.doFilter(req,res);
    }

    @Override
    public void destroy() {
        // If you have assigned any expensive resources as field of
        // this Filter class, then you could clean/close them here.
    }
}
