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

import com.dlf.a8_11_work.R;
import com.dlf.a8_11_work.bean.NoteBean;
import com.dlf.a8_11_work.contract.IContract;
import com.dlf.a8_11_work.presenter.IPresenter;

import java.util.List;

public class CompileActivity extends AppCompatActivity implements IContract.View {

    private Toolbar mCompileToolbar;
    private EditText mEtDesc;
    private EditText mEtTitle;
    private IPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compile);
        initView();
        initData();
    }

    private void initData() {
        presenter = new IPresenter(this);
    }

    private void initView() {
        mCompileToolbar = (Toolbar) findViewById(R.id.compile_toolbar);
        mEtDesc = (EditText) findViewById(R.id.et_desc);
        mEtTitle = (EditText) findViewById(R.id.et_title);
        mCompileToolbar.setTitle("编辑笔记");
        setSupportActionBar(mCompileToolbar);
    }

    private void submit() {
        // validate
        String desc = mEtDesc.getText().toString().trim();
        if (TextUtils.isEmpty(desc)) {
            Toast.makeText(this, "笔记内容", Toast.LENGTH_SHORT).show();
            return;
        }

        String title = mEtTitle.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            Toast.makeText(this, "笔记标签", Toast.LENGTH_SHORT).show();
            return;
        }
        NoteBean noteBean = new NoteBean();
        noteBean.setDesc(desc);
        noteBean.setTitle(title);
        presenter.getNoteData("insert", noteBean);
        finish();
        // TODO validate success, do something
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.compile,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.compile:
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

    }

    @Override
    public void noteInsert(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
