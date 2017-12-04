package com.co.hm.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Show Home Page
 */

@Controller
public class IndexController {

  @RequestMapping("")
  public String index() {
    return "index";
  }

}
