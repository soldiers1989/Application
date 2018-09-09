package com.chad.zhihu.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.zhihu.R;
import com.chad.zhihu.entity.zhihu.CommentsInfo;
import com.chad.zhihu.ui.base.BaseRecyclerViewAdapter;
import com.chad.zhihu.util.DateUtil;
import com.chad.zhihu.util.LogUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;

public class CommentsAdapter extends BaseRecyclerViewAdapter<CommentsInfo.Comment> {

    private static final String TAG = CommentsAdapter.class.getSimpleName();

    private Context mContext;

    public CommentsAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        LogUtil.d(TAG, "onCreateViewHolder");
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_comments,
                viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        LogUtil.d(TAG, "onBindViewHolder : holder = " + viewHolder + " , position = " + position);
        CommentsInfo.Comment comment = data.get(position);
        if (comment == null) {
            return;
        }
        ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
        itemViewHolder.imageAvatar.setImageURI(comment.getAvatar());
        itemViewHolder.textAuthor.setText(comment.getAuthor());
        itemViewHolder.textContent.setText(comment.getContent());
        itemViewHolder.textTime.setText(DateUtil.formatTime(comment.getTime()));
        itemViewHolder.textLike.setText(String.valueOf(comment.getLikes()));
        super.onBindViewHolder(viewHolder, position);
    }

    public class ItemViewHolder extends BaseRecyclerViewAdapter.ViewHolder {

        @BindView(R.id.image_avatar)
        SimpleDraweeView imageAvatar;
        @BindView(R.id.text_author)
        AppCompatTextView textAuthor;
        @BindView(R.id.text_content)
        AppCompatTextView textContent;
        @BindView(R.id.text_time)
        AppCompatTextView textTime;
        @BindView(R.id.text_like)
        AppCompatTextView textLike;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
