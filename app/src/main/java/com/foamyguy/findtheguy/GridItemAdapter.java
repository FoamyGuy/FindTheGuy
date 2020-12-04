package com.foamyguy.findtheguy;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by O_O on 12/14/2015.
 */
public class GridItemAdapter extends RecyclerView.Adapter<GridItemAdapter.ViewHolder> {
    private ArrayList<String> mDataset;

    private int curDuddIcon = Types.ICONS[0];
    private int curGoodIcon = Types.ICONS[1];

    private int score = 0;
    TextView scoreTxt;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView imgView;


        public ViewHolder(View v) {
            super(v);
            imgView = (ImageView) v.findViewById(R.id.icon);
        }
    }

    public void add(int position, String item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(String item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    public void moveGood() {

        remove(Types.TYPE_GOOD);
        Random rnd = new Random();
        add(rnd.nextInt(49), Types.TYPE_GOOD);


    }

    public void changeSkin() {
        Random rnd = new Random();

        curDuddIcon = Types.ICONS[rnd.nextInt(Types.ICONS.length)];
        curGoodIcon = Types.ICONS[rnd.nextInt(Types.ICONS.length)];
        while (curDuddIcon == curGoodIcon) {
            curGoodIcon = Types.ICONS[rnd.nextInt(Types.ICONS.length)];
        }
        notifyDataSetChanged();
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public GridItemAdapter(ArrayList<String> myDataset) {
        mDataset = myDataset;
    }

    public GridItemAdapter(ArrayList<String> myDataset, TextView scoreTxt) {

        mDataset = myDataset;
        this.scoreTxt = scoreTxt;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GridItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String type = mDataset.get(position);
        if (type.equals(Types.TYPE_DUDD)) {
            holder.imgView.setImageResource(curDuddIcon);
        } else if (type.equals(Types.TYPE_GOOD)) {
            holder.imgView.setImageResource(curGoodIcon);
            holder.imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moveGood();
                    changeSkin();
                    //Toast.makeText(v.getContext(), "Click", Toast.LENGTH_SHORT).show();
                    score++;
                    scoreTxt.setText("Score: " + score);
                }
            });

        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}