package com.dungcts.mvp.constract;

import data.model.User;

public class LoginPresenter implements  ILoginConstract.IPresenter{

    User userList[] = {
            new User("admin", "12345"),
    };

    private ILoginConstract.IView mView;

    public LoginPresenter(ILoginConstract.IView view) {
        mView = view;
    }

    @Override
    public void login(String username, String password) {
        boolean isLoggedIn = false;
        for (User user : userList) {
            if (username.equals(user.username) && password.equals(user.password)) {
                isLoggedIn = true;
                break;
            }
        }
        if (isLoggedIn) {
            mView.loginSuccess();
        } else {
            mView.loginFailed();
        }
    }
}
