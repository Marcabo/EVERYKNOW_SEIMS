package com.herion.everyknow.seims.service.impl;

import cn.hutool.core.util.StrUtil;
import com.herion.everyknow.seims.dao.StudentDao;
import com.herion.everyknow.seims.dao.bean.StudentAndEmploy;
import com.herion.everyknow.seims.dao.entity.Student;
import com.herion.everyknow.seims.service.AnalysisService;
import com.herion.everyknow.seims.service.bean.YearAnalysisRequest;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description 数据可视化 ServiceImpl 层
 * @auther wubo25320
 * @create 2020-05-20 19:31
 */
@Service
public class AnalysisServiceImpl implements AnalysisService {

    // 毕业生总人数
    private static final String GRADUATION_TOTAL            = "graduation_total";
    // 历年就业率
    private static final String GRADUATION_EMPLOY_RATIO     = "graduation_employ_ratio";
    // 就业年薪分布
    private static final String GRADUATION_YEAR_SALARY      = "graduation_year_salary";
    // 就业方式分布
    private static final String GRADUATION_EMPLOY_TYPE      = "graduation_employ_type";

    private static final String TOTAL_NUM                   = "total_num";
    private static final String EMPLOY_NUM                  = "employ_num";

    private static final String SL4W                        = "s_l4w";
    private static final String S4_8W                       = "s4_8W";
    private static final String S8_12W                      = "s8_12w";
    private static final String S12_16W                     = "s12_16w";
    private static final String S16_20W                     = "s16_20w";
    private static final String S20_24W                     = "s20_24w";
    private static final String S24_28W                     = "s24_28w";
    private static final String SG28W                       = "s_g28w";

    private static String enter_higher_school               = "升学";
    private static String go_abroad                         = "出国";
    private static String join_army                         = "参军";
    private static String self_employed                     = "自主创业";
    private static String signing_employment_agreement      = "签订就业协议书";
    private static String sign_contract                     = "签合同";


    @Autowired
    private StudentDao studentDao;

    @Override
    public Map<String, Map<String, Object>> queryByCondition(YearAnalysisRequest request) {
        Student query = new Student();
        query.setCollegeCode(request.getCollegeCode());
        query.setDeptCode(request.getDeptCode());
        query.setClazzId(request.getClazzId());

        List<String> betweenYear = null;
        try {
            betweenYear = this.getBetweenYear(request.getStartSession(), request.getEndSession());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<StudentAndEmploy> studentAndEmployList = studentDao.getStudentAndEmploy(query, betweenYear);
        Map<String, Map<String, Object>> result = this.createResult(request.getStartSession(), request.getEndSession());

        // 将学生按年分
        Map<String, List<StudentAndEmploy>> yearStudent = new LinkedHashMap<>();
        for (String s : betweenYear) {
            yearStudent.put(s, new ArrayList<>());
        }
        studentAndEmployList.stream().forEach(student -> {
            yearStudent.get(student.getGraduationSession()).add(student);
        });


        for (String s : betweenYear) {
            // 设置 毕业生总人数
            result.get(GRADUATION_TOTAL).put(s, yearStudent.get(s).size());
            // 设置历年就业率
            Map<String, Integer> employMap = new HashMap<>();
            employMap.put(TOTAL_NUM, yearStudent.get(s).size());
            employMap.put(EMPLOY_NUM, yearStudent.get(s).stream().filter(stu -> StrUtil.isNotBlank(stu.getCompanyFullName())).collect(Collectors.toList()).size());
            result.get(GRADUATION_EMPLOY_RATIO).put(s, employMap);
            // 设置就业年薪分布
            Map<String, Integer> salaryMap = new LinkedHashMap<>();
            salaryMap.put(SL4W, yearStudent.get(s).stream().filter(stu -> stu.getSalary() != null && stu.getSalary() * 12 < 40000).collect(Collectors.toList()).size());
            salaryMap.put(S4_8W, yearStudent.get(s).stream().filter(stu -> stu.getSalary() != null && stu.getSalary() * 12 <80000 && stu.getSalary() * 12 >= 40000).collect(Collectors.toList()).size());
            salaryMap.put(S8_12W, yearStudent.get(s).stream().filter(stu -> stu.getSalary() != null && stu.getSalary() * 12 <120000 && stu.getSalary() * 12 >= 80000).collect(Collectors.toList()).size());
            salaryMap.put(S12_16W, yearStudent.get(s).stream().filter(stu -> stu.getSalary() != null && stu.getSalary() * 12 <160000 && stu.getSalary() * 12 >= 120000).collect(Collectors.toList()).size());
            salaryMap.put(S16_20W, yearStudent.get(s).stream().filter(stu -> stu.getSalary() != null && stu.getSalary() * 12 <200000 && stu.getSalary() * 12 >= 160000).collect(Collectors.toList()).size());
            salaryMap.put(S20_24W, yearStudent.get(s).stream().filter(stu -> stu.getSalary() != null && stu.getSalary() * 12 <240000 && stu.getSalary() * 12 >= 200000).collect(Collectors.toList()).size());
            salaryMap.put(S24_28W, yearStudent.get(s).stream().filter(stu -> stu.getSalary() != null && stu.getSalary() * 12 <280000 && stu.getSalary() * 12 >= 240000).collect(Collectors.toList()).size());
            salaryMap.put(SG28W, yearStudent.get(s).stream().filter(stu -> stu.getSalary() != null && stu.getSalary() * 12 >= 280000).collect(Collectors.toList()).size());
            result.get(GRADUATION_YEAR_SALARY).put(s, salaryMap);
            // 设置就业方式分布
            Map<String, Integer> employMethodMap = new LinkedHashMap<>();
            employMethodMap.put(enter_higher_school, yearStudent.get(s).stream().filter(stu -> "enter_higher_school".equals(stu.getEmployMethod())).collect(Collectors.toList()).size());
            employMethodMap.put(go_abroad, yearStudent.get(s).stream().filter(stu -> "go_abroad".equals(stu.getEmployMethod())).collect(Collectors.toList()).size());
            employMethodMap.put(join_army, yearStudent.get(s).stream().filter(stu -> "join_army".equals(stu.getEmployMethod())).collect(Collectors.toList()).size());
            employMethodMap.put(self_employed, yearStudent.get(s).stream().filter(stu -> "self_employed".equals(stu.getEmployMethod())).collect(Collectors.toList()).size());
            employMethodMap.put(signing_employment_agreement, yearStudent.get(s).stream().filter(stu -> "signing_employment_agreement".equals(stu.getEmployMethod())).collect(Collectors.toList()).size());
            employMethodMap.put(sign_contract, yearStudent.get(s).stream().filter(stu -> "sign_contract".equals(stu.getEmployMethod())).collect(Collectors.toList()).size());
            result.get(GRADUATION_EMPLOY_TYPE).put(s, employMethodMap);
        }

        return result;
    }

    private Map<String, Map<String, Object>> createResult(String startSession, String endSession) {
        Map<String, Map<String, Object>> result = new LinkedHashMap<>();

        // 放入的数据
        result.put(GRADUATION_TOTAL, new LinkedHashMap<>());
        result.put(GRADUATION_EMPLOY_RATIO, new LinkedHashMap<>());
        result.put(GRADUATION_YEAR_SALARY, new LinkedHashMap<>());
        result.put(GRADUATION_EMPLOY_TYPE, new LinkedHashMap<>());

        List<String> betweenYear = new ArrayList<>();
        try {
             betweenYear = this.getBetweenYear(startSession, endSession);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (String s : betweenYear) {
            result.get(GRADUATION_TOTAL).put(s, null);
            result.get(GRADUATION_EMPLOY_RATIO).put(s, null);
            result.get(GRADUATION_YEAR_SALARY).put(s, null);
            result.get(GRADUATION_EMPLOY_TYPE).put(s, null);
        }

        return result;
    }

    private List<String> getBetweenYear(String startYear, String endYear) throws ParseException {
        List<String> result = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date startDate = sdf.parse(startYear);
        Date endDate = sdf.parse(endYear);

        result.add(startYear);

        Calendar instance = Calendar.getInstance();
        instance.setTime(startDate);
        for (;;) {
            if (instance.getTime().before(endDate)) {
                instance.add(Calendar.YEAR, 1);
                result.add(instance.get(Calendar.YEAR) + "");
            } else {
                break;
            }
        }
        result.add(endYear);

        return result;
    }
}
