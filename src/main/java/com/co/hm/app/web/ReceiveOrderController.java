package com.co.hm.app.web;

import com.co.hm.utils.ConvertUtils;
import com.co.hm.utils.NeApiURL;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Search receive_order_base
 */

@Controller
public class ReceiveOrderController extends BaseController {

  @RequestMapping(value = "/receive_order_search", method = RequestMethod.GET)
  public String searchReceiveOrder(HttpServletRequest request,
                                   HttpServletResponse response,
                                   Model model) {
    execApiSearchReceiveOrder(request, response, model);
    return "show";
  }

  /**
   * Execute api /api_v1_receiveorder_base/search and binding the result into the Model
   *
   * @param request: a HttpServletRequest object that contains the request the client
   * @param response: an HttpServletResponse object that contains the response the servlet sends to the client
   */
  private void execApiSearchReceiveOrder(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Model model) {
    try {
      HashMap<String, Object> apiResponse = neApiExecuteAndSaveToken(request,
              getCurrentToken(request),
              NeApiURL.RECEIVE_ORDER_SEARCH_PATH,
              createSearchReceiveOrderParams());
      if (apiResponse != null) {
        model.addAttribute("data", ConvertUtils.convertOb2String(apiResponse));
      }
    } catch (Exception e) {
      logger.error("error", e);
    }
  }

  /**
   * create the params to call the api /api_v1_receiveorder_base/search
   *
   * @return the params to call Next Engine API
   */
  private HashMap<String, String> createSearchReceiveOrderParams() {
    HashMap<String, String> apiParams = new HashMap<>();

    // Condition : receive_order_id = 1
    apiParams.put("receive_order_shop_id-eq", "1");

    // Condition : receive_order_date > 2017-01-19 00:00:00
    apiParams.put("receive_order_date-gt", "2015-01-19 00:00:00");

    // Field is selected
    apiParams.put("fields", "receive_order_shop_id,receive_order_id,receive_order_shop_cut_form_id,receive_order_date,receive_order_import_date");

    return apiParams;
  }

}