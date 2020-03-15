package cn.youfull.trimhelp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="companyinfo")
public class Companyinfo {
    private long id;
    private long userId;
    private String companyProfile;
    private long companyLogo;
    private double regCapital;
    private String address;
}
