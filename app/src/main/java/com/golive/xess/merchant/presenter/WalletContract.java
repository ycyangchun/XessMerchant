package com.golive.xess.merchant.presenter;

/**
 * Created by YangChun .
 * on 2017/3/9.
 * 钱包
 */

public interface WalletContract {
    interface Persenter{
        void getWalletData();
    }
    interface View{
        void dataFailed(Throwable throwable);
        void dataSuccess();
    }
}
