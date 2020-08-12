package com.dlf.a8_11_work.model;

import com.dlf.a8_11_work.bean.NoteBean;
import com.dlf.a8_11_work.contract.IContract;
import com.dlf.a8_11_work.greenDao.GreenDaoUtil;

import java.util.List;

public class IModel implements IContract.Model {
    @Override
    public void getInsert(NoteBean noteBean, IContract.CallBack callBack) {
        if (GreenDaoUtil.query(noteBean) == null) {
            GreenDaoUtil.insert(noteBean);
            callBack.noteInsert("插入成功");
        }else {
            callBack.noteInsert("已有该条笔记");
        }
    }

    @Override
    public void getLike(NoteBean noteBean, IContract.CallBack callBack) {
        List<NoteBean> noteBeans = GreenDaoUtil.likeSelect(noteBean);
        callBack.noteLikeSelect(noteBeans);
    }

    @Override
    public void getSelect(NoteBean noteBean, IContract.CallBack callBack) {
        List<NoteBean> select = GreenDaoUtil.select();
        callBack.noteSelect(select);
    }
}
