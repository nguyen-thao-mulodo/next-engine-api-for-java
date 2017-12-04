package com.co.hm.app.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import com.co.hm.base.domain.BaseUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Model holding the user information of the next engine
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users", indexes = {
        @Index(name = "uid", columnList = "uid")
})
public class User extends BaseUser implements Serializable {

}
