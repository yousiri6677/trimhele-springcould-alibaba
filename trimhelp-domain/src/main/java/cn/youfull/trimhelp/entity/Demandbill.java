package cn.youfull.trimhelp.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="demandbill")
public class Demandbill {


    private  long id;

    /**
     * 用户id
     */
    private long userId;

    /**
     * 需求id
     */
    private long demand;

    /**
     * 完成时间
     */
    private Date completionTime;
}
