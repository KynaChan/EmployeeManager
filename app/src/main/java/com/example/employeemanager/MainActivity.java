package com.example.employeemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView position, name, joinDate;
    ImageButton iBUser;
    int SELECT_PICTURE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String userName = Global.adminForeName+ " " + Global.adminSurName;
        checkLogin(findViewById(android.R.id.content));
        getSupportFragmentManager().beginTransaction().add(R.id.requestFragment, RequestListFragment.class, null).commit();

        iBUser = (ImageButton) findViewById(R.id.iBUser);
        position = (TextView) findViewById(R.id.textPosition);
        position.setText(Global.position);
        name = (TextView) findViewById(R.id.textName);
        name.setText(userName);
        joinDate = (TextView) findViewById(R.id.textJoinDate);
        joinDate.setText(Global.joinDate);
    }

    private void checkLogin(View v){
        if(Global.isLogin){
            return;
        }
        startLogin(v);
    }

    public void startSetting(View v) {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }

    public void startEmployeeList(View v) {
        Intent intent = new Intent(this, EmployeeActivity.class);
        startActivity(intent);
    }

    public void startRequestList(View v) {
        Intent intent = new Intent(this, RequestActivity.class);
        startActivity(intent);
    }
    public void startLogin(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void pickImg(View v){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select picture"), SELECT_PICTURE);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == SELECT_PICTURE) {
            Uri selectedImageUri = data.getData();
            if (null != selectedImageUri) {
                iBUser.setImageURI(selectedImageUri);
            }
            return;
        }
        return;
    }




}