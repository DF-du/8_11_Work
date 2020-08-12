package com.dlf.two.presenter;

import com.dlf.two.contract.IContract;
import com.dlf.two.model.IModel;

public class IPresenter implements IContract.Presenter {

    IContract.View view;
    private final IModel iModel;

    public IPresenter(IContract.View view) {
        this.view = view;
        iModel = new IModel();
    }



    @Override
    public void loadUPUIData(String imgPath) {
        iModel.getData(new IContract.CallBack() {
            @Override
            public void loadUpUISunnecc(String msg) {
                view.loadUpUISunnecc(msg);
            }

            @Override
            public void loadUpUIFailed(String msg) {
                view.loadUpUIFailed(msg);
            }
        },imgPath);
    }
}
