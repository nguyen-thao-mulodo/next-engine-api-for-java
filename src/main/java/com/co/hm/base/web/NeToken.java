/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.hm.base.web;

import lombok.Data;

/**
 * This class holds Authentication Token of User
 */
@Data
public class NeToken implements java.io.Serializable {

  private String accessToken = null;
  private String refreshToken = null;
}
