package com.co.hm.app.web;

import com.co.hm.utils.*;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Download and Upload CSV file master_goods
 */

@Controller
@RequestMapping("/master_goods")
public class MasterGoodsController extends BaseController {

  private final static String CSV_MASTER_GOODS = "master_goods.csv";

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String masterGoods() {
    return "MasterGoods/index";
  }

  @RequestMapping(value = "/show", method = RequestMethod.GET)
  public String showMasterGoods() {
    return "MasterGoods/show";
  }

  @RequestMapping(value = "/upload", method = RequestMethod.POST)
  public String uploadMasterGoods(HttpServletRequest request,
                                  HttpServletResponse response,
                                  @RequestParam("file") MultipartFile file,
                                  RedirectAttributes redirectAttributes) {
    logger.info("File info ======== " + file.getSize());
    if (!FileUtils.uploadFile(file)) {
      redirectAttributes.addFlashAttribute("message", getMessage("choose_file_to_upload"));
    } else {
      execApiUploadMasterGoods(request, response, redirectAttributes, file.getOriginalFilename());
    }
    return "redirect:/master_goods/show";
  }

  @RequestMapping(value = "/download", method = RequestMethod.GET)
  public @ResponseBody
  FileSystemResource downloadCSVFile(HttpServletRequest request,
                                     HttpServletResponse response) {

    return FileUtils.downloadCSVFile(response, Constant.UPLOADED_FORDER, CSV_MASTER_GOODS);
  }

  /**
   * Execute api /api_v1_master_goods/upload
   *
   * @param request: a HttpServletRequest object that contains the request the client
   * @param response: an HttpServletResponse object that contains the response the servlet sends to the client
   * @param redirectAttributes A specialization of the Model interface that controllers can use to select attributes for a redirect scenario
   * @param fileName the param to call the Next Engine API
   */
  private void execApiUploadMasterGoods(HttpServletRequest request,
                                        HttpServletResponse response,
                                        RedirectAttributes redirectAttributes,
                                        String fileName) {
    try {
      HashMap<String, Object> apiResponse = neApiExecuteAndSaveToken(request, getCurrentToken(request),
              NeApiURL.MASTER_GOODS_UPLOAD_PATH, createUploadMasterGoodsParams(fileName));
      if (apiResponse != null) {
        redirectAttributes.addFlashAttribute("data", ConvertUtils.convertOb2String(apiResponse));
      }
    } catch (Exception e) {
      logger.error("error", e);
    }
  }

  /**
   * create params for api /api_v1_master_goods/upload
   *
   * @param fileName the param to call the Next Engine API
   * @return the result of the API
   */
  private HashMap<String, String> createUploadMasterGoodsParams(String fileName) {
    HashMap<String, String> apiParams = new HashMap<>();
    apiParams.put("wait_flag", "1");
    apiParams.put("data_type", "csv");
    apiParams.put("data", StringUtils.readTextFileToString(fileName));
    return apiParams;
  }

}
