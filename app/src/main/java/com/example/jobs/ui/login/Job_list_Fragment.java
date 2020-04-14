package com.example.jobs.ui.login;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.lifecycle.ViewModelProviders;

import androidx.fragment.app.Fragment;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jobs.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Job_list_Fragment extends Fragment {

 //   public static Job_list_Fragment newInstance() {
 //       return new Job_list_Fragment();
  //  }
    private RecyclerView recyclerView;
    private ListAdapter mListadapter;
    private ArrayList<HashMap> arrayList;
    private LinearLayoutManager layoutManager;
    RequestQueue queue;
    String URL = "https://jobs-9e6e0.firebaseio.com/Job_details.json";
    public Job_list_Fragment(){

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayList=get_job_details();

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.job_list__fragment, container, false);
            layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mListadapter = new ListAdapter(arrayList, getContext());
            recyclerView = (RecyclerView) root.findViewById(R.id.recycler_list);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(mListadapter);
            //return inflater.inflate(R.layout.job_list__fragment, container, false);
        return root;

    }


    private ArrayList<HashMap> get_job_details(){
        final ArrayList<HashMap> details=new ArrayList<>();
        queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray data=new JSONArray(response);
                    for(int i=0;i<data.length();i++) {
                        HashMap<String, String> map = new HashMap<String, String>();
                        JSONObject tmp=data.getJSONObject(i);
                        Log.d("data",""+i+""+tmp.getString("Job_id"));
                        map.put("Job_id",tmp.getString("Job_id").toString());
                        map.put("company_name",tmp.getString("company_name").toString());
                        map.put("Job_exp",tmp.getString("Job_exp").toString());
                        map.put("Job_salary",tmp.getString("Job_salary").toString());
                        map.put("Job_type",tmp.getString("Job_type").toString());
                        map.put("company_img",tmp.getString("company_img").toString());
                        details.add(map);
                        mListadapter.notifyDataSetChanged();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
             //   Toast.makeText(getContext(),details.size()+""+map.get("Job_type"),Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString()+"<=");
            }
        });
        queue.add(request);

        return details;
    }




}
