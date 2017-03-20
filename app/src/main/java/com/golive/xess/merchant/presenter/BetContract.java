package com.golive.xess.merchant.presenter;

import com.golive.xess.merchant.model.api.body.BetBody;
import com.golive.xess.merchant.model.api.body.PayBody;
import com.golive.xess.merchant.model.entity.OrdersEntity;
import com.golive.xess.merchant.model.entity.PageEntity;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

import okhttp3.RequestBody;

/**
 * Created by YangChun .
 * on 2017/3/10.
 * 投注记录
 */

public interface BetContract {
    int TYPEORDER = 0;
    int TYPEDETAIL = 1;
    int TYPEPAY = 2;
    interface  Presenter{
        void query(BetBody data);
        void statement();
        void batchPay(PayBody payBody);
        void detail();
    }
    interface  View{
        void showOnFailure(Throwable throwable, int type);
        void successQuery(List<LinkedTreeMap> ordersEntityList , PageEntity.DataBean.OtherBean otherBean);
        void successPay(List<LinkedTreeMap> payEntityList);
    }
}
