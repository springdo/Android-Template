package com.practiceapps.donal.mybluekins.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.practiceapps.donal.mybluekins.*;
import com.practiceapps.donal.mybluekins.POJO.Jobs;

import java.util.ArrayList;

/**
 * Created by donal on 26/02/2015.
 */
public class BuildSummaryAdapter extends RecyclerView.Adapter <BuildSummaryAdapter.BuildSummaryViewHolder>{

    private LayoutInflater mLayoutInflater;
    private ArrayList<Jobs> jobsArrayList = new ArrayList<>();

    private final String ABORTED = "ic_aborted";

    public BuildSummaryAdapter (Context context, ArrayList<Jobs> jList){
        this.jobsArrayList = jList;
    };

    @Override
    public BuildSummaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_build_summary_row, parent, false);
        BuildSummaryViewHolder buildSummaryViewHolder = new BuildSummaryViewHolder(view);
        return buildSummaryViewHolder;
    }

    @Override
    public void onBindViewHolder(BuildSummaryViewHolder holder, int position) {

        Jobs current = getJobsArrayList().get(position);
        holder.textView.setText(current.getName());

        // NOTE for grey keys are aborted, disabled, notbuilt

        if (current.getColor().contains("blue")){
            holder.imageView.setImageResource(R.drawable.ic_blue);

        } else if (current.getColor().contains("yellow")){
            holder.imageView.setImageResource(R.drawable.ic_yellow);

        } else if (current.getColor().contains("red")){
            holder.imageView.setImageResource(R.drawable.ic_red);

        } else {
//            (current.getColor().contains("aborted")
//            || current.getColor().contains("disabled")
//                    || current.getColor().contains("notbuilt"))
            holder.imageView.setImageResource(R.drawable.ic_aborted);
        }
    }

    @Override
    public int getItemCount() {
        return getJobsArrayList().size();
    }

    public void setJobsArrayList(ArrayList<Jobs> jobsArrayList) {
        this.jobsArrayList = jobsArrayList;
        notifyDataSetChanged();
//        notifyItemRangeChanged(0, jobsArrayList.size());
    }

    public ArrayList<Jobs> getJobsArrayList() {
        return jobsArrayList;
    }

    static class BuildSummaryViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public BuildSummaryViewHolder(View itemView) {
            // note itemView it the container view of our recylcer view
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.build_summary_image);
            textView = (TextView) itemView.findViewById(R.id.build_summary_jobname);
        }
    };

}
