package com.navneet.ns4u;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.ViewHolder> {
    List<NumberModel> list=new ArrayList<>();
    public void add(NumberModel numberModel) {
        this.list.add(numberModel);
    }

    @NonNull
    @Override
    public NumberAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.numberitem,parent,false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberAdapter.ViewHolder holder, int position) {
        holder.textView70.setText(list.get(position).getName());
        holder.textView71.setText(list.get(position).getNumber());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView70;
        private TextView textView71;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView70 = (TextView) itemView.findViewById(R.id.textView70);
            textView71 = (TextView) itemView.findViewById(R.id.textView71);

        }
    }
}
