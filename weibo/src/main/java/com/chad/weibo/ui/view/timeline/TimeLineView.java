package com.chad.weibo.ui.view.timeline;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.VideoView;

import com.chad.weibo.R;
import com.chad.weibo.util.DateUtil;
import com.chad.weibo.util.StringUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimeLineView extends ConstraintLayout implements View.OnClickListener {

    @BindView(R.id.user_avatar)
    SimpleDraweeView mUserAvatar;
    @BindView(R.id.user_name)
    AppCompatTextView mUserName;
    @BindView(R.id.create_at)
    AppCompatTextView mCreateAt;
    @BindView(R.id.source)
    AppCompatTextView mSource;
    @BindView(R.id.text)
    AppCompatTextView mText;
    @BindView(R.id.view_recycler_image)
    RecyclerView mImageRecyclerView;
    @BindView(R.id.view_video)
    VideoView mVideoView;
    @BindView(R.id.count_repost)
    AppCompatTextView mRepostCount;
    @BindView(R.id.count_comment)
    AppCompatTextView mCommentCount;
    @BindView(R.id.count_attitude)
    AppCompatTextView mAttitudeCount;
    @BindView(R.id.image_attitude)
    AppCompatImageView mAttitudeImage;

    private Context mContext;
    private OnButtonClickListener mOnButtonClickListener = null;
    private GridLayoutManager mGridLayoutManager = null;
    private ImageAdapter mImageAdapter = null;

    public interface OnButtonClickListener {
        void onRepostClick();

        void onCommentClick();

        void onAttitudeClick();
    }

    public TimeLineView(Context context) {
        this(context, null);
    }

    public TimeLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initRecyclerView();
    }

    private void initView(Context context) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.item_time_line, this, true);
        ButterKnife.bind(this);
        mVideoView.setVisibility(GONE);
    }

    private void initRecyclerView() {
        mGridLayoutManager = new GridLayoutManager(mContext, 3);
        mImageAdapter = new ImageAdapter(mContext);
        mImageRecyclerView.setLayoutManager(mGridLayoutManager);
        mImageRecyclerView.setAdapter(mImageAdapter);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            list.add(i + "");
        }
        mImageAdapter.setData(list);
    }

    public void setOnButtonClickListener(OnButtonClickListener listener) {
        mOnButtonClickListener = listener;
    }

    public void setUserAvatarUrl(String url) {
        mUserAvatar.setImageURI(url);
    }

    public void setUserName(String userName) {
        mUserName.setText(userName);
    }

    public void setCreateAt(String createAt) {
        mCreateAt.setText(DateUtil.formatTime(mContext, createAt));
    }

    public void setSourcce(String source) {
        mSource.setText(StringUtil.getWeiBoSource(source));
    }

    public void setText(String text) {
        mText.setText(text);
    }

    public void setRepostCount(int count) {
        if (count == 0) {
            mRepostCount.setText(R.string.repost);
        } else {
            mRepostCount.setText(count);
        }
    }

    public void setCommentCount(int count) {
        if (count == 0) {
            mCommentCount.setText(R.string.repost);
        } else {
            mCommentCount.setText(count);
        }
    }

    public void setAttitudeCount(int count) {
        if (count == 0) {
            mAttitudeCount.setText(R.string.repost);
        } else {
            mAttitudeCount.setText(count);
        }
    }

    public void setAttitudeImage(boolean isAttitude) {
        if (isAttitude) {
            mAttitudeImage.setImageResource(R.drawable.ic_time_line_attitude_selected);
        } else {
            mAttitudeImage.setImageResource(R.drawable.ic_time_line_attitude_normal);
        }
    }

    public void setMedia(boolean isVideo) {
        if (isVideo) {
            mImageRecyclerView.setVisibility(GONE);
            mVideoView.setVisibility(VISIBLE);
        } else {
            mImageRecyclerView.setVisibility(VISIBLE);
            mVideoView.setVisibility(GONE);
        }
    }

    @OnClick({R.id.btn_repost, R.id.btn_comment, R.id.btn_attitude})
    @Override
    public void onClick(View v) {
        if (mOnButtonClickListener == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.btn_repost:
                mOnButtonClickListener.onRepostClick();
                break;
            case R.id.btn_comment:
                mOnButtonClickListener.onCommentClick();
                break;
            case R.id.btn_attitude:
                mOnButtonClickListener.onAttitudeClick();
                break;
            default:
                break;
        }
    }
}
