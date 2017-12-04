package com.co.hm.app.web;

import com.co.hm.base.web.NeToken;
import com.co.hm.utils.Constant;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * The controller is for authentication processing
 * <p>
 * - login: start authentication processing. call api Login of the next engine API <br>
 * - callback: The method redirected when the next engine API is authenticated. <br>
 * </p>
 */
@Controller
public class AuthController extends BaseController {

  /**
   * The method redirected when the next engine API is authenticated.
   * redirect to previous request when authentication is success
   *
   * @param request a HttpServletRequest object that contains the request the client
   * @param response an HttpServletResponse object that contains the response the servlet sends to the client
   * @param messages A specialization of the Model interface that controllers can use to select attributes for a redirect scenario
   * @return a String that the server will redirect to
   */
  @RequestMapping(value = "/callback", method = RequestMethod.GET)
  public String callback(HttpServletRequest request, HttpServletResponse response, RedirectAttributes messages) {
    logger.info("UID   ==============" + request.getParameter("uid"));
    logger.info("STATE ==============" + request.getParameter("state"));
    logger.info("PATH  ==============" + getReferrerPath(request));

    if (request.getParameter("uid") == null || request.getParameter("state") == null) {
      return "redirect:" + Constant.ROOT_PATH;
    }

    // Get accessToken and refreshToken
    HashMap<String, Object> apiResponse = neLogin(request, response, authClientProperty.getRedirectUrl());
    if (apiResponse != null) {
      try {
        saveToken(request, new NeToken(), apiResponse);
        messages.addFlashAttribute("data", getMessage("logged_in_success"));
      } catch (Exception e) {
        messages.addFlashAttribute("data", getMessage("st_error"));
        logger.error("error", e);
      }
    }

    return "redirect:" + getReferrerPath(request);
  }

  /**
   * start authentication processing. call api Login of the next engine API
   *
   * @param request a HttpServletRequest object that contains the request the client
   * @param response an HttpServletResponse object that contains the response the servlet sends to the client
   * @return a String that the server will redirect to
   */
  @RequestMapping(value = "/auth/login", method = RequestMethod.GET)
  public String login(HttpServletRequest request, HttpServletResponse response) {
    String path = request.getParameter("path");
    String query = "";
    if (path != null && !path.isEmpty()) {
      query = "?path=" + path;
    }
    neApiClientService.requireLogin(request, response, authClientProperty.getClientId(),
            authClientProperty.getClientSecret(), authClientProperty.getRedirectUrl() + query);
    return null;
  }
}
