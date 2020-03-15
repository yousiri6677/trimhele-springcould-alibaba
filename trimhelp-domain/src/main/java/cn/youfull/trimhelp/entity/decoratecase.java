package cn.youfull.trimhelp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="decoratecase")
public class decoratecase {
    private long id;
    private long companyId;
    private long imgId;
    private String  title;
    private String content;
    private double area;
    private int browseNum;
    private long demandTypeId;

}
