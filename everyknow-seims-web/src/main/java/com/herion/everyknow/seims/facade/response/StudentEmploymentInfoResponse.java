package com.herion.everyknow.seims.facade.response;

import com.herion.everyknow.seims.facade.request.StudentEmploymentInfoRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description 学生就业信息 返回
 * @auther wubo25320
 * @create 2020-04-15 11:10
 */
@Getter
@Setter
@ToString
public class StudentEmploymentInfoResponse extends StudentEmploymentInfoRequest {
    private String companyNatureName;

    private String companyProvinceName;

    private String companyCityName;

    private String industryTypeName;

    private String employMethodMethod;
}
