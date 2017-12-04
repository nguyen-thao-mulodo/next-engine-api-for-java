package com.co.hm.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines method related to String handling.
 * <p>
 * - ReadTextFileToString: Read contain in file to a String
 * </p>
 */
public final class StringUtils {

  private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);

  private StringUtils() {

  }

  /**
   * Read text file to string
   *
   * @param fileName the file name need to be read
   * @return the content of the fileName
   */
  public static String readTextFileToString(String fileName) {
    StringBuilder sb = new StringBuilder();
    try(
        BufferedReader br = new BufferedReader(
        new FileReader(Constant.UPLOADED_FORDER + fileName))) {

      String line;

      while ((line = br.readLine()) != null) {
        sb.append(line + System.lineSeparator());
      }
    } catch (Exception e) {
      logger.error("error", e);
    }
    return sb.toString();
  }

}
