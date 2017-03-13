package com.golive.xess.merchant.presenter;

import com.golive.xess.merchant.model.entity.OrdersEntity;
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
        void query(RequestBody data);
        void statement();
        void batchPay();
        void detail();
    }
    interface  View{
        void showOnFailure(Throwable throwable, int type);
        void successQuery(List<LinkedTreeMap> ordersEntityList);
    }
}
