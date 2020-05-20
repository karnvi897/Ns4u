package com.navneet.ns4u;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    List<VideoModel> list=new ArrayList<>();
    public void add(VideoModel model){
        list.add(model);
    }
    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.videoitem,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VideoAdapter.ViewHolder holder, final int position) {

        Picasso.get().load(list.get(position).getPhotourl()).into(holder.imageView2);
        holder.textView67.setText(list.get(position).getName());
        holder.textView68.setText(list.get(position).getVideourl());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(list.get(position).getVideourl()));
                try {
                  holder.itemView.getContext().startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView2;
        private TextView textView67;
        private TextView textView68;
        public ViewHolder(@NonNull View view) {
            super(view);
            imageView2 = (ImageView) view.findViewById(R.id.imageView2);
            textView67 = (TextView) view.findViewById(R.id.textView67);
            textView68 = (TextView) view.findViewById(R.id.textView68);

        }
    }
}
