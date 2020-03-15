package cn.youfull.trimhelp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")
public class User {
    private long id;
    private String userName;
    private String account;
    private String passWord;
    private String idNumber;
    private int userTypeId;
    private String address;
    private int userState;







}
