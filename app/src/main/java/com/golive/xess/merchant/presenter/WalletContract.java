package com.golive.xess.merchant.presenter;

import com.golive.xess.merchant.model.entity.WalletEntity;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

import okhttp3.RequestBody;

/**
 * Created by YangChun .
 * on 2017/3/9.
 * 钱包
 */

public interface WalletContract {
    int TYPEINFO = 0;
    int TYPELOGS = 1;
    interface Persenter{
        void getWalletInfo(RequestBody data);
        void getWalletLogs(RequestBody data);
    }
    interface View{
        void dataFailed(Throwable throwable , int type);
        void dataInfoSuccess(WalletEntity walletEntity);
        void dataLogsSuccess(List<LinkedTreeMap> walletLogEntity);
    }
}
