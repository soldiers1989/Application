package com.chad.hlife.helper;

import com.chad.hlife.app.AppConstant;
import com.mob.paysdk.AliPayAPI;
import com.mob.paysdk.OnPayListener;
import com.mob.paysdk.PayOrder;
import com.mob.paysdk.PaySDK;
import com.mob.paysdk.UnionPayAPI;
import com.mob.paysdk.WXPayAPI;

public class MobPayHelper {

    private static volatile MobPayHelper mMobPayHelper = null;

    public static MobPayHelper getInstance() {
        synchronized (MobPayHelper.class) {
            if (mMobPayHelper == null) {
                mMobPayHelper = new MobPayHelper();
            }
        }
        return mMobPayHelper;
    }

    private MobPayHelper() {
    }

    public void pay(int model, String orderNo, int amount, String subject, String body,
                    OnPayListener<PayOrder> listener) {
        PayOrder payOrder = createOrder(orderNo, amount, subject, body);
        switch (model) {
            case AppConstant.PAY_MODEL_ALI:
                AliPayAPI aliPayAPI = PaySDK.createMobPayAPI(AliPayAPI.class);
                aliPayAPI.pay(payOrder, listener);
                break;
            case AppConstant.PAY_MODEL_WX:
                WXPayAPI wxPayAPI = PaySDK.createMobPayAPI(WXPayAPI.class);
                wxPayAPI.pay(payOrder, listener);
                break;
            case AppConstant.PAY_MODEL_UNION:
                UnionPayAPI unionPayAPI = PaySDK.createMobPayAPI(UnionPayAPI.class);
                unionPayAPI.pay(payOrder, listener);
                break;
            default:
                break;
        }
    }

    private PayOrder createOrder(String orderNo, int amount, String subject, String body) {
        PayOrder payOrder = new PayOrder();
        payOrder.setOrderNo(orderNo);
        payOrder.setAmount(amount);
        payOrder.setSubject(subject);
        payOrder.setBody(body);
        return payOrder;
    }
}
