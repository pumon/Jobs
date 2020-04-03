package com.example.jobs.ui.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jobs.R;

class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
{

    public ListAdapter() { }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView company;
        TextView job;
        ImageView imageView;

        public ViewHolder(View itemView)
        {
            super(itemView);
            this.company=itemView.findViewById(R.id.company_name);
            this.job=itemView.findViewById(R.id.job_details);
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

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, final int position)
    {
        holder.imageView.setImageResource(R.drawable.test);
        holder.company.setText("Infosys");
        holder.job.setText("Android Developer");

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
        return 10;
    }
}
