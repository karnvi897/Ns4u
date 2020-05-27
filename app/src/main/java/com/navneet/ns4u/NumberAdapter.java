package com.navneet.ns4u;

import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

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
    public void onBindViewHolder(@NonNull final NumberAdapter.ViewHolder holder, final int position) {
        holder.textView70.setText(list.get(position).getName());
        holder.textView71.setText(list.get(position).getNumber());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog=new ProgressDialog(holder.itemView.getContext());
                progressDialog.setTitle("Please Wait");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                FirebaseDatabase.getInstance().getReference("all_number").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(list.get(position).getNumber()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                        progressDialog.dismiss();
                        list.remove(position);
                        notifyDataSetChanged();}
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView70;
        private TextView textView71;
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView70 = (TextView) itemView.findViewById(R.id.textView70);
            textView71 = (TextView) itemView.findViewById(R.id.textView71);
            imageView=itemView.findViewById(R.id.delet);


        }
    }
}
