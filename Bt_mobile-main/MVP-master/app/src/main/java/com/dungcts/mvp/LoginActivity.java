package com.dungcts.mvp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dungcts.mvp.constract.ILoginConstract;
import com.dungcts.mvp.constract.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements ILoginConstract.IView {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;

    private ILoginConstract.IPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initGUI();
    }

    private void initGUI() {
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                if (username.isEmpty() || password.isEmpty()) {
                    displayAlertDialog("Tên đăng nhập và mật khẩu không được để trống");
                } else {
                    mPresenter.login(username, password);
                }
            }
        });
        mPresenter = new LoginPresenter(this);
    }

    @Override
    public void loginSuccess() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("ĐĂNG NHẬP THÀNH CÔNG !")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);

                        finish();
                    }
                });
        builder.create().show();
    }

    @Override
    public void loginFailed() {
        displayAlertDialog("ĐĂNG NHẬP THẤT BẠI. Tên đăng nhập và mật khẩu không đúng - Vui lòng kiểm tra lại tên đăng nhập và mật khẩu.");
    }

    private void displayAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }
    public void onRegisterClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);

        startActivity(intent);
    }
}