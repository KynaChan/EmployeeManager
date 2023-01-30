package com.example.employeemanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class RequestInfoFragment extends Fragment {
    ImageButton btnClose;
    public RequestInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_request_info, container, false);
        btnClose = (ImageButton) v.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getParentFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.requestFragment, RequestListFragment.class, null)
                        .commit();
            }
        });
        return v;
    }

    public void closeFragment(View v){
        FragmentManager fm = getParentFragmentManager();
        fm.beginTransaction()
                .replace(R.id.requestFragment, RequestListFragment.class, null)
                .commit();
    }

}