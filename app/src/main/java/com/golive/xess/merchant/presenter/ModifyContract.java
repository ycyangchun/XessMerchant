package com.golive.xess.merchant.presenter;

import com.golive.xess.merchant.model.api.body.LoginBody;
import com.golive.xess.merchant.model.api.body.ModifyBody;
import com.golive.xess.merchant.model.entity.CommonEntity;
import com.golive.xess.merchant.model.entity.LoginEntity;

/**
 * Created by YangChun .
 * on 2017/3/14.
 * 修改密码
 */

public interface ModifyContract {
    interface Presenter{
        void modify(ModifyBody body);
    }
    interface  View{
        void showOnFailure(Throwable throwable);
        void successModify(LoginEntity loginEntity , String pwd);
    }
}
