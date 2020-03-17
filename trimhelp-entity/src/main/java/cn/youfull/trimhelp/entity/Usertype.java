package cn.youfull.trimhelp.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * <p>
 * 
 * </p>
 *
 * @author scc
 * @since 2019-12-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Usertype")
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
