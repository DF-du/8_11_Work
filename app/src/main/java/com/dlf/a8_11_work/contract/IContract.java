package com.dlf.a8_11_work.contract;

import com.dlf.a8_11_work.bean.NoteBean;

import java.util.List;

public interface IContract {
    interface Model {
        void getInsert(NoteBean noteBean,CallBack callBack);
        void getLike(NoteBean noteBean,CallBack callBack);
        void getSelect(NoteBean noteBean,CallBack callBack);
    }

    interface View {
        void noteSelect(List<NoteBean> noteBeans);
        void noteLikeSelect(List<NoteBean> noteBeans);
        void noteInsert(String msg);
    }

    interface Presenter {
        void getNoteData(String type,NoteBean noteBean);
    }

    interface CallBack{
        void noteSelect(List<NoteBean> noteBeans);
        void noteLikeSelect(List<NoteBean> noteBeans);
        void noteInsert(String msg);
    }
}
