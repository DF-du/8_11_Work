package com.dlf.a8_11_work.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dlf.a8_11_work.R;
import com.dlf.a8_11_work.adapter.RlvAdapter;
import com.dlf.a8_11_work.bean.NoteBean;
import com.dlf.a8_11_work.contract.IContract;
import com.dlf.a8_11_work.presenter.IPresenter;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements IContract.View {

    private Toolbar mToolbarSearch;
    private EditText mContentEt;
    private RecyclerView mRlvSearch;
    private RlvAdapter adapter;
    private IPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serch);
        initView();
        initData();
    }

    private void initData() {
        presenter = new IPresenter(this);
    }

    private void initView() {
        mToolbarSearch = (Toolbar) findViewById(R.id.search_toolbar);
        mContentEt = (EditText) findViewById(R.id.et_content);
        mRlvSearch = (RecyclerView) findViewById(R.id.search_rlv);
        mToolbarSearch.setTitle("搜索笔记");
        setSupportActionBar(mToolbarSearch);
        mRlvSearch.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RlvAdapter(this);
        mRlvSearch.setAdapter(adapter);
        mContentEt = (EditText) findViewById(R.id.et_content);
    }

    private void submit() {
        // validate
        String content = mContentEt.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "请输入搜索内容", Toast.LENGTH_SHORT).show();
            return;
        }

        NoteBean noteBean = new NoteBean();
        noteBean.setTitle(content);
        presenter.getNoteData("like", noteBean);

        // TODO validate success, do something


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                submit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void noteSelect(List<NoteBean> noteBeans) {

    }

    @Override
    public void noteLikeSelect(List<NoteBean> noteBeans) {
        adapter.addData(noteBeans);
    }

    @Override
    public void noteInsert(String msg) {

    }
}
