package com.example.employeemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Hashtable;

public class RequestListAdapter extends RecyclerView.Adapter {
    Hashtable<String, String> requestWithStatus;
    Context context;
    private FragmentManager fragmentManager;

    public RequestListAdapter(Context context, Hashtable<String,String> requestWithStatus){
        this.requestWithStatus = requestWithStatus;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_list_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder pendingViewHolder = (ViewHolder) holder;
        String requestName = requestWithStatus.keySet().toArray()[position].toString(),
                requestStatus = requestWithStatus.get(requestName);

        pendingViewHolder.textRequestName.setText(requestName);
        pendingViewHolder.textStatus.setText(requestStatus);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(v.getContext(),
                        "Request from "+requestName+" is "+requestStatus,
                        Toast.LENGTH_LONG).show();
                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                RequestInfoFragment requestInfoFragment = new RequestInfoFragment();
                activity.getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.requestFragment, RequestInfoFragment.class, null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return requestWithStatus.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textRequestName, textStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textRequestName = (TextView) itemView.findViewById(R.id.textRequestName);
            textStatus = (TextView) itemView.findViewById(R.id.textStatus);
        }
    }
}
