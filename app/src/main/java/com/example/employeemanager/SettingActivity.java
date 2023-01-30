package com.example.employeemanager;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {
    private RadioGroup radioGroupTheme;
    private Switch switchRequestRespond, switchDataUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        radioGroupTheme = (RadioGroup) findViewById(R.id.radioGroupTheme);
        radioGroupTheme.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch(checkedId){
                    case R.id.radioDefaultTheme:
                        Toast.makeText(SettingActivity.this, "Default theme is selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioLightTheme:
                        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO);
                        Toast.makeText(SettingActivity.this, "Light theme is selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioDarkTheme:
                        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES);
                        Toast.makeText(SettingActivity.this, "Dark theme is selected", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        switchRequestRespond = (Switch) findViewById(R.id.switchRequestRespond);
        switchRequestRespond.setChecked(Global.switchNewRequest);
        switchDataUpdate = (Switch) findViewById(R.id.switchDataUpdate);
        switchDataUpdate.setChecked(Global.switchDataUpdate);
        switchRequestRespond.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(SettingActivity.this,
                        "Notification for new request is " + (isChecked ? "ON" : "OFF"),
                        Toast.LENGTH_SHORT).show();
                Global.switchNewRequest = isChecked;
            }
        });
        switchDataUpdate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(SettingActivity.this,
                        "Notification for data update is " + (isChecked ? "ON" : "OFF"),
                        Toast.LENGTH_SHORT).show();
                Global.switchDataUpdate = isChecked;
            }
        });
    }

    public void closeActivity(View v){
        super.onBackPressed();
    }



}