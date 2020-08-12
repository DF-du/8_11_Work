package com.dlf.two.presenter;

import com.dlf.two.bean.ImgBean;
import com.dlf.two.contract.MyContract;
import com.dlf.two.model.MyModel;

public class MyPresenter implements MyContract.Presenter {

    MyContract.View view;
    private final MyModel myModel;

    public MyPresenter(MyContract.View view) {
        this.view = view;
        myModel = new MyModel();
    }

    @Override
    public void loadUpUIData() {
        myModel.getData(new MyContract.CallBack() {
            @Override
            public void loadUpUISunnecc(ImgBean infoBean) {
                view.loadUpUISunnecc(infoBean);
            }

            @Override
            public void loadUpUIFailed(String msg) {
                view.loadUpUIFailed(msg);
            }
        });
    }
}
