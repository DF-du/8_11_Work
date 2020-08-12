package com.dlf.two.contract;

import com.dlf.two.bean.ImgBean;

public interface MyContract {
    interface Model {
        void getData(CallBack callBack);
    }

    interface View {
        void loadUpUISunnecc(ImgBean imgBean);
        void loadUpUIFailed(String msg);
    }

    interface Presenter {
        void loadUpUIData();
    }

    interface CallBack {
        void loadUpUISunnecc(ImgBean imgBean);
        void loadUpUIFailed(String msg);
    }
}
