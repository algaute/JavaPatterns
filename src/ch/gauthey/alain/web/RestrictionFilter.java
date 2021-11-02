package ch.gauthey.alain.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter( urlPatterns = "/*" )
public class RestrictionFilter implements Filter {

    public RestrictionFilter() {
        System.out.println("RestrictionFilter constructor");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Inside RestrictionFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("Inside RestrictionFilter doFilter");

        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        System.out.println("Inside RestrictionFilter destroy");
    }


}
