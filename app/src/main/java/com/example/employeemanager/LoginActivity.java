package com.example.employeemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout usernameWrapper, pwdWrapper;
    private EditText inputUsername, inputPwd;
    private CheckBox btnKeepLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        pwdWrapper = (TextInputLayout) findViewById(R.id.pwdWrapper);
        inputUsername = (EditText) findViewById(R.id.inputUsername);
        inputPwd = (EditText) findViewById(R.id.inputPwd);
        btnKeepLogin = (CheckBox) findViewById(R.id.btnKeepLogin);

        inputUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        inputPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }

    public boolean validateUsername(String username){
//        sample username = ee;
        if(username.isEmpty() || !username.equals(Global.userName)){
            return false;
        }
        return true;
    }
    public boolean validatePassword(String pwd) {
//        sample pwd = 0123456;
        if(pwd.length() < 6 || !pwd.equals(Global.userPwd)) {
            return false;
        }
        return true;
    }

    public void startMain(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void login(View v){

        String username = usernameWrapper.getEditText().getText().toString().trim();
        String pwd = pwdWrapper.getEditText().getText().toString().trim();
        usernameWrapper.setErrorEnabled(false);
        pwdWrapper.setErrorEnabled(false);

        if (!validateUsername(username)) {
            usernameWrapper.setError(getString(R.string.username_error));
            return;
        }
        if (!validatePassword(pwd)) {
            pwdWrapper.setError(getString(R.string.pwd_error));
            return;
        }
        Global.isLogin = true;
        isKeepLogin();
        startMain(v);
    }

    private void hideKeyboard(View v) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public boolean isKeepLogin(){
        Global.keepLogin = btnKeepLogin.isChecked();
        return btnKeepLogin.isChecked();
    }
}