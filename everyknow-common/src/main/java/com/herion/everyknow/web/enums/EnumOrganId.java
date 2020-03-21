package com.herion.everyknow.web.enums;

/**
 * @Description 机构号
 * @auther wubo25320
 * @create 2020-03-18 22:41
 */
public enum EnumOrganId {
    NCU("NCU","南昌大学"),
    BJU("BJU","北京大学"),
    QHU("QHU","清华大学");

    String id;
    String name;

    EnumOrganId(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
