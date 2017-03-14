package com.golive.xess.merchant.presenter;


import com.golive.xess.merchant.model.api.body.BetDetailBody;
import com.golive.xess.merchant.model.entity.OrdersEntity;

import okhttp3.RequestBody;

/**
 *  订单详情
 */
public interface BetDetailContract {
    interface Presenter {
        void getDetail(BetDetailBody data);
    }
    interface View {
        void showOnFailure(Throwable throwable);
        void successLoad(OrdersEntity ordersEntity);
    }
}
