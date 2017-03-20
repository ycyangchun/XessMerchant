package com.golive.xess.merchant.model.api.body;

/**
 * Created by YangChun .
 * on 2017/3/20.
 */

public class AccountBody {

    public AccountBody(String mobile) {
        this.mobile = mobile;
    }

    /**
     * mobile : 18515966636
     */


    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
