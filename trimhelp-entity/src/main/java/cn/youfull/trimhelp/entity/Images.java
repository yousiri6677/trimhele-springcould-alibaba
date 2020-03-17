package cn.youfull.trimhelp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.io.Serializable;

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
@Table(name="Images")
public class Images {

    private  long id;
    /**
     * 图片大概信息
     */
    private String imgInfo;

    /**
     * 图片路径
     */
    private String imgPath;

    /**
     * 0:正常,1:默认
     */
    private Integer imgState;

    /**
     * 所属用户ID
     */
    private long userId;


}
