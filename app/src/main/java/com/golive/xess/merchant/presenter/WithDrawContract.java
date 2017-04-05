package com.golive.xess.merchant.presenter;

import com.golive.xess.merchant.model.api.body.BindCardBody;
import com.golive.xess.merchant.model.api.body.UnBindCardBody;
import com.golive.xess.merchant.model.api.body.WithdrawBody;
import com.golive.xess.merchant.model.entity.BindOrChangerCard;
import com.golive.xess.merchant.model.entity.PayEvent;
import com.golive.xess.merchant.model.entity.WalletEntity;

/**
 * Created by YangChun .
 * on 2017/3/10.
 *
 */

public interface WithDrawContract {

    /** 确认提现 **/
    int DIALOG_STATUS_AFFIRM = 0;
    /** 提现成功 **/
    int DIALOG_STATUS_WITHDRAW = 1;
    /** 绑定银行卡 **/
    int DIALOG_STATUS_CARD = 2;
    /** 银行卡 确认 **/
    int DIALOG_STATUS_CARD_AFFIRM = 3;
    /** 提现失败 **/
    int DIALOG_STATUS_WITHDRAW_FAILED = 4;
    /** 更换定银行卡 **/
    int DIALOG_STATUS_CHANGE_CARD = 5;
    /** 提示 **/
    int DIALOG_STATUS_REMINDER = -1;

    interface  Presenter{
        void withDraw(WithdrawBody data);
        void bindCard(BindCardBody data,String commission);
        void changeCard(BindCardBody data,String commission);
    }
    interface  View{
        void showOnFailure(Throwable throwable, int type);
        void successWithDraw(PayEvent payEvent ,String withdrawKidney, String leftOrRight);
        void successBindOrChangeCard(BindOrChangerCard data,String commission);
    }
}
