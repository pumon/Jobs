package com.example.jobs.ui.login;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobs.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
{
    private ArrayList<HashMap> arrayList;
    private  Context context;
    HashMap map ;
    Bitmap bmp;
    URL url;
    public ListAdapter(ArrayList<HashMap> arrayList, Context context) {
        this.arrayList=arrayList;
        this.context=context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView company;
        TextView job;
        TextView exp;
        TextView salary;
        ImageView imageView;

        public ViewHolder(View itemView)
        {
            super(itemView);
            this.company=itemView.findViewById(R.id.company_name);
            this.job=itemView.findViewById(R.id.job_details);
            this.exp=itemView.findViewById(R.id.exp);
            this.salary=itemView.findViewById(R.id.salary);
            this.imageView=itemView.findViewById(R.id.company_image);
        }
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, final int position)
    {
        map= arrayList.get(position);
        if(map.get("company_img").equals("null"))
            holder.imageView.setImageResource(R.drawable.test);
        else
            Picasso.with(context).load(map.get("company_img").toString()).fit().centerCrop().into(holder.imageView);
        holder.company.setText((String)map.get("company_name"));
        holder.job.setText((String)map.get("Job_type"));
        holder.salary.setText((String)"INR "+map.get("Job_salary"));
        holder.exp.setText((String)"Exp: "+map.get("Job_exp")+" Years");
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(v.getContext()," Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }
}
