package cn.youfull.trimhelp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "demandinfo")
public class Demandinfo {

    private long demandInfoId;
    private long demandTypeId;//对应需求类型Id
    private String demandInfoName;//具体需求名称
}
