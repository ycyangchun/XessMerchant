package com.golive.xess.merchant.presenter;

/**
 * Created by YangChun .
 * on 2017/3/8.
 */

public interface PersonalContract {
    interface Persenter{
        void submitEdit();
    }
    interface View{
        void editOnFailure(Throwable throwable);
        void successEdit();

    }
}
