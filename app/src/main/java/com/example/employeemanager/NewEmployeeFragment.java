package com.example.employeemanager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONException;

import java.util.Random;

public class NewEmployeeFragment extends Fragment {
    ImageButton btnClose;
    CheckBox btnConfirm;
    Button btnSubmit;
    private EditText fNameEdit, lNameEdit;

    public NewEmployeeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_employee, container, false);
        initElement(v);
        btnConfirm.setError(null);
        initBindingCloseBtn();
        initBindingSubmitInfo();
        return v;
    }

    private void initBindingCloseBtn(){
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFragment(v);
            }
        });
    }
    private void initBindingSubmitInfo(){
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            Random id = new Random();
            Activity a = getActivity();
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                if(Global.checkConfirmation(btnConfirm)){

                    // send to worker
//                    OneTimeWorkRequest.Builder task = new OneTimeWorkRequest.Builder(ApiWorker.class);
//                    Data.Builder data = new Data.Builder();
//                    data.put("info", new EmployeeInfo(id.nextInt(),Global.getEditText(fNameEdit), Global.getEditText(lNameEdit)));
//                    task.setInputData(data.build());
//                    WorkManager.getInstance(null).enqueue(task.build());

//                    if (checkFillEditText()){

                        ApiManager api = new ApiManager(getContext(), getParentFragment());
                        try {
                            api.postEmployeeInfo(id.nextInt(), Global.getEditText(fNameEdit), Global.getEditText(lNameEdit));
                            Global.makeToast(a, getString(R.string.employee_added));
                            closeFragment(v);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
//                    Global.makeToast(a, getString(R.string.submit_error_msg));
//                }
                btnConfirm.setError("");
                return;
            }
        });
    }

    public void closeFragment(View v){
        FragmentManager fm = getParentFragmentManager();
        fm.beginTransaction()
                .replace(R.id.employeeFragment, EmployeeListFragment.class, null)
                .commit();
    }

    private boolean checkFillEditText() {
        if (Global.getEditText(fNameEdit).matches("") || Global.getEditText(lNameEdit).matches("")) {
            return false;
        }
        return true;
    }


    private void initElement(View v){
        btnClose = (ImageButton) v.findViewById(R.id.btnClose);
        btnConfirm = (CheckBox) v.findViewById(R.id.btnConfirm);
        btnSubmit = (Button) v.findViewById(R.id.btnSubmit);

        fNameEdit = (EditText) v.findViewById(R.id.fNameEdit);
        lNameEdit = (EditText) v.findViewById(R.id.lNameEdit);
    }
}