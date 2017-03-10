package com.golive.xess.merchant.presenter;

/**
 * Created by YangChun .
 * on 2017/3/10.
 * 投注记录
 */

public interface BetContract {
    interface  Presenter{
        void query(String arr[]);
        void statement();
        void batchPay();
        void detail();
    }
    interface  View{
        void showOnFailure(Throwable throwable,int type);
        void successQuery();
    }
}
