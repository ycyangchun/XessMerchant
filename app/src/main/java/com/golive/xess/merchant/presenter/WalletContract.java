package com.golive.xess.merchant.presenter;

import com.golive.xess.merchant.model.api.body.WalletLogsBody;
import com.golive.xess.merchant.model.api.body.WalletBody;
import com.golive.xess.merchant.model.entity.WalletEntity;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

/**
 * Created by YangChun .
 * on 2017/3/9.
 * 钱包
 */

public interface WalletContract {
    int TYPEINFO = 0;
    int TYPELOGS = 1;
    int GAINDATA = 2;//获取数据
    int GAINMORE = 3;//加载更多
    interface Persenter{
        void getWalletInfo(WalletBody data );
        void getWalletLogs(WalletLogsBody data,int gain);
    }
    interface View{
        void dataFailed(Throwable throwable , int type ,int gain);
        void dataInfoSuccess(WalletEntity walletEntity);
        void dataLogsSuccess(List<LinkedTreeMap> walletLogEntity,int gain);
    }
}
