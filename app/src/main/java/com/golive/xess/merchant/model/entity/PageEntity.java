package com.golive.xess.merchant.model.entity;

import java.util.List;

/**
 * Created by YangChun .
 * on 2017/3/13.
 */

public class PageEntity<T> {
    /**
     * code : 0
     * msg : success
     * data : {"pageSize":10,"pageNo":0,"orders":[]}
     */

    private String code;
    private String msg;
    private DataBean data;
    /**
     * other : {"num":8,"amount":1800,"singular":8}
     */

    private OtherBean other;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public OtherBean getOther() {
        return other;
    }

    public void setOther(OtherBean other) {
        this.other = other;
    }

    public static class DataBean {
        /**
         * pageSize : 10
         * pageNo : 0
         * orders : []
         */

        private int pageSize;
        private int pageNo;
        private List<?> orders;
        private List<?> accountings;

        public List<?> getAccountings() {
            return accountings;
        }

        public void setAccountings(List<?> accountings) {
            this.accountings = accountings;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public List<?> getOrders() {
            return orders;
        }

        public void setOrders(List<?> orders) {
            this.orders = orders;
        }
    }

    public static class OtherBean {
        /**
         * num : 8
         * amount : 1800.0
         * singular : 8
         */

        private int num;
        private double amount;
        private int singular;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public int getSingular() {
            return singular;
        }

        public void setSingular(int singular) {
            this.singular = singular;
        }
    }
}
