package com.golive.xess.merchant.model.api;


import com.golive.xess.merchant.model.api.body.AccountBody;
import com.golive.xess.merchant.model.api.body.BetBody;
import com.golive.xess.merchant.model.api.body.BetDetailBody;
import com.golive.xess.merchant.model.api.body.BindCardBody;
import com.golive.xess.merchant.model.api.body.LoginBody;
import com.golive.xess.merchant.model.api.body.ModifyBody;
import com.golive.xess.merchant.model.api.body.PayBody;
import com.golive.xess.merchant.model.api.body.ReplacePayBody;
import com.golive.xess.merchant.model.api.body.StoreBody;
import com.golive.xess.merchant.model.api.body.SyncBody;
import com.golive.xess.merchant.model.api.body.UnBindCardBody;
import com.golive.xess.merchant.model.api.body.UserBody;
import com.golive.xess.merchant.model.api.body.WalletBody;
import com.golive.xess.merchant.model.api.body.WalletLogsBody;
import com.golive.xess.merchant.model.api.body.WithdrawBody;
import com.golive.xess.merchant.model.entity.AccountEntity;
import com.golive.xess.merchant.model.entity.BindOrChangerCard;
import com.golive.xess.merchant.model.entity.CommonEntity;
import com.golive.xess.merchant.model.entity.LoginEntity;
import com.golive.xess.merchant.model.entity.MarketEntity;
import com.golive.xess.merchant.model.entity.OrdersEntity;
import com.golive.xess.merchant.model.entity.PageEntity;
import com.golive.xess.merchant.model.entity.PayEntity;
import com.golive.xess.merchant.model.entity.PayEvent;
import com.golive.xess.merchant.model.entity.SyncEntity;
import com.golive.xess.merchant.model.entity.UserInfo;
import com.golive.xess.merchant.model.entity.WalletEntity;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 *
 */
public interface ApiService {
    String SECRET_KEY = "golive_lottery@123456#$%~";
    String SECRET_VALUE = "12345678";

    /**
     *  设备信息上传
     */
    @POST("lottery/devicesAuto")
    Observable<String> devicesAuto(@Query("deviceModel") String deviceModel,
                                   @Query("versionSdk") String versionSdk,@Query("versionRelease") String versionRelease,
                                   @Query("deviceId") String deviceId,@Query("wlanMac") String wlanMac,
                                   @Query("btMac") String btMac,@Query("screenInches") String screenInches,
                                   @Query("brand") String brand,@Query("model") String model,
                                   @Query("deviceNo") String deviceNo);
    /**
     *  7.25修改商户信息(POST)
     */
    @POST("lottery/updateStoreAccountInfo")
    Observable<CommonEntity> updateStoreAccount(@Body StoreBody body);
    /**
     * 7.16商家登录(POST)
     * @param data
     * @return
     */
    @POST("lottery/storeLogin")
    Observable<CommonEntity<LoginEntity>> storeLogin(@Body LoginBody data);

    /**
     * 7.7获取用户信息(POST)
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/getUserInfo")
    Observable<CommonEntity<UserInfo>> getUserInfo(@Body UserBody data);


    /**
     * 7.8获取商家信息(POST)
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/getStoreInfo")
    Observable<CommonEntity<UserInfo>> getStoreInfo(@Body UserBody data);

     /**
     * 7.13订单列表(POST)
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/orders")
    Observable<PageEntity<List<LinkedTreeMap>>> getOrders(@Body BetBody data);

    /**
     * 7.14订单详情(POST)
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/order")
    Observable<CommonEntity<OrdersEntity>> getOrderDetail(@Body BetDetailBody data);


    /**
     * 7.14我的钱包-用户(POST)
     *  1．个人：设备编号+用户编号   deviceNo  userNo
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/myWallet")
    Observable<CommonEntity<WalletEntity>> getWallet(@Body WalletBody data);

    /**
     * 7.15我的钱包-商家(POST)
     *  2．商家：设备编号+商家编号   deviceNo  storeNo
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/store/myWallet")
    Observable<CommonEntity<WalletEntity>> getWalletStore(@Body WalletBody data);
    /**
     * 7.17钱包记录-用户(POST)
     *  1．个人：设备编号+用户编号   deviceNo  userNo
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/getWalletLogs")
    Observable<PageEntity<List<LinkedTreeMap>>> getWalletLogs(@Body WalletLogsBody data);

    /**
     * 7.18钱包记录-商家(POST)
     *  2．商家：设备编号+商家编号   deviceNo  storeNo
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/store/getWalletLogs")
    Observable<PageEntity<List<LinkedTreeMap>>> getWalletStoreLogs(@Body WalletLogsBody data);


    /**
     * 7.26商家待投注(POST)
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/batchAgentPay")
    Observable<CommonEntity<List<LinkedTreeMap>>> batchAgentPay(@Body ReplacePayBody data);

    /**
     * 7.23生成对账信息(POST)
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/dzOrders")
    Observable<CommonEntity<AccountEntity>> getAccount(@Body AccountBody data);

    /**
     * 7.19修改商家用户密码(POST)
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/mofifyPwdByStore")
    Observable<CommonEntity<LoginEntity>> modifyPwd(@Body ModifyBody data);

    /**
     * 7.32获取支付二维码(POST)
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/payCenter")
    Observable<CommonEntity<PayEntity>> payCenter(@Body PayBody data);

    /**
     * 用户同步
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/syncUserInfo")
    Observable<CommonEntity<SyncEntity>> syncUserInfo(@Body SyncBody data);


    /**
     * 绑定银行卡
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/storeAccount/bindCard")
    Observable<CommonEntity<BindOrChangerCard>> bindCard(@Body BindCardBody data);

    /**
     *  变更银行卡
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/storeAccount/changeCard")
    Observable<CommonEntity<BindOrChangerCard>> changeCard(@Body BindCardBody data);

    /**
     *  解绑银行卡
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/storeAccount/unBindCard")
    Observable<CommonEntity> unBindCard(@Body UnBindCardBody data);

    /**
     *  7.41商家佣金提现(POST)
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/storeWithdraw")
    Observable<CommonEntity<PayEvent>> storeWithdraw(@Body WithdrawBody data);

    /**
     *
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/store/CountNumAndAmountByTime")
    Observable<CommonEntity<MarketEntity>> countNumAndAmountByTime(@Body ReplacePayBody data);

    /**
     *  退出登录
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("lottery/Logout")
    Observable<CommonEntity> logout(@Body UnBindCardBody data);
}
