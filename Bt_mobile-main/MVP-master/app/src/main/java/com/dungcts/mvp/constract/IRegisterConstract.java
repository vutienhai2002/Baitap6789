package com.dungcts.mvp.constract;

public interface IRegisterConstract {
    interface IView {
        void registerSuccess();
        void registerFailed(String s);
    }

    interface IPresenter {
        void register( String username, String password, String confirmPassword);
    }
}
