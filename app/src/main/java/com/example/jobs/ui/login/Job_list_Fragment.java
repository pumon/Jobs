package com.example.jobs.ui.login;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.lifecycle.ViewModelProviders;

import androidx.fragment.app.Fragment;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jobs.R;

public class Job_list_Fragment extends Fragment {

    public static Job_list_Fragment newInstance(int i) {
        return new Job_list_Fragment();
    }

    public Job_list_Fragment(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.job_list__fragment, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recycler_list);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ListAdapter mListadapter = new ListAdapter();
        recyclerView.setAdapter(mListadapter);
        //return inflater.inflate(R.layout.job_list__fragment, container, false);
        return root;
    }
    
}
