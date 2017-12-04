package com.co.hm.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Define common method
 * <p>
 * - ConvertOb2String: Convert object to String <br>
 * </p>
 */
public final class ConvertUtils {

  private ConvertUtils() {
  }

  /**
   * Convert Object to Json
   *
   * @param ob the object need to be converted to String
   * @return the string value of the param ob
   * @throws JsonProcessingException if has errors when converting
   */
  public static String convertOb2String(Object ob) throws JsonProcessingException {
    ObjectMapper om = new ObjectMapper();
    return om.writerWithDefaultPrettyPrinter().writeValueAsString(ob);
  }
}
