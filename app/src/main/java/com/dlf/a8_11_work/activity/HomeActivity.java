package com.dlf.a8_11_work.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dlf.a8_11_work.R;
import com.dlf.a8_11_work.adapter.RlvAdapter;
import com.dlf.a8_11_work.bean.NoteBean;
import com.dlf.a8_11_work.contract.IContract;
import com.dlf.a8_11_work.greenDao.GreenDaoUtil;
import com.dlf.a8_11_work.presenter.IPresenter;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements IContract.View {

    private Toolbar mToolbarHome;
    private RecyclerView mRlv;
    private RlvAdapter adapter;
    private IPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initData();
    }

    private void initData() {
        presenter = new IPresenter(this);
    }

    private void initView() {
        mToolbarHome = (Toolbar) findViewById(R.id.home_toolbar);
        mRlv = (RecyclerView) findViewById(R.id.rlv);
        mToolbarHome.setTitle("首页");
        setSupportActionBar(mToolbarHome);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RlvAdapter(this);
        mRlv.setAdapter(adapter);

        List<NoteBean> select = GreenDaoUtil.select();
        if (select != null) {
            adapter.addData(select);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.one:
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.two:
                Intent intent1 = new Intent(HomeActivity.this, CompileActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        presenter.getNoteData("select",null);
    }

    @Override
    public void noteSelect(List<NoteBean> noteBeans) {
        adapter.addData(noteBeans);
    }

    @Override
    public void noteLikeSelect(List<NoteBean> noteBeans) {

    }

    @Override
    public void noteInsert(String msg) {

    }
}
