package com.dlf.two;

import android.Manifest;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.dlf.two.adapter.RlvAdapter;
import com.dlf.two.bean.ImgBean;
import com.dlf.two.contract.IContract;
import com.dlf.two.contract.MyContract;
import com.dlf.two.presenter.IPresenter;
import com.dlf.two.presenter.MyPresenter;
import com.google.android.material.navigation.NavigationView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements IContract.View, MyContract.View {

    private Toolbar mToolbar;
    private RecyclerView mRlv;
    private LinearLayout mLl;
    private NavigationView mNv;
    private DrawerLayout mDrawer;
    private ImageView header;
    private RlvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] arr = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this, arr, 100);
        initView();
        initData();
    }

    private void initData() {
        MyPresenter presenter = new MyPresenter(this);
        presenter.loadUpUIData();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRlv = (RecyclerView) findViewById(R.id.rlv);
        mLl = (LinearLayout) findViewById(R.id.ll);
        mNv = (NavigationView) findViewById(R.id.nv);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        mToolbar.setTitle("ToolBar");
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.open, R.string.close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        mDrawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                mLl.setX(mNv.getRight());
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        View headerView = mNv.getHeaderView(0);
        headerView.setMinimumHeight(500);

        header = headerView.findViewById(R.id.header_img);
        final String imgPath = "/sdcard/Pictures/a.jpg";///sdcard/Pictures/s.jpg
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                header.setImageURI(Uri.parse(imgPath));
                IPresenter iPresenter = new IPresenter(MainActivity.this);
                iPresenter.loadUPUIData(imgPath);

            }
        });

        mRlv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RlvAdapter(this);
        mRlv.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 1, 1, "线性");
        menu.add(1, 2, 1, "网格");
        menu.add(1, 3, 1, "瀑布");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                mRlv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                break;
            case 2:
                mRlv.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                break;
            case 3:
                mRlv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                adapter.b = false;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loadUpUISunnecc(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadUpUISunnecc(ImgBean imgBean) {
        adapter.addList(imgBean.getResults());
    }

    @Override
    public void loadUpUIFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
