package com.dlf.a8_11_work.greenDao;

import com.dlf.a8_11_work.bean.NoteBean;
import com.dlf.greendaodemo.db.DaoSession;
import com.dlf.greendaodemo.db.NoteBeanDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class GreenDaoUtil {
    static DaoSession daoSession = DaoApp.getDaoSession();

    public static void insert(NoteBean noteBean){
        daoSession.insert(noteBean);
    }

    public static List<NoteBean> likeSelect(NoteBean noteBean){
        QueryBuilder<NoteBean> where = daoSession.queryBuilder(NoteBean.class).where(NoteBeanDao.Properties.Title.like("%" + noteBean.getTitle() + "%"));
        List<NoteBean> list = where.list();
        return list;
    }

    public static List<NoteBean> select(){
        List<NoteBean> noteBean = daoSession.loadAll(NoteBean.class);
        return noteBean;
    }

    public static NoteBean query(NoteBean noteBean){
        NoteBean unique = daoSession.queryBuilder(NoteBean.class).where(NoteBeanDao.Properties.Title.eq(noteBean.getTitle())).unique();
        return unique;
    }
}
