package com.example.employeemanager;

import static com.android.volley.Request.Method.DELETE;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiManager {
    Context context;
    Fragment fragmentCtx;
    private String url = "http://web.socem.plymouth.ac.uk/COMP2000/api/employees/";

    public ApiManager(Context context, Fragment fragmentCtx){
        this.context=context;
        this.fragmentCtx=fragmentCtx;
    }

    public void requestEmployee(){
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrRequest = new JsonArrayRequest(
                url,
                new EmployeeListListener((EmployeeListFragment) fragmentCtx),
                new ResponseErrorHandler()
        );
        queue.add(jsonArrRequest);
    }

    public void postEmployeeInfo(int id, String forename, String surname) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(context);

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("id", id);
        jsonObj.put("forename", forename);
        jsonObj.put("surname", surname);

        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonObj,
                new PostEmployeeListener(),
                new ResponseErrorHandler()
        );
        queue.add(jsonObjRequest);
    }

    public void updateEmployeeInfo(int id, String forename, String surname) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(context);
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("id", id);
        jsonObj.put("forename", forename);
        jsonObj.put("surname", surname);

        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(
                Request.Method.PUT,
                url+String.valueOf(id)+"/",
                jsonObj,
                new PutInfoListener(),
                new ResponseErrorHandler()
        );
        queue.add(jsonObjRequest);
    }

    public void deleteEmployee(int id) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest jsonObjRequest = new StringRequest(
                Request.Method.DELETE,
                url+""+String.valueOf(id),
                new DeleteEmployeeListener(),
                new ResponseErrorHandler()
        );
        queue.add(jsonObjRequest);
    }
}


// GET
class EmployeeListListener implements Response.Listener<JSONArray>{
    private EmployeeListFragment fragmentCtx;
    public EmployeeListListener(EmployeeListFragment context)
    {
        this.fragmentCtx = (EmployeeListFragment) context;
    }

    @Override
    public void onResponse(JSONArray response) {
        try {
            for(int i = 0; i < response.length(); i++) {
                JSONObject employee = response.getJSONObject(i);
//                Log.e("HTTP RESPONSE", employee.getString("surname"));
                if(fragmentCtx == null){
                    return;
                }
                fragmentCtx.populateList(employee);
            }
            fragmentCtx.renderList();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}


// POST
class PostEmployeeListener implements Response.Listener<JSONObject>{
    @Override
    public void onResponse(JSONObject response) {
        Log.d("HTTP POST", String.valueOf(response));
    }
}


// PUT
class PutInfoListener implements Response.Listener<JSONObject>{
    @Override
    public void onResponse(JSONObject response) {
        Log.d("HTTP PUT", String.valueOf(response));
    }
}


// DELETE
class DeleteEmployeeListener implements Response.Listener<String>{
    @Override
    public void onResponse(String response) {
        Log.d("HTTP DELETE", String.valueOf(response));
    }

}


class ResponseErrorHandler implements Response.ErrorListener{

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("HTTPRESPONSE", error.toString());
    }
}
