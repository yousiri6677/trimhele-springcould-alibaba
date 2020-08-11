package com.trimhelp.starter.commons.annotion;

import java.lang.annotation.*;

/**
 * 业务操作详细日志注解
 * wl
 * 2020-02-20
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface BussinessLogMonitor {
    /**
     * 操作名称
     * @return
     */
    String hanleName() default "";

    /**
     * 操作类型（1新增；2修改；3删除）
     * @return
     */
    String handleType() default "1";

    /**
     * 设备类型 0：pc 1：app
     * @return
     */
    int derviceType() default 0;

    /**
     * 操作内容
     * @return
     */
    String handleContent() default "";

    /**
     * 操作表名
     * @return
     */
    String handleTable() default "";

    /**
     * 操作表主键名称
     * @return
     */
    String handleTableKey() default "";
    /**
     * 操作表主键值
     * @return
     */
    String handleTableId() default "";

    /**
     * 参数结构
     * @return
     */
    String paramStr() default "";

    /**
     * 条件结构
     * @return
     */
    String conditionStr() default "";
    /**
     * 是否查询一条数据
     * @return
     */
    String isOne() default "1";



//   Class parseclass()default DefaultContentParse.class;
//
//   Class serviceclass()default IService.class;
}
