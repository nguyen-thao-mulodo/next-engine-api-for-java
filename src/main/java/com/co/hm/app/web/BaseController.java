package com.co.hm.app.web;

import com.co.hm.app.domain.User;
import com.co.hm.app.repository.CompanyRepository;
import com.co.hm.app.repository.UserRepository;
import com.co.hm.app.domain.Company;

/**
 * Base class of Controller
 */
public abstract class BaseController extends com.co.hm.base.web.BaseController<User, UserRepository, Company, CompanyRepository> {

  public BaseController() {
    super(User.class, Company.class);
  }

}
