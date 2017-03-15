package com.golive.xess.merchant.presenter;

import com.golive.xess.merchant.model.api.body.LoginBody;
import com.golive.xess.merchant.model.api.body.UserBody;
import com.golive.xess.merchant.model.entity.LoginEntity;
import com.golive.xess.merchant.model.entity.UserInfo;

import okhttp3.RequestBody;

/**
 * Created by YangChun .
 * on 2017/3/8.
 */

public interface PersonalContract {
    interface Persenter{
        void initViewData(LoginBody body);
        void upLoadPicture(String fileData,String fileSuff,String fileType);
        void submitEdit();
    }
    interface View{
        void editOnFailure(Throwable throwable);
        void successEdit();
        void showOnFailure(Throwable throwable);
        void successLoad(LoginEntity loginEntity );
        void successUpload(String string);
    }
}
