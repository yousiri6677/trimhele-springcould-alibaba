package cn.youfull.trimhelp.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="demand")
public class demand {
    private long id;
    private String title;
    private String content;
    private long releaseId;
    private long demandTypeId;
    private double money;
    private String requirements;
    private Date releaseTime;
    private Date acceptanceTime;
    private String referenceDoc;
    private int state;

}
