package com.dungcts.mvp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dungcts.mvp.constract.IRegisterConstract;
import com.dungcts.mvp.constract.RegisterPresenter;

public class RegisterActivity extends AppCompatActivity implements IRegisterConstract.IView {

    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtConfirmPassword;
    private Button btnRegister;

    private IRegisterConstract.IPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mPresenter = new RegisterPresenter(this);

        initGUI();
    }

    private void initGUI() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String confirmPassword = edtConfirmPassword.getText().toString().trim();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
                    displayAlertDialog("Vui lòng điền đầy đủ thông tin.");
                } else if (!password.equals(confirmPassword)) {
                    displayAlertDialog("Mật khẩu và xác nhận mật khẩu không khớp.");
                } else {
                    mPresenter.register( username, password, confirmPassword);
                }
            }
        });
    }

    @Override
    public void registerSuccess() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("ĐĂNG KÝ THÀNH CÔNG !")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);

                        finish();
                    }
                });
        builder.create().show();
    }

    @Override
    public void registerFailed(String s) {
        displayAlertDialog("ĐĂNG KÝ THẤT BẠI. Vui lòng kiểm tra lại thông tin.");
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
    public void onLoginClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);

        startActivity(intent);
    }
}
