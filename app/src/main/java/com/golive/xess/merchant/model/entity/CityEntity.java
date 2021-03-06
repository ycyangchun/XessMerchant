package com.golive.xess.merchant.model.entity;

import java.util.List;

/**
 * Created by YangChun .
 * on 2017/3/18.
 */

public class CityEntity {

    /**
     * code : 110000
     * name : 北京市
     * city : [{"code":"110101 ","name":"东城区"},{"code":"110102 ","name":"西城区"},{"code":"110103 ","name":"崇文区"},{"code":"110104 ","name":"宣武区"},{"code":"110105 ","name":"朝阳区"},{"code":"110106 ","name":"丰台区"},{"code":"110107 ","name":"石景山区"},{"code":"110108 ","name":"海淀区"},{"code":"110109 ","name":"门头沟区"},{"code":"110111 ","name":"房山区"},{"code":"110112 ","name":"通州区"},{"code":"110113 ","name":"顺义区"},{"code":"110114 ","name":"昌平区"},{"code":"110115 ","name":"大兴区"},{"code":"110116 ","name":"怀柔区"},{"code":"110117 ","name":"平谷区"},{"code":"110228 ","name":"密云县"},{"code":"110229 ","name":"延庆县"}]
     */

    private String code;
    private String name;
    private List<CityBean> city;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityBean> getCity() {
        return city;
    }

    public void setCity(List<CityBean> city) {
        this.city = city;
    }

    public static class CityBean {
        /**
         * code : 110101
         * name : 东城区
         */

        private String code;
        private String name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
