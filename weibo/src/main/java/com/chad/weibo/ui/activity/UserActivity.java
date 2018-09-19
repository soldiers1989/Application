package com.chad.weibo.ui.activity;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.weibo.R;
import com.chad.weibo.entity.User;
import com.chad.weibo.eventbus.EventMessage;
import com.chad.weibo.eventbus.EventType;
import com.chad.weibo.glide.GlideApp;
import com.chad.weibo.ui.base.BaseRxAppCompatActivity;
import com.chad.weibo.util.LogUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class UserActivity extends BaseRxAppCompatActivity {

    private static final String TAG = UserActivity.class.getSimpleName();

    @BindView(R.id.cover_image_phone)
    AppCompatImageView mCoverImagePhone;
    @BindView(R.id.user_avatar)
    SimpleDraweeView mUserAvatar;
    @BindView(R.id.user_name)
    AppCompatTextView mUserName;
    @BindView(R.id.count)
    AppCompatTextView mCount;
    @BindView(R.id.description)
    AppCompatTextView mDescription;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    protected void initViews() {
        LogUtil.d(TAG, "initViews");
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData;");
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUser(EventMessage eventMessage) {
        LogUtil.d(TAG, "onUser : eventMessage = " + (eventMessage == null ? null : "Not Null"));
        if (eventMessage == null) {
            return;
        }
        switch (eventMessage.getType()) {
            case EventType.TYPE_USER:
                handleUser((User) eventMessage.getObject());
                break;
            default:
                break;
        }
    }

    private void handleUser(User user) {
        LogUtil.d(TAG, "handleUser : user = " + (user == null ? null : "Not Null"));
        if (user == null) {
            return;
        }
        GlideApp.with(this)
                .load(user.getCover_image_phone())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mCoverImagePhone);
        mUserAvatar.setImageURI(user.getAvatar_large());
        mUserName.setText(user.getScreen_name());
        mCount.setText(getString(R.string.friends) + " " + user.getFriends_count()
                + "  |  " + getString(R.string.followers) + " " + user.getFollowers_count());
        mDescription.setText(getString(R.string.description) + user.getDescription());
    }

    @Override
    protected void onDestroy() {
        LogUtil.d(TAG, "onDestroy");
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
