package com.dungcts.mvp.constract;

public class RegisterPresenter implements IRegisterConstract.IPresenter {

    private IRegisterConstract.IView mView;

    public RegisterPresenter(IRegisterConstract.IView view) {
        this.mView = view;
    }

    @Override
    public void register( String username, String password, String confirmPassword) {
        if ( username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            mView.registerFailed("Vui lòng điền đầy đủ thông tin.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            mView.registerFailed("Mật khẩu và xác nhận mật khẩu không khớp.");
            return;
        }



        if (password.length() < 6) {
            mView.registerFailed("Mật khẩu phải chứa ít nhất 6 ký tự.");
            return;
        }

        mView.registerSuccess();
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
