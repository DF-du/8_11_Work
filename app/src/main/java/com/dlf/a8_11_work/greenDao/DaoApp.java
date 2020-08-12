package com.dlf.a8_11_work.greenDao;

import android.app.Application;

import com.dlf.greendaodemo.db.DaoMaster;
import com.dlf.greendaodemo.db.DaoSession;

public class DaoApp extends Application {
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper ku = new DaoMaster.DevOpenHelper(this, "ku");
        daoSession = new DaoMaster(ku.getWritableDatabase()).newSession();
    }

    public static  DaoSession getDaoSession(){
        return daoSession;
    }
}
