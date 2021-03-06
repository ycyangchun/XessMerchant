package com.golive.xess.merchant.presenter;

import com.golive.xess.merchant.model.api.body.AccountBody;
import com.golive.xess.merchant.model.api.body.BetBody;
import com.golive.xess.merchant.model.api.body.ReplacePayBody;
import com.golive.xess.merchant.model.entity.AccountEntity;
import com.golive.xess.merchant.model.entity.MarketEntity;
import com.golive.xess.merchant.model.entity.PageEntity;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

/**
 * Created by YangChun .
 * on 2017/3/10.
 * 投注记录
 */

public interface BetContract {
    int TYPEORDER = 0;
    int TYPEDMARKET = 1;
    int TYPEPAY = 2;
    int TYPEACCOUNT = 3;
    int GAINDATA = 4;//获取数据
    int GAINMORE = 5;//加载更多
    interface  Presenter{
        void query(BetBody data,int gain);
        void account(AccountBody accountBody);
        void batchPay(ReplacePayBody payBody);
        void market(ReplacePayBody payBody);
    }
    interface  View{
        void showOnFailure(Throwable throwable, int type ,int gain);
        void successQuery(List<LinkedTreeMap> ordersEntityList , PageEntity.DataBean.OtherBean otherBean ,int gain);
        void successPay(List<LinkedTreeMap> payEntityList);
        void successAccount(AccountEntity accountEntity);
        void successMarket(MarketEntity marketEntity);
        void loadProgress();
        void hideProgress();
    }
}
