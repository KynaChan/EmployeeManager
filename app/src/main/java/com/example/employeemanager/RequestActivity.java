package com.example.employeemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class RequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        addFragment();
    }

    public void addFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.requestFragment, RequestListFragment.class, null)
                .addToBackStack("EmployeeListFragment")
                .commit();
    }

    public void closeActivity(View v){
        finish();
    }

}