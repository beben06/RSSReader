package com.dandine.benjamin.rssreader.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dandine.benjamin.rssreader.R;
import com.dandine.benjamin.rssreader.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * RecyclerAdapter
 * Created by benjamindandine on 07/02/2017.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private OnItemClickListener listener;
    private List<Item> items;

    public RecyclerViewAdapter(List<Item> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.textView.setText(items.get(position).title);
        if (items.get(position).enclosure != null) {
            Picasso.with(viewHolder.imageView.getContext()).load(items.get(position).enclosure.getUrl()).into(viewHolder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Item item);
    }

    /**
     * View Holder
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.title);
            imageView = (ImageView) view.findViewById(R.id.img);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, items.get(getAdapterPosition()));
        }
    }

}
