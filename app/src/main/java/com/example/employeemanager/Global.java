package com.example.employeemanager;

import android.app.Activity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Global {

    public static boolean switchNewRequest, switchDataUpdate, isLogin, keepLogin;
//    public static EmployeeInfo employeeInfo = new EmployeeInfo();
    public static String
            userName="ee",
            userPwd="0123456",
            adminSurName="Smith",
            adminForeName="Emily",
            position="Admin",
            joinDate="10 DEC 2020";

    public static String getEditText(EditText editText){
        String stringText = editText.getText().toString();
        return stringText;
    }

    public static void makeToast(Activity a, String msg){
        Toast.makeText(a, msg, Toast.LENGTH_SHORT).show();
    }

    public static boolean checkConfirmation(CheckBox cb) {
        return cb.isChecked();
    }

}
