package com.example.employeemanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmployeeListFragment extends Fragment {
    ProgressBar loadingPB;
    Button btnAddEmployee;
    RecyclerView employeeList;
    private ArrayList<EmployeeInfo> employees = new ArrayList<EmployeeInfo>();

    public EmployeeListFragment() {
        // Required empty public constructor
    }

    public void populateList(JSONObject employeeObj){
        int id = 0;
        String forename = null, surname = null;
        try {
            id = employeeObj.getInt("id");
            forename = employeeObj.getString("forename");
            surname = employeeObj.getString("surname");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        employees.add(new EmployeeInfo(id, forename,surname));
    }

    public void renderList(){
        loadingPB.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        employeeList.setLayoutManager(linearLayoutManager);
        EmployeeListAdapter employeeListAdapter = new EmployeeListAdapter(getContext(), (ArrayList<EmployeeInfo>) employees);
        employeeList.setAdapter(employeeListAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_employee_list, container, false);
        initElement(v);
        initBindingAddBtn();
        loadingPB.setVisibility(View.VISIBLE);
        ApiManager api = new ApiManager(getContext(), this);
        api.requestEmployee();

        return v;
    }

    public void initBindingAddBtn(){
        btnAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getParentFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.employeeFragment, NewEmployeeFragment.class, null)
                        .commit();
            }
        });
    }


    private void initElement(View v){
        btnAddEmployee = (Button) v.findViewById(R.id.btnAddEmployee);
        loadingPB = (ProgressBar) v.findViewById(R.id.loadingPB);
        employeeList = (RecyclerView) v.findViewById(R.id.employeeList);
    }
}