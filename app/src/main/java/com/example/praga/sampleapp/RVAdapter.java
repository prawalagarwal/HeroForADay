package com.example.praga.sampleapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
/**
 * Created by praga on 7/25/2016.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.NGOViewHolder> {
    List<NGOEvent> ngoEvents;
    Context context;
    private ClickListener clickListener;

    public class NGOViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CardView cv;
        TextView ngoName;
        TextView activityDate;
        TextView activityLocation;
        ImageView ngoTileImage;


        NGOViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            cv = (CardView)itemView.findViewById(R.id.cv);
            ngoName = (TextView)itemView.findViewById(R.id.ngo_name);
            activityDate = (TextView)itemView.findViewById(R.id.activity_date);
            activityLocation = (TextView) itemView.findViewById(R.id.activity_location);
            ngoTileImage = (ImageView) itemView.findViewById(R.id.ngo_title_photo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //context.startActivity(new Intent(context, NGODescriptionPageActivity.class ));

            if (clickListener!=null)
            {
                clickListener.itemCLicked(v, getAdapterPosition());
            }
        }
    }



    RVAdapter(Context context,List<NGOEvent> events){
        this.context = context;
        this.ngoEvents = events;
    }

    public void setClickListener(ClickListener clickListener)
    {
        this.clickListener = clickListener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public NGOViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        NGOViewHolder pvh = new NGOViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(NGOViewHolder ngoViewHolder, int i) {
        ngoViewHolder.ngoName.setText(ngoEvents.get(i).eventName);
        ngoViewHolder.activityDate.setText(ngoEvents.get(i).date);
        ngoViewHolder.activityLocation.setText(ngoEvents.get(i).location);
        ngoViewHolder.ngoTileImage.setImageResource(ngoEvents.get(i).photoId);
    }

    @Override
    public int getItemCount() {
        return ngoEvents.size();
    }

    public interface ClickListener{
        public void itemCLicked(View view, int position);
    }
}
