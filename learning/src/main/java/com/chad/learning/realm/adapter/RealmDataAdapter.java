package com.chad.learning.realm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.learning.R;
import com.chad.learning.realm.bean.Person;

import io.realm.RealmResults;

public class RealmDataAdapter extends RecyclerView.Adapter<RealmDataAdapter.DataViewHolder> {

    private Context mContext;
    private RealmResults<Person> mPersons = null;

    private OnItemClickListener mOnItemClickListener = null;
    private OnItemLongClickListener mOnItemLongClickListener = null;

    public RealmDataAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.realm_data_item, parent, false);
        DataViewHolder dataViewHolder = new DataViewHolder(view);
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, final int position) {
        if (holder == null || mPersons == null) {
            return;
        }
        final Person person = mPersons.get(position);
        holder.mTextName.setText(person.getName());
        holder.mTextAge.setText(person.getAge());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position, person);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    mOnItemLongClickListener.onItemLongClick(position, person);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mPersons == null) {
            return 0;
        }
        return mPersons.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        if (listener == null) {
            return;
        }
        mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        if (listener == null) {
            return;
        }
        mOnItemLongClickListener = listener;
    }

    public void setPersons(RealmResults<Person> persons) {
        if (persons == null) {
            return;
        }
        mPersons = persons;
        notifyDataSetChanged();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView mTextName;
        private AppCompatTextView mTextAge;

        public DataViewHolder(View itemView) {
            super(itemView);
            mTextName = itemView.findViewById(R.id.text_name);
            mTextAge = itemView.findViewById(R.id.text_age);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, Person person);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(int position, Person person);
    }
}
