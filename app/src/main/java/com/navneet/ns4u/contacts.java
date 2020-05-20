package com.navneet.ns4u;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class contacts extends Fragment {

    private FloatingActionButton addfab;
    private LinearLayout addcontactlayout;
    private EditText addname;
    private EditText addmobile;
    private Button addcontact;
    RecyclerView recyclerView;

    String noti=null;




    public contacts() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addfab = view.findViewById(R.id.addfab);
        addcontactlayout = view.findViewById(R.id.addcontactlayout);
        addname = view.findViewById(R.id.addname);
        addmobile = view.findViewById(R.id.addmobile);
        addcontact = view.findViewById(R.id.addcontact);
        recyclerView=view.findViewById(R.id.rc);

        addcontactlayout.setVisibility(View.GONE);

        addfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addcontactlayout.setVisibility(View.VISIBLE);
            }
        });

        addcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=addname.getText().toString();
                String number=addmobile.getText().toString();
                if(name.isEmpty()){
                    addname.setError("Enter Name");
                }else if(number.isEmpty()){
                    addmobile.setError("Enter Number");
                }else {

                    /////////////////main code/////////////////////

                    if(noti==null){
                        Database database=new Database(getContext());
                        database.setName(name);
                        database.setNumber(number);
                    }
                    HashMap<String,String> map=new HashMap<>();
                    map.put("name",name);
                    map.put("number",number);
                    FirebaseDatabase.getInstance().getReference("all_number").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push()
                            .setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                addcontactlayout.setVisibility(View.GONE);
                            }
                        }
                    });
                }
            }
        });



        FirebaseDatabase.getInstance().getReference("all_number").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            noti="exist";
                            NumberAdapter adapter =new NumberAdapter();
                            for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                                NumberModel numberModel=snapshot.getValue(NumberModel.class);
                                adapter.add(numberModel);
                            }
                            recyclerView.setAdapter( adapter);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        }else {
                            Toast.makeText(getContext(), "Have no number, add please", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
