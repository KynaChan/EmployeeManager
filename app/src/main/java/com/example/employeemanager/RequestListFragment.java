package com.example.employeemanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Hashtable;

public class RequestListFragment extends Fragment {
    private RecyclerView requestList;
    private Hashtable<String, String> requestWithStatus;
    private RequestInfoItem newRequestInfo;
    private RequestInfoItem requestInfo;

    public RequestListFragment() {
        // Required empty public constructor
    }

    public void populateList(){
        requestWithStatus = new Hashtable<>();
        requestWithStatus.put("Alex Vasilios", "In pending");
        requestWithStatus.put("James Smith", "In pending");
        requestWithStatus.put("Abby Smith", "In pending");
        requestWithStatus.put("Peter Smith", "In pending");
    }

    public void addNewRequest(){
        if(getArguments() == null){
//            Log.d("argIsNull","NULL");
            return;
        }
        newRequestInfo = getArguments().getParcelable("newRequestInfo");
        String dateFrom = newRequestInfo.dateFrom;
        String status = newRequestInfo.status;
        requestWithStatus.put(dateFrom, status);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_request_list, container, false);

        requestList = (RecyclerView) v.findViewById(R.id.requestList);
        populateList();
        addNewRequest();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        requestList.setLayoutManager(linearLayoutManager);
        RequestListAdapter requestListAdapter = new RequestListAdapter(getContext(), (Hashtable<String, String>) requestWithStatus);
        requestList.setAdapter(requestListAdapter);
        return v;
    }
//
//    public void updateState(String newStatus, String dateFrom){
//        requestInfo.updateRequest(newStatus);
//        setNotifForStateChange(newStatus, dateFrom);
//    }
//
//    public void setNotifForStateChange(String status, String dateFrom){
//        showNotification(getString(R.string.notif_replied_request), "Request for "+dateFrom+" has been "+status+"! " );
//    }
//
//    public void showNotification(String textTitle, String textContent){
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
//                requireContext(),
//                "Notification")
//                .setSmallIcon(R.drawable.icon_bell)
//                .setContentTitle(textTitle)
//                .setContentText(textContent)
//                .setAutoCancel(true);
//        NotificationManagerCompat managerCompt = NotificationManagerCompat.from(requireContext());
//        managerCompt.notify(1, mBuilder.build());
//    }
}
