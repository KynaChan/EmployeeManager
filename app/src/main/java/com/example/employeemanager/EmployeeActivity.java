package com.example.employeemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        addFragment();
    }

    public void closeActivity(View v){
//        super.onBackPressed();
        finish();
    }

    public void addFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.employeeFragment, EmployeeListFragment.class, null)
                .addToBackStack("EmployeeListFragment")
                .commit();
    }
}