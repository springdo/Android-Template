package com.practiceapps.donal.mybluekins.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.practiceapps.donal.mybluekins.interfaces.DrawerClickListener;
import com.practiceapps.donal.mybluekins.helper.*;
import com.practiceapps.donal.mybluekins.*;

import java.util.Collections;
import java.util.List;

/**
 * Created by donal on 20/02/2015.
 */
public class RecyclerListInformationAdapter extends RecyclerView.Adapter <RecyclerListInformationAdapter.MyViewHolder> {
    // note the RecyclerView.Adapter expects a generic of ViewHolder and is an abstract class
    // so below overrides are needed as is the subclassing. we use our implementation of the ViewHolder
    // for the generic type and therefore need to change the overrides below to match

    //
    // A ViewHolder describes an item view and metadata about its place within the RecyclerView.
    // ie it represents a box around items such as textbox, image in a list item
    // https://developer.android.com/reference/android/support/v7/widget/RecyclerView.ViewHolder.html

    private LayoutInflater inflater;
    private Context mContext;
    // create the list as empty to prevent any null pointer annoyances
    List<RecyclerListInformation> recyclerListData = Collections.emptyList();

    private DrawerClickListener mDrawerClickListener;


    // constructer needed for layout inflation of the ViewHolder
    // create an inflater object here which will be used by the RecyclerView.Adapter's
    // onCreateViewHolder to inflate my ViewHolder xml file
    public RecyclerListInformationAdapter (Context context, List<RecyclerListInformation> recyclerListInformation){
        this.mContext = context;
        this.recyclerListData = recyclerListInformation;
        inflater = LayoutInflater.from(context);

    }


    @Override    // note the docs call viewGroup parent.....
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // view returns the root of my recylcer list in this instance is a LinearLayout
        // parent view is viewGroup
        View view = inflater.inflate(R.layout.recycler_list_custom_row, viewGroup, false);

        // by passing this inflated view ie layout object to the MyViewHolder implementation
        // android can use it to recycle the view and prevent expensive calls to findViewById over
        // and over. Esentially the MyViewHolder is the 'recycle'
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override    // note the docs call viewGroup parent.....
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {
        // for binding the data - first get my list item for each position and
        // apply to the ViewHolder's data fields
        RecyclerListInformation currentItem = recyclerListData.get(i);
        viewHolder.textView.setText(currentItem.getTitle());
        viewHolder.imageView.setImageResource(currentItem.getIconId());


    }

    public void setDrawerClickListener(DrawerClickListener clickListener){
        this.mDrawerClickListener = clickListener;
    }



    @Override
    public int getItemCount() {
        return recyclerListData.size();
    }

    // Implement the ViewHolder class as per requirement of the RecyclerView.Adapter
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        ImageView imageView;
        // constructor required as no default avail
        // note it takes a View object.....

        public MyViewHolder(View itemView) {
            super(itemView);
            // make each item in the view clickable
            itemView.setOnClickListener(this);
            textView = (TextView) itemView.findViewById(R.id.recyclerviewlist_text);
            imageView = (ImageView) itemView.findViewById(R.id.recyclerviewlist_image);
        }

        // should not handle launching teh new activity inside the Adapter
        @Override
        public void onClick(View view){
//            mContext.startActivity(new Intent(mContext, SubActivity.class));
            // check hte click listener was setup ie setDrawerClickListener was called
            if (mDrawerClickListener !=null){
                // this passes off launcing new activity to the drawer fragment
                mDrawerClickListener.drawerItemClicked(view, getPosition());
            }
        }
    }


}
