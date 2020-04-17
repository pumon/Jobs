package com.example.jobs.ui.login;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jobs.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;


public class Create_Job_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    // TODO: Rename and change types of parameters
    private DatabaseReference rootRef,data;
    private EditText job_type,company_name,exp,salary;

    public Create_Job_Fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.create_job_fragment, container, false);
        Button upload=root.findViewById(R.id.upload);
        company_name=root.findViewById(R.id.company_name);
        job_type=root.findViewById(R.id.job_type);
        exp=root.findViewById(R.id.job_exp);
        salary=root.findViewById(R.id.job_salary);
        upload.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                upload_data();
                company_name.setText("");
                exp.setText("");
                salary.setText("");
                job_type.setText("");
                Toast.makeText(getContext(),"Uploaded",Toast.LENGTH_LONG).show();
            }
        });
        return root;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void upload_data(){
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            String URL = "https://jobs-9e6e0.firebaseio.com/Job_details.json";
            JSONObject jsonBody = new JSONObject();
            String id=LocalDateTime.now().toString();
            jsonBody.put("Job_id", id);
            jsonBody.put("Job_type", job_type.getText());
            jsonBody.put("Job_salary", salary.getText());
            jsonBody.put("Job_exp", exp.getText());
            jsonBody.put("company_img", "null");
            jsonBody.put("company_name", company_name.getText());

            JSONArray array= new JSONArray();
            array.put(jsonBody);
            //Toast.makeText(getContext(),array.toString(),Toast.LENGTH_LONG).show();
            final String requestBody = jsonBody.toString();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
