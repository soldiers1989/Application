package com.chad.learning.realm.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.learning.R;
import com.chad.learning.parent.base.BaseAppCompatActivity;
import com.chad.learning.realm.adapter.RealmDataAdapter;
import com.chad.learning.realm.bean.Person;
import com.chad.learning.realm.interfaces.OnItemClickListener;
import com.chad.learning.realm.interfaces.OnItemLongClickListener;

import java.util.UUID;

import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmActivity extends BaseAppCompatActivity
        implements View.OnClickListener, OnItemClickListener, OnItemLongClickListener {

    @BindView(R.id.edit_name)
    AppCompatEditText mEditName;
    @BindView(R.id.edit_age)
    AppCompatEditText mEditAge;
    @BindView(R.id.btn_add)
    AppCompatButton mBtnAdd;
    @BindView(R.id.btn_modify)
    AppCompatButton mBtnModify;
    @BindView(R.id.btn_delete_all)
    AppCompatButton mBtnDeleteAll;
    @BindView(R.id.recycler_data)
    RecyclerView mDataRecycler;

    private Realm mRealm = null;
    private LinearLayoutManager mLinearLayoutManager = null;
    private RealmDataAdapter mRealmDataAdapter = null;
    private RealmResults<Person> mPersons = null;

    private int mModifyIndex = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取默认的Realm
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_realm;
    }

    @Override
    public void initViews() {
        mBtnAdd.setOnClickListener(this);
        mBtnModify.setOnClickListener(this);
        mBtnDeleteAll.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRealmDataAdapter = new RealmDataAdapter(getApplicationContext());
        mRealmDataAdapter.setOnItemClickListener(this);
        mRealmDataAdapter.setOnItemLongClickListener(this);
        mDataRecycler.setLayoutManager(mLinearLayoutManager);
        mDataRecycler.setAdapter(mRealmDataAdapter);
        findAllFromRealm();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                onAddClick();
                break;
            case R.id.btn_modify:
                onModifyClick();
                break;
            case R.id.btn_delete_all:
                onDeleteAllClick();
                break;
            default:
                break;
        }
    }

    private void onAddClick() {
        addToRealm(mEditName.getText().toString(), mEditAge.getText().toString());
        findAllFromRealm();
        clearEditText();
    }

    private void onModifyClick() {
        modifyToRealm(mEditName.getText().toString(), mEditAge.getText().toString());
        findAllFromRealm();
        clearEditText();
    }

    private void onDeleteAllClick() {
        deleteFromRealm();
        findAllFromRealm();
        clearEditText();
    }

    /**
     * 向Realm添加Person
     * @param name
     * @param age
     */
    private void addToRealm(String name, String age) {
        if (mRealm == null) {
            return;
        }
        mRealm.beginTransaction(); // 事物开始
        Person person = mRealm.createObject(Person.class, UUID.randomUUID().toString()); // 创建Person
        person.setName(name);
        person.setAge(age);
        mRealm.commitTransaction(); // 事物提交
    }

    /**
     * 修改Realm中的某一Person
     * @param name
     * @param age
     */
    private void modifyToRealm(final String name, final String age) {
        if (mRealm == null || mPersons == null || mModifyIndex < 0) {
            return;
        }
        /**
         * 创建一个写的事物执行写入
         */
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Person person = mPersons.get(mModifyIndex);
                person.setName(name);
                person.setAge(age);
            }
        });
    }

    /**
     * 从Realm查询所有Person
     */
    private void findAllFromRealm() {
        if (mRealm == null || mRealmDataAdapter == null) {
            return;
        }
        mPersons = mRealm.where(Person.class).findAll();
        mRealmDataAdapter.setPersons(mPersons);
    }

    /**
     * 删除Realm中的某一Person
     * @param position
     */
    private void deleteFromRealm(final int position) {
        if (mRealm == null || mPersons == null) {
            return;
        }
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mPersons.get(position).deleteFromRealm();
            }
        });
    }

    /**
     * 删除Realm中的全部Person
     */
    private void deleteFromRealm() {
        if (mRealm == null || mPersons == null) {
            return;
        }
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mPersons.deleteAllFromRealm();
            }
        });
    }

    private void clearEditText() {
        mEditName.getText().clear();
        mEditAge.getText().clear();
    }

    @Override
    public void onItemClick(int position, Person person) {
        mModifyIndex = position;
        mEditName.setText(person.getName());
        mEditAge.setText(person.getAge());
    }

    @Override
    public void onItemLongClick(int position, Person person) {
        deleteFromRealm(position);
        findAllFromRealm();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRealm != null) {
            if (!mRealm.isClosed()) {
                mRealm.close();
            }
            mRealm = null;
        }
    }
}
