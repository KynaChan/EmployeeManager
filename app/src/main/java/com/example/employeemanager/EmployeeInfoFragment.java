package com.example.employeemanager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import org.json.JSONException;

import java.util.zip.GZIPOutputStream;

public class EmployeeInfoFragment extends Fragment {
    private ImageButton btnClose;
    private Button btnSubmit, btnDelete;
    private LinearLayout boxDelete;
    private CheckBox btnConfirm;
    private EditText fNameEdit, lNameEdit, deleteEdit;

    private EmployeeInfo employeeInfo;

    public EmployeeInfoFragment(EmployeeInfo employeeInfo) {
        this.employeeInfo = employeeInfo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_employee_info, container, false);
        ApiManager api = new ApiManager(getContext(), this);
        initElement(v);
        initBindingCloseBtn();
        initBindingDeleteBtn(api);
        initBindingSubmitBtn(api);
        confirmDelete(v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        fNameEdit.setText(employeeInfo.getForeName());
        lNameEdit.setText(employeeInfo.getSureName());
    }

    private void initBindingSubmitBtn(ApiManager api){
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Global.checkConfirmation(btnConfirm)){
                    btnConfirm.setError(null);
                    // send to worker
//                    OneTimeWorkRequest task = OneTimeWorkRequest.from(ApiWorker.class);
//                    WorkManager.getInstance(null).enqueue(task);
                    try {
                        api.updateEmployeeInfo(
                                employeeInfo.getId(),
                                Global.getEditText(fNameEdit),
                                Global.getEditText(lNameEdit)
                        );
                        Global.makeToast(getActivity(), getString(R.string.employee_update));
                        closeFragment(v);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                btnConfirm.setError("");
            }
        });
    }
    private void initBindingDeleteBtn(ApiManager api){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    api.deleteEmployee(employeeInfo.getId());
                    Global.makeToast(getActivity(), getString(R.string.employee_delete));
                    closeFragment(v);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void confirmDelete(View v){
        deleteEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(Global.getEditText(deleteEdit).matches("DELETE")) {
                    btnDelete.setVisibility(v.VISIBLE);
                    boxDelete.setVisibility(v.GONE);
                    Log.d("VISIBLE", "VISIBLE");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void initBindingCloseBtn(){
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getParentFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.employeeFragment, EmployeeListFragment.class, null)
                        .commit();
            }
        });
    }

    public void closeFragment(View v){
        FragmentManager fm = getParentFragmentManager();
        fm.beginTransaction()
                .replace(R.id.employeeFragment, EmployeeListFragment.class, null)
                .commit();
    }



    private void initElement(View v){
        fNameEdit = (EditText) v.findViewById(R.id.fNameEdit);
        lNameEdit = (EditText) v.findViewById(R.id.lNameEdit);
        deleteEdit = (EditText) v.findViewById(R.id.deleteEdit);

        btnClose = (ImageButton) v.findViewById(R.id.btnClose);
        btnSubmit = (Button) v.findViewById(R.id.btnSubmit);
        btnDelete = (Button) v.findViewById(R.id.btnDelete);
        btnConfirm = (CheckBox) v.findViewById(R.id.btnConfirm);
        boxDelete = (LinearLayout) v.findViewById(R.id.boxDelete);
    }

}