package com.co.hm.app.domain;

import com.co.hm.base.domain.BaseCompany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * Model holding the company information of the next engine
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "companies", indexes = {@Index(name = "main_function_id", columnList = "main_function_id")})
public class Company extends BaseCompany implements Serializable {

}
