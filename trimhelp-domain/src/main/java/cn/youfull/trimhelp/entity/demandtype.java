package cn.youfull.trimhelp.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="demandtype")
public class demandtype {


    private  long id;

    /**
     * 类型名称
     */
    private String typeName;
}
