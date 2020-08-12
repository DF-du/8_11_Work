package com.dlf.a8_11_work.presenter;

import com.dlf.a8_11_work.bean.NoteBean;
import com.dlf.a8_11_work.contract.IContract;
import com.dlf.a8_11_work.model.IModel;

import java.util.List;

public class IPresenter implements IContract.Presenter {
    private final IModel iModel;
    IContract.View view;

    public IPresenter(IContract.View view) {
        this.view = view;
        iModel = new IModel();
    }

    @Override
    public void getNoteData(String type, NoteBean noteBean) {
        switch (type) {
            case "insert":
                iModel.getInsert(noteBean, new IContract.CallBack() {
                    @Override
                    public void noteSelect(List<NoteBean> noteBeans) {

                    }

                    @Override
                    public void noteLikeSelect(List<NoteBean> noteBeans) {

                    }

                    @Override
                    public void noteInsert(String msg) {
                        view.noteInsert(msg);
                    }
                });
                break;
            case "select":
                iModel.getSelect(noteBean, new IContract.CallBack() {
                    @Override
                    public void noteSelect(List<NoteBean> noteBeans) {
                        view.noteSelect(noteBeans);
                    }

                    @Override
                    public void noteLikeSelect(List<NoteBean> noteBeans) {

                    }

                    @Override
                    public void noteInsert(String msg) {

                    }
                });
                break;
            case "like":
                iModel.getLike(noteBean, new IContract.CallBack() {
                    @Override
                    public void noteSelect(List<NoteBean> noteBeans) {

                    }

                    @Override
                    public void noteLikeSelect(List<NoteBean> noteBeans) {
                        view.noteLikeSelect(noteBeans);
                    }

                    @Override
                    public void noteInsert(String msg) {

                    }
                });
                break;
        }
    }
}
