package com.chad.hlife.ui.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.chad.hlife.R;
import com.chad.hlife.entity.mob.CarDetailInfo;

import java.util.List;

public class CarDetailAdapter extends BaseExpandableListAdapter {

    private String[] groups;
    private List<List<CarDetailInfo.Config>> childs;

    private Context mContext;

    public CarDetailAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getGroupCount() {
        return groups == null ? 0 : groups.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childs == null ? 0 : childs.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups == null ? null : groups[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childs == null ? null : childs.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_car_detail_group, parent, false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.textTitle = convertView.findViewById(R.id.text_title);
            groupViewHolder.imageArrow = convertView.findViewById(R.id.image_arrow);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.textTitle.setText(groups[groupPosition]);
        if (isExpanded) {
            groupViewHolder.imageArrow.setImageResource(R.drawable.ic_down_arrow);
        } else {
            groupViewHolder.imageArrow.setImageResource(R.drawable.ic_right_arrow);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_car_detail_child, parent, false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.textName = convertView.findViewById(R.id.text_name);
            childViewHolder.textValue = convertView.findViewById(R.id.text_value);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        CarDetailInfo.Config config = childs.get(groupPosition).get(childPosition);
        childViewHolder.textName.setText(config.getName());
        childViewHolder.textValue.setText(config.getValue());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void setGroupData(String[] groups) {
        this.groups = groups;
        notifyDataSetChanged();
    }

    public void setChildData(List<List<CarDetailInfo.Config>> childs) {
        this.childs = childs;
        notifyDataSetChanged();
    }

    public static class GroupViewHolder {
        AppCompatTextView textTitle;
        AppCompatImageView imageArrow;
    }

    public static class ChildViewHolder {
        AppCompatTextView textName;
        AppCompatTextView textValue;
    }
}
