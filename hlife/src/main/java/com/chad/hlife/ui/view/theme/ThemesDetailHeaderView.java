package com.chad.hlife.ui.view.theme;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.chad.hlife.R;
import com.chad.hlife.entity.zhihu.ThemesDetailInfo;
import com.chad.hlife.glide.CustomGlideModule;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThemesDetailHeaderView extends ConstraintLayout {

    @BindView(R.id.image_preview)
    AppCompatImageView mImagePreview;
    @BindView(R.id.text_description)
    AppCompatTextView mTextDescription;
    @BindView(R.id.editor_recycler)
    RecyclerView mEditorRecycler;

    private Context mContext = null;
    private LinearLayoutManager mLinearLayoutManager = null;
    private EditorAdapter mEditorAdapter = null;
    private OnEditorItemClickListener mOnEditorItemClickListener = null;

    public interface OnEditorItemClickListener {
        void onEditorItemClick(int position);
    }

    public ThemesDetailHeaderView(Context context) {
        this(context, null);
    }

    public ThemesDetailHeaderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThemesDetailHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.layout_themes_detail_header, this, true);
        ButterKnife.bind(this);
        initEditorRecycler();
    }

    private void initEditorRecycler() {
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mEditorAdapter = new EditorAdapter(mContext);
        mEditorAdapter.setOnItemClickListener(position -> {
            if (mOnEditorItemClickListener != null) {
                mOnEditorItemClickListener.onEditorItemClick(position);
            }
        });

        mEditorRecycler.setLayoutManager(mLinearLayoutManager);
        mEditorRecycler.setAdapter(mEditorAdapter);
    }

    public void setOnEditorItemClickListener(OnEditorItemClickListener listener) {
        mOnEditorItemClickListener = listener;
    }

    public void setImagePreview(String image) {
        CustomGlideModule.loadCenterCrop(mContext, image, mImagePreview);
    }

    public void setDescription(String description) {
        mTextDescription.setText(description);
    }

    public void setData(List<ThemesDetailInfo.Editor> editors) {
        mEditorAdapter.setData(editors);
    }
}
