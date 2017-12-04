package com.co.hm.base.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The base class of all model classes in the application
 * It is abstract because there is no meaning to instantiate this class.
 */
@Data
@MappedSuperclass
public abstract class BaseObject implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", columnDefinition = "INT(11)")
  private long id;

  @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
  private Date createdAt;

  @Column(name = "updated_at", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
  private Date updatedAt;

}
