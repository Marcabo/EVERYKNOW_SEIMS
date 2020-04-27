package com.herion.everyknow.seims.service.impl;

import cn.hutool.core.util.StrUtil;
import com.herion.everyknow.seims.dao.StudentDao;
import com.herion.everyknow.seims.dao.bean.StudentAndEmploy;
import com.herion.everyknow.seims.dao.entity.Student;
import com.herion.everyknow.seims.service.DataVisualService;
import com.herion.everyknow.seims.service.bean.DataVisualRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 数据可视化 ServiceImpl 层
 * @auther wubo25320
 * @create 2020-04-24 13:19
 */
@Service
public class DataVisualServiceImpl implements DataVisualService {

    private static String GRADUATION_EDUCATION_RATIO = "graduation_education_ratio";
    private static String GRADUATION_SEX_RATIO = "graduation_sex_ratio";
    private static String GRADUATION_EMPLOY_RATIO = "graduation_employ_ratio";
    private static String GRADUATION_INDUSTRY = "graduation_industry";
    private static String GRADUATION_NATURE = "graduation_nature";
    private static String GRADUATION_PROVINCE = "graduation_province";
    private static String GRADUATION_EMPLOY_METHOD = "graduation_employ_method";
    private static String GRADUATION_SALARY = "graduation_salary";

    private static String ZHUANKE = "专科";
    private static String BENKE = "本科";
    private static String SHUOSHI = "硕士";
    private static String BOSHI = "博士";
    private static String BOSHIHOU = "博士后";

    private static String NAN = "男";
    private static String NV = "女";

    private static String YIJIUYE = "已就业";
    private static String WEIJIUYE = "未就业";

    private static String CODE1 = "金融业";
    private static String CODE10 = "房地产业";
    private static String CODE11 = "电力、热力、燃气及水生产和供应业";
    private static String CODE12 = "建筑业";
    private static String CODE13 = "批发和零售业";
    private static String CODE14 = "水利、环境和公共设施管理业";
    private static String CODE15 = "军队";
    private static String CODE16 = "卫生和社会工作";
    private static String CODE17 = "采矿业";
    private static String CODE18 = "农、林、牧、渔业";
    private static String CODE19 = "居民服务、修理和其他服务业";
    private static String CODE2 = "公共管理、社会保障和社会组织";
    private static String CODE20 = "住宿和餐饮业";
    private static String CODE3 = "信息传输、软件和信息技术服务业";
    private static String CODE4 = "教育";
    private static String CODE5 = "科学研究和技术服务业";
    private static String CODE6 = "文化、体育和娱乐业";
    private static String CODE7 = "租赁和商务服务业";
    private static String CODE8 = "制造业";
    private static String CODE9 = "交通运输、仓储和邮政业";

    private static String force_unit = "部队单位";
    private static String higher_education_unit = "高等教育单位";
    private static String medical_and_health_unit = "医疗卫生单位";
    private static String middle_and_primary_education_unit = "中初教育单位";
    private static String other_institutions = "其他事业单位";
    private static String party_and_government_organs = "党政机关";
    private static String private_enterprise = "民营企业";
    private static String scientific_and_design_institute = "科研设计单位";
    private static String state_owned_enterprise = "国有企业";
    private static String three_funded_enterprise = "三资企业";

    private static String PROVINCE11 = "北京市";
    private static String PROVINCE12 = "天津市";
    private static String PROVINCE13 = "河北省";
    private static String PROVINCE14 = "山西省";
    private static String PROVINCE15 = "内蒙古自治区";
    private static String PROVINCE21 = "辽宁省";
    private static String PROVINCE22 = "吉林省";
    private static String PROVINCE23 = "黑龙江省";
    private static String PROVINCE31 = "上海市";
    private static String PROVINCE32 = "江苏省";
    private static String PROVINCE33 = "浙江省";
    private static String PROVINCE34 = "安徽省";
    private static String PROVINCE35 = "福建省";
    private static String PROVINCE36 = "江西省";
    private static String PROVINCE37 = "山东省";
    private static String PROVINCE41 = "河南省";
    private static String PROVINCE42 = "湖北省";
    private static String PROVINCE43 = "湖南省";
    private static String PROVINCE44 = "广东省";
    private static String PROVINCE45 = "广西壮族自治区";
    private static String PROVINCE46 = "海南省";
    private static String PROVINCE50 = "重庆市";
    private static String PROVINCE51 = "四川省";
    private static String PROVINCE52 = "贵州省";
    private static String PROVINCE53 = "云南省";
    private static String PROVINCE54 = "西藏自治区";
    private static String PROVINCE61 = "陕西省";
    private static String PROVINCE62 = "甘肃省";
    private static String PROVINCE63 = "青海省";
    private static String PROVINCE64 = "宁夏回族自治区";
    private static String PROVINCE65 = "新疆维吾尔自治区";
    private static String PROVINCE71 = "台湾省";
    private static String PROVINCE81 = "香港特别行政区";
    private static String PROVINCE82 = "澳门特别行政区";

    private static String enter_higher_school = "升学";
    private static String go_abroad = "出国";
    private static String join_army = "参军";
    private static String self_employed = "自主创业";
    private static String signing_employment_agreement = "签订就业协议书";
    private static String sign_contract = "签合同";

    private static String S0_3000 = "0-3000";
    private static String S3000_4000 = "3000-4000";
    private static String S4000_5000 = "4000-5000";
    private static String S5000_6000 = "5000-6000";
    private static String S6000_7000 = "6000-7000";
    private static String S7000_8000 = "7000-8000";
    private static String S8000_9000 = "8000-9000";
    private static String S9000_10000 = "9000-10000";
    private static String S10000_11000 = "10000-11000";
    private static String S11000_12000 = "11000-12000";
    private static String S12000_13000 = "12000-13000";
    private static String S13000_14000 = "13000-14000";
    private static String S14000_15000 = "14000-15000";
    private static String S15000_16000 = "15000-16000";
    private static String S16000_17000 = "16000-17000";
    private static String S17000_18000 = "17000-18000";
    private static String S18000_19000 = "18000-19000";
    private static String S19000_20000 = "19000-20000";
    private static String SL20000 = ">20000";


    @Autowired
    private StudentDao studentDao;


    @Override
    public Map<String, Map<String, Integer>> getByCondition(DataVisualRequest data) {
        Student query = new Student();
        query.setGraduationSession(data.getGraduationSession());
        query.setCollegeCode(data.getCollegeCode());
        query.setDeptCode(data.getDeptCode());
        query.setClazzId(data.getClazzId());

        // 获取到 学生及对应的 学生就业信息
        List<StudentAndEmploy> studentAndEmployList = studentDao.getStudentAndEmploy(query);

        // 返回的信息
        Map<String, Map<String, Integer>> resultMapMap = createResult();


        for (StudentAndEmploy studentAndEmploy : studentAndEmployList) {
            resultMapMap = toResult(resultMapMap, studentAndEmploy);
        }
        return resultMapMap;
    }

    private Map<String, Map<String, Integer>> createResult() {
        Map<String, Map<String, Integer>> tableMapMap = new LinkedHashMap<>();
        tableMapMap.put(GRADUATION_EDUCATION_RATIO, new LinkedHashMap<>());
        tableMapMap.get(GRADUATION_EDUCATION_RATIO).put("专科", 0);
        tableMapMap.get(GRADUATION_EDUCATION_RATIO).put("本科", 0);
        tableMapMap.get(GRADUATION_EDUCATION_RATIO).put("硕士", 0);
        tableMapMap.get(GRADUATION_EDUCATION_RATIO).put("博士", 0);
        tableMapMap.get(GRADUATION_EDUCATION_RATIO).put("博士后", 0);


        tableMapMap.put(GRADUATION_SEX_RATIO, new LinkedHashMap<>());
        tableMapMap.get(GRADUATION_SEX_RATIO).put("男", 0);
        tableMapMap.get(GRADUATION_SEX_RATIO).put("女", 0);

        tableMapMap.put(GRADUATION_EMPLOY_RATIO, new LinkedHashMap<>());
        tableMapMap.get(GRADUATION_EMPLOY_RATIO).put("已就业", 0);
        tableMapMap.get(GRADUATION_EMPLOY_RATIO).put("未就业", 0);


        tableMapMap.put(GRADUATION_INDUSTRY, new LinkedHashMap<>());
        tableMapMap.get(GRADUATION_INDUSTRY).put("金融业", 0);
        tableMapMap.get(GRADUATION_INDUSTRY).put("房地产业", 0);
        tableMapMap.get(GRADUATION_INDUSTRY).put("电力、热力、燃气及水生产和供应业", 0);
        tableMapMap.get(GRADUATION_INDUSTRY).put("建筑业", 0);
        tableMapMap.get(GRADUATION_INDUSTRY).put("批发和零售业", 0);
        tableMapMap.get(GRADUATION_INDUSTRY).put("水利、环境和公共设施管理业", 0);
        tableMapMap.get(GRADUATION_INDUSTRY).put("军队", 0);
        tableMapMap.get(GRADUATION_INDUSTRY).put("卫生和社会工作", 0);
        tableMapMap.get(GRADUATION_INDUSTRY).put("采矿业", 0);
        tableMapMap.get(GRADUATION_INDUSTRY).put("农、林、牧、渔业", 0);
        tableMapMap.get(GRADUATION_INDUSTRY).put("居民服务、修理和其他服务业", 0);
        tableMapMap.get(GRADUATION_INDUSTRY).put("公共管理、社会保障和社会组织", 0);
        tableMapMap.get(GRADUATION_INDUSTRY).put("住宿和餐饮业", 0);
        tableMapMap.get(GRADUATION_INDUSTRY).put("信息传输、软件和信息技术服务业", 0);
        tableMapMap.get(GRADUATION_INDUSTRY).put("教育", 0);
        tableMapMap.get(GRADUATION_INDUSTRY).put("科学研究和技术服务业", 0);
        tableMapMap.get(GRADUATION_INDUSTRY).put("文化、体育和娱乐业", 0);
        tableMapMap.get(GRADUATION_INDUSTRY).put("租赁和商务服务业", 0);
        tableMapMap.get(GRADUATION_INDUSTRY).put("制造业", 0);
        tableMapMap.get(GRADUATION_INDUSTRY).put("交通运输、仓储和邮政业", 0);

        tableMapMap.put(GRADUATION_NATURE, new LinkedHashMap<>());
        tableMapMap.get(GRADUATION_NATURE).put("部队单位", 0);
        tableMapMap.get(GRADUATION_NATURE).put("高等教育单位", 0);
        tableMapMap.get(GRADUATION_NATURE).put("医疗卫生单位", 0);
        tableMapMap.get(GRADUATION_NATURE).put("中初教育单位", 0);
        tableMapMap.get(GRADUATION_NATURE).put("其他事业单位", 0);
        tableMapMap.get(GRADUATION_NATURE).put("党政机关", 0);
        tableMapMap.get(GRADUATION_NATURE).put("民营企业", 0);
        tableMapMap.get(GRADUATION_NATURE).put("科研设计单位", 0);
        tableMapMap.get(GRADUATION_NATURE).put("国有企业", 0);
        tableMapMap.get(GRADUATION_NATURE).put("三资企业", 0);

        tableMapMap.put(GRADUATION_PROVINCE, new LinkedHashMap<>());
        tableMapMap.get(GRADUATION_PROVINCE).put("北京市", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("天津市", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("河北省", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("山西省", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("内蒙古自治区", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("辽宁省", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("吉林省", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("黑龙江省", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("上海市", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("江苏省", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("浙江省", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("安徽省", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("福建省", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("江西省", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("山东省", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("河南省", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("湖北省", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("湖南省", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("广东省", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("广西壮族自治区", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("海南省", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("重庆市", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("四川省", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("贵州省", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("云南省", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("西藏自治区", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("陕西省", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("甘肃省", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("青海省", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("宁夏回族自治区", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("新疆维吾尔自治区", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("台湾省", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("香港特别行政区", 0);
        tableMapMap.get(GRADUATION_PROVINCE).put("澳门特别行政区", 0);

        tableMapMap.put(GRADUATION_EMPLOY_METHOD, new LinkedHashMap<>());
        tableMapMap.get(GRADUATION_EMPLOY_METHOD).put("升学", 0);
        tableMapMap.get(GRADUATION_EMPLOY_METHOD).put("出国", 0);
        tableMapMap.get(GRADUATION_EMPLOY_METHOD).put("参军", 0);
        tableMapMap.get(GRADUATION_EMPLOY_METHOD).put("自主创业", 0);
        tableMapMap.get(GRADUATION_EMPLOY_METHOD).put("签订就业协议书", 0);
        tableMapMap.get(GRADUATION_EMPLOY_METHOD).put("签合同", 0);

        tableMapMap.put("graduation_salary", new LinkedHashMap<>());
        tableMapMap.get("graduation_salary").put("0-3000", 0);
        tableMapMap.get("graduation_salary").put("3000-4000", 0);
        tableMapMap.get("graduation_salary").put("4000-5000", 0);
        tableMapMap.get("graduation_salary").put("5000-6000", 0);
        tableMapMap.get("graduation_salary").put("6000-7000", 0);
        tableMapMap.get("graduation_salary").put("7000-8000", 0);
        tableMapMap.get("graduation_salary").put("8000-9000", 0);
        tableMapMap.get("graduation_salary").put("9000-10000", 0);
        tableMapMap.get("graduation_salary").put("10000-11000", 0);
        tableMapMap.get("graduation_salary").put("11000-12000", 0);
        tableMapMap.get("graduation_salary").put("12000-13000", 0);
        tableMapMap.get("graduation_salary").put("13000-14000", 0);
        tableMapMap.get("graduation_salary").put("14000-15000", 0);
        tableMapMap.get("graduation_salary").put("15000-16000", 0);
        tableMapMap.get("graduation_salary").put("16000-17000", 0);
        tableMapMap.get("graduation_salary").put("17000-18000", 0);
        tableMapMap.get("graduation_salary").put("18000-19000", 0);
        tableMapMap.get("graduation_salary").put("19000-20000", 0);
        tableMapMap.get("graduation_salary").put(">20000", 0);

        return tableMapMap;
    }

    private Map<String, Map<String, Integer>> toResult(Map<String, Map<String, Integer>> mapMap, StudentAndEmploy studentAndEmploy) {
        if ("专科".equals(studentAndEmploy.getEducationBackground())) {
            mapMap.get(GRADUATION_EDUCATION_RATIO).put(ZHUANKE, mapMap.get(GRADUATION_EDUCATION_RATIO).get(ZHUANKE) + 1);
        }
        if ("本科".equals(studentAndEmploy.getEducationBackground())) {
            mapMap.get(GRADUATION_EDUCATION_RATIO).put(BENKE, mapMap.get(GRADUATION_EDUCATION_RATIO).get(BENKE) + 1);
        }
        if ("硕士".equals(studentAndEmploy.getEducationBackground())) {
            mapMap.get(GRADUATION_EDUCATION_RATIO).put(SHUOSHI, mapMap.get(GRADUATION_EDUCATION_RATIO).get(SHUOSHI) + 1);
        }
        if ("博士".equals(studentAndEmploy.getEducationBackground())) {
            mapMap.get(GRADUATION_EDUCATION_RATIO).put(BOSHI, mapMap.get(GRADUATION_EDUCATION_RATIO).get(BOSHI) + 1);
        }
        if ("博士后".equals(studentAndEmploy.getEducationBackground())) {
            mapMap.get(GRADUATION_EDUCATION_RATIO).put(BOSHIHOU, mapMap.get(GRADUATION_EDUCATION_RATIO).get(BOSHIHOU) + 1);
        }

        if (studentAndEmploy.getSex() != null && 1 == studentAndEmploy.getSex()) {
            mapMap.get(GRADUATION_SEX_RATIO).put(NAN, mapMap.get(GRADUATION_SEX_RATIO).get(NAN) + 1);
        }
        if (studentAndEmploy.getSex() != null && 0 == studentAndEmploy.getSex()) {
            mapMap.get(GRADUATION_SEX_RATIO).put(NV, mapMap.get(GRADUATION_SEX_RATIO).get(NV) + 1);
        }

        if (StrUtil.isNotBlank(studentAndEmploy.getCompanyFullName())) {
            // 说明已就业
            mapMap.get(GRADUATION_EMPLOY_RATIO).put(YIJIUYE, mapMap.get(GRADUATION_EMPLOY_RATIO).get(YIJIUYE) + 1);
        } else {
            // 说明未就业
            mapMap.get(GRADUATION_EMPLOY_RATIO).put(WEIJIUYE, mapMap.get(GRADUATION_EMPLOY_RATIO).get(WEIJIUYE) + 1);
        }

        if ("code1".equals(studentAndEmploy.getIndustryType())) {
            mapMap.get(GRADUATION_INDUSTRY).put(CODE1, mapMap.get(GRADUATION_INDUSTRY).get(CODE1) + 1);
        }
        if ("code10".equals(studentAndEmploy.getIndustryType())) {
            mapMap.get(GRADUATION_INDUSTRY).put(CODE10, mapMap.get(GRADUATION_INDUSTRY).get(CODE10) + 1);
        }
        if ("code11".equals(studentAndEmploy.getIndustryType())) {
            mapMap.get(GRADUATION_INDUSTRY).put(CODE11, mapMap.get(GRADUATION_INDUSTRY).get(CODE11) + 1);
        }
        if ("code12".equals(studentAndEmploy.getIndustryType())) {
            mapMap.get(GRADUATION_INDUSTRY).put(CODE12, mapMap.get(GRADUATION_INDUSTRY).get(CODE12) + 1);
        }
        if ("code13".equals(studentAndEmploy.getIndustryType())) {
            mapMap.get(GRADUATION_INDUSTRY).put(CODE13, mapMap.get(GRADUATION_INDUSTRY).get(CODE13) + 1);
        }
        if ("code14".equals(studentAndEmploy.getIndustryType())) {
            mapMap.get(GRADUATION_INDUSTRY).put(CODE14, mapMap.get(GRADUATION_INDUSTRY).get(CODE14) + 1);
        }
        if ("code15".equals(studentAndEmploy.getIndustryType())) {
            mapMap.get(GRADUATION_INDUSTRY).put(CODE15, mapMap.get(GRADUATION_INDUSTRY).get(CODE15) + 1);
        }
        if ("code16".equals(studentAndEmploy.getIndustryType())) {
            mapMap.get(GRADUATION_INDUSTRY).put(CODE16, mapMap.get(GRADUATION_INDUSTRY).get(CODE16) + 1);
        }
        if ("code17".equals(studentAndEmploy.getIndustryType())) {
            mapMap.get(GRADUATION_INDUSTRY).put(CODE17, mapMap.get(GRADUATION_INDUSTRY).get(CODE17) + 1);
        }
        if ("code18".equals(studentAndEmploy.getIndustryType())) {
            mapMap.get(GRADUATION_INDUSTRY).put(CODE18, mapMap.get(GRADUATION_INDUSTRY).get(CODE18) + 1);
        }
        if ("code19".equals(studentAndEmploy.getIndustryType())) {
            mapMap.get(GRADUATION_INDUSTRY).put(CODE19, mapMap.get(GRADUATION_INDUSTRY).get(CODE19) + 1);
        }
        if ("code2".equals(studentAndEmploy.getIndustryType())) {
            mapMap.get(GRADUATION_INDUSTRY).put(CODE2, mapMap.get(GRADUATION_INDUSTRY).get(CODE2) + 1);
        }
        if ("code20".equals(studentAndEmploy.getIndustryType())) {
            mapMap.get(GRADUATION_INDUSTRY).put(CODE20, mapMap.get(GRADUATION_INDUSTRY).get(CODE20) + 1);
        }
        if ("code3".equals(studentAndEmploy.getIndustryType())) {
            mapMap.get(GRADUATION_INDUSTRY).put(CODE3, mapMap.get(GRADUATION_INDUSTRY).get(CODE3) + 1);
        }
        if ("code4".equals(studentAndEmploy.getIndustryType())) {
            mapMap.get(GRADUATION_INDUSTRY).put(CODE4, mapMap.get(GRADUATION_INDUSTRY).get(CODE4) + 1);
        }
        if ("code5".equals(studentAndEmploy.getIndustryType())) {
            mapMap.get(GRADUATION_INDUSTRY).put(CODE5, mapMap.get(GRADUATION_INDUSTRY).get(CODE5) + 1);
        }
        if ("code6".equals(studentAndEmploy.getIndustryType())) {
            mapMap.get(GRADUATION_INDUSTRY).put(CODE6, mapMap.get(GRADUATION_INDUSTRY).get(CODE6) + 1);
        }
        if ("code7".equals(studentAndEmploy.getIndustryType())) {
            mapMap.get(GRADUATION_INDUSTRY).put(CODE7, mapMap.get(GRADUATION_INDUSTRY).get(CODE7) + 1);
        }
        if ("code8".equals(studentAndEmploy.getIndustryType())) {
            mapMap.get(GRADUATION_INDUSTRY).put(CODE8, mapMap.get(GRADUATION_INDUSTRY).get(CODE8) + 1);
        }
        if ("code9".equals(studentAndEmploy.getIndustryType())) {
            mapMap.get(GRADUATION_INDUSTRY).put(CODE9, mapMap.get(GRADUATION_INDUSTRY).get(CODE9) + 1);
        }

        if ("force_unit".equals(studentAndEmploy.getCompanyNatureCode())) {
            mapMap.get(GRADUATION_NATURE).put(force_unit, mapMap.get(GRADUATION_NATURE).get(force_unit) + 1);
        }
        if ("higher_education_unit".equals(studentAndEmploy.getCompanyNatureCode())) {
            mapMap.get(GRADUATION_NATURE).put(higher_education_unit, mapMap.get(GRADUATION_NATURE).get(higher_education_unit) + 1);
        }
        if ("medical_and_health_unit".equals(studentAndEmploy.getCompanyNatureCode())) {
            mapMap.get(GRADUATION_NATURE).put(medical_and_health_unit, mapMap.get(GRADUATION_NATURE).get(medical_and_health_unit) + 1);
        }
        if ("middle_and_primary_education_unit".equals(studentAndEmploy.getCompanyNatureCode())) {
            mapMap.get(GRADUATION_NATURE).put(middle_and_primary_education_unit, mapMap.get(GRADUATION_NATURE).get(middle_and_primary_education_unit) + 1);
        }
        if ("other_institutions".equals(studentAndEmploy.getCompanyNatureCode())) {
            mapMap.get(GRADUATION_NATURE).put(other_institutions, mapMap.get(GRADUATION_NATURE).get(other_institutions) + 1);
        }
        if ("party_and_government_organs".equals(studentAndEmploy.getCompanyNatureCode())) {
            mapMap.get(GRADUATION_NATURE).put(party_and_government_organs, mapMap.get(GRADUATION_NATURE).get(party_and_government_organs) + 1);
        }
        if ("private_enterprise".equals(studentAndEmploy.getCompanyNatureCode())) {
            mapMap.get(GRADUATION_NATURE).put(private_enterprise, mapMap.get(GRADUATION_NATURE).get(private_enterprise) + 1);
        }
        if ("scientific_and_design_institute".equals(studentAndEmploy.getCompanyNatureCode())) {
            mapMap.get(GRADUATION_NATURE).put(scientific_and_design_institute, mapMap.get(GRADUATION_NATURE).get(scientific_and_design_institute) + 1);
        }
        if ("state_owned_enterprise".equals(studentAndEmploy.getCompanyNatureCode())) {
            mapMap.get(GRADUATION_NATURE).put(state_owned_enterprise, mapMap.get(GRADUATION_NATURE).get(state_owned_enterprise) + 1);
        }
        if ("three_funded_enterprise".equals(studentAndEmploy.getCompanyNatureCode())) {
            mapMap.get(GRADUATION_NATURE).put(three_funded_enterprise, mapMap.get(GRADUATION_NATURE).get(three_funded_enterprise) + 1);
        }


        if ("11".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE11, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE11) + 1);
        }
        if ("12".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE12, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE12) + 1);
        }
        if ("13".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE13, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE13) + 1);
        }
        if ("14".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE14, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE14) + 1);
        }
        if ("15".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE15, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE15) + 1);
        }
        if ("21".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE21, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE21) + 1);
        }
        if ("22".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE22, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE22) + 1);
        }
        if ("23".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE23, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE23) + 1);
        }
        if ("31".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE31, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE31) + 1);
        }
        if ("32".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE32, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE32) + 1);
        }
        if ("33".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE33, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE33) + 1);
        }
        if ("34".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE34, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE34) + 1);
        }
        if ("35".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE35, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE35) + 1);
        }
        if ("36".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE36, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE36) + 1);
        }
        if ("37".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE37, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE37) + 1);
        }
        if ("41".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE41, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE41) + 1);
        }
        if ("42".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE42, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE42) + 1);
        }
        if ("43".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE43, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE43) + 1);
        }
        if ("44".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE44, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE44) + 1);
        }
        if ("45".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE45, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE45) + 1);
        }
        if ("46".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE46, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE46) + 1);
        }
        if ("50".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE50, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE50) + 1);
        }
        if ("51".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE51, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE51) + 1);
        }
        if ("52".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE52, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE52) + 1);
        }
        if ("53".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE53, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE53) + 1);
        }
        if ("54".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE54, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE54) + 1);
        }
        if ("61".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE61, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE61) + 1);
        }
        if ("62".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE62, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE62) + 1);
        }
        if ("63".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE63, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE63) + 1);
        }
        if ("64".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE64, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE64) + 1);
        }
        if ("65".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE65, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE65) + 1);
        }
        if ("71".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE71, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE71) + 1);
        }
        if ("81".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE81, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE81) + 1);
        }
        if ("82".equals(studentAndEmploy.getCompanyProvince())) {
            mapMap.get(GRADUATION_PROVINCE).put(PROVINCE82, mapMap.get(GRADUATION_PROVINCE).get(PROVINCE82) + 1);
        }

        if ("enter_higher_school".equals(studentAndEmploy.getEmployMethod())) {
            mapMap.get(GRADUATION_EMPLOY_METHOD).put(enter_higher_school, mapMap.get(GRADUATION_EMPLOY_METHOD).get(enter_higher_school) + 1);
        }
        if ("go_abroad".equals(studentAndEmploy.getEmployMethod())) {
            mapMap.get(GRADUATION_EMPLOY_METHOD).put(go_abroad, mapMap.get(GRADUATION_EMPLOY_METHOD).get(go_abroad) + 1);
        }
        if ("join_army".equals(studentAndEmploy.getEmployMethod())) {
            mapMap.get(GRADUATION_EMPLOY_METHOD).put(join_army, mapMap.get(GRADUATION_EMPLOY_METHOD).get(join_army) + 1);
        }
        if ("self_employed".equals(studentAndEmploy.getEmployMethod())) {
            mapMap.get(GRADUATION_EMPLOY_METHOD).put(self_employed, mapMap.get(GRADUATION_EMPLOY_METHOD).get(self_employed) + 1);
        }
        if ("signing_employment_agreement".equals(studentAndEmploy.getEmployMethod())) {
            mapMap.get(GRADUATION_EMPLOY_METHOD).put(signing_employment_agreement, mapMap.get(GRADUATION_EMPLOY_METHOD).get(signing_employment_agreement) + 1);
        }
        if ("sign_contract".equals(studentAndEmploy.getEmployMethod())) {
            mapMap.get(GRADUATION_EMPLOY_METHOD).put(sign_contract, mapMap.get(GRADUATION_EMPLOY_METHOD).get(sign_contract) + 1);
        }

        if (studentAndEmploy.getSalary() != null) {
            if (studentAndEmploy.getSalary() >= 0 && studentAndEmploy.getSalary() <3000) {
                mapMap.get(GRADUATION_SALARY).put(S0_3000, mapMap.get(GRADUATION_SALARY).get(S0_3000) + 1);
            }
            if (studentAndEmploy.getSalary() >= 3000 && studentAndEmploy.getSalary() <4000) {
                mapMap.get(GRADUATION_SALARY).put(S3000_4000, mapMap.get(GRADUATION_SALARY).get(S3000_4000) + 1);
            }
            if (studentAndEmploy.getSalary() >= 4000 && studentAndEmploy.getSalary() <5000) {
                mapMap.get(GRADUATION_SALARY).put(S4000_5000, mapMap.get(GRADUATION_SALARY).get(S4000_5000) + 1);
            }
            if (studentAndEmploy.getSalary() >= 5000 && studentAndEmploy.getSalary() <6000) {
                mapMap.get(GRADUATION_SALARY).put(S5000_6000, mapMap.get(GRADUATION_SALARY).get(S5000_6000) + 1);
            }
            if (studentAndEmploy.getSalary() >= 6000 && studentAndEmploy.getSalary() <7000) {
                mapMap.get(GRADUATION_SALARY).put(S6000_7000, mapMap.get(GRADUATION_SALARY).get(S6000_7000) + 1);
            }
            if (studentAndEmploy.getSalary() >= 7000 && studentAndEmploy.getSalary() <8000) {
                mapMap.get(GRADUATION_SALARY).put(S7000_8000, mapMap.get(GRADUATION_SALARY).get(S7000_8000) + 1);
            }
            if (studentAndEmploy.getSalary() >= 8000 && studentAndEmploy.getSalary() <9000) {
                mapMap.get(GRADUATION_SALARY).put(S8000_9000, mapMap.get(GRADUATION_SALARY).get(S8000_9000) + 1);
            }
            if (studentAndEmploy.getSalary() >= 9000 && studentAndEmploy.getSalary() <10000) {
                mapMap.get(GRADUATION_SALARY).put(S9000_10000, mapMap.get(GRADUATION_SALARY).get(S9000_10000) + 1);
            }
            if (studentAndEmploy.getSalary() >= 10000 && studentAndEmploy.getSalary() <11000) {
                mapMap.get(GRADUATION_SALARY).put(S10000_11000, mapMap.get(GRADUATION_SALARY).get(S10000_11000) + 1);
            }
            if (studentAndEmploy.getSalary() >= 11000 && studentAndEmploy.getSalary() <12000) {
                mapMap.get(GRADUATION_SALARY).put(S11000_12000, mapMap.get(GRADUATION_SALARY).get(S11000_12000) + 1);
            }
            if (studentAndEmploy.getSalary() >= 12000 && studentAndEmploy.getSalary() <13000) {
                mapMap.get(GRADUATION_SALARY).put(S12000_13000, mapMap.get(GRADUATION_SALARY).get(S12000_13000) + 1);
            }
            if (studentAndEmploy.getSalary() >= 13000 && studentAndEmploy.getSalary() <14000) {
                mapMap.get(GRADUATION_SALARY).put(S13000_14000, mapMap.get(GRADUATION_SALARY).get(S13000_14000) + 1);
            }
            if (studentAndEmploy.getSalary() >= 14000 && studentAndEmploy.getSalary() <15000) {
                mapMap.get(GRADUATION_SALARY).put(S14000_15000, mapMap.get(GRADUATION_SALARY).get(S14000_15000) + 1);
            }
            if (studentAndEmploy.getSalary() >= 15000 && studentAndEmploy.getSalary() <16000) {
                mapMap.get(GRADUATION_SALARY).put(S15000_16000, mapMap.get(GRADUATION_SALARY).get(S15000_16000) + 1);
            }
            if (studentAndEmploy.getSalary() >= 16000 && studentAndEmploy.getSalary() <17000) {
                mapMap.get(GRADUATION_SALARY).put(S16000_17000, mapMap.get(GRADUATION_SALARY).get(S16000_17000) + 1);
            }
            if (studentAndEmploy.getSalary() >= 17000 && studentAndEmploy.getSalary() <18000) {
                mapMap.get(GRADUATION_SALARY).put(S17000_18000, mapMap.get(GRADUATION_SALARY).get(S17000_18000) + 1);
            }
            if (studentAndEmploy.getSalary() >= 18000 && studentAndEmploy.getSalary() <19000) {
                mapMap.get(GRADUATION_SALARY).put(S18000_19000, mapMap.get(GRADUATION_SALARY).get(S18000_19000) + 1);
            }
            if (studentAndEmploy.getSalary() >= 19000 && studentAndEmploy.getSalary() <20000) {
                mapMap.get(GRADUATION_SALARY).put(S19000_20000, mapMap.get(GRADUATION_SALARY).get(S19000_20000) + 1);
            }
            if (studentAndEmploy.getSalary() >= 20000) {
                mapMap.get(GRADUATION_SALARY).put(SL20000, mapMap.get(GRADUATION_SALARY).get(SL20000) + 1);
            }
        }



        return mapMap;
    }
}

