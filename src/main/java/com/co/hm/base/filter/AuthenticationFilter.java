package com.co.hm.base.filter;

import com.co.hm.base.web.NeToken;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * An Authentication Filter to check user logged-in or not.
 * If user is not login, the request is blocked by redirecting to the login page
 */
public class AuthenticationFilter extends GenericFilterBean {

  private final String redirectPath;

  public AuthenticationFilter(String redirectPath) {
    this.redirectPath = redirectPath;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    String path = req.getServletPath();
    NeToken neToken = (NeToken) req.getSession().getAttribute(NeToken.class.getName());
    if (neToken == null) {
      res.sendRedirect(this.redirectPath + "?path=" + path);
      return;
    }
    filterChain.doFilter(request, response);
  }
}
