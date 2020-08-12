package com.dlf.two.contract;

public interface IContract {
    interface Model {
        void getData(CallBack callBack, String imgPath);
    }

    interface View {
        void loadUpUISunnecc(String msg);
        void loadUpUIFailed(String msg);
    }

    interface Presenter {
        void loadUPUIData(String imgPath);
    }

    interface CallBack {
        void loadUpUISunnecc(String msg);
        void loadUpUIFailed(String msg);
    }
}
