package com.youfull.trimhelp.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author scc
 * @since 2019-12-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("Usertype")
public class Usertype {


    /**
     * 用户类型Id
     */
    private Integer Id;

    /**
     * 用户类型
     */
    private String userType;


}
