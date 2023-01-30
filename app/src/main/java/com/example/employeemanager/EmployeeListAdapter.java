package com.example.employeemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EmployeeListAdapter extends RecyclerView.Adapter{
    ArrayList<EmployeeInfo> employees;
    Context context;

    public EmployeeListAdapter(Context context, ArrayList<EmployeeInfo> employees){
        this.employees = employees;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_list_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder pendingViewHolder = (ViewHolder) holder;
        EmployeeInfo employeeInfo = employees.get(position);
        String name = employeeInfo.getForeName()+" "+employeeInfo.getSureName();

        pendingViewHolder.employeeName.setText(name);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                EmployeeInfoFragment employeeInfoFragment = new EmployeeInfoFragment(employeeInfo);
                activity.getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.employeeFragment, employeeInfoFragment, null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView employeeName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            employeeName = (TextView) itemView.findViewById(R.id.employeeName);
        }
    }
}
