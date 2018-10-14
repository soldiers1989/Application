package com.chad.hlife.ui.view.theme;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.hlife.R;
import com.chad.hlife.entity.zhihu.ThemesDetailInfo;
import com.chad.hlife.ui.base.BaseRecyclerViewAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;

public class EditorAdapter extends BaseRecyclerViewAdapter<ThemesDetailInfo.Editor> {

    private Context mContext;

    public EditorAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_themes_editor,
                viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThemesDetailInfo.Editor editor = data.get(position);
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.imageAvatar.setImageURI(editor.getAvatar());
        super.onBindViewHolder(holder, position);
    }

    public class ItemViewHolder extends BaseRecyclerViewAdapter.ViewHolder {

        @BindView(R.id.image_avatar)
        SimpleDraweeView imageAvatar;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
