package cn.youfull.trimhelp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.io.Serializable;

/**
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")
public class User implements Serializable  {

    private static final long serialVersionUID = -90000032L;
    private long id;
    private String userName;
    private String account;
    private String passWord;
    private String idNumber;
    private int userTypeId;
    private String address;
    private int userState;

}
