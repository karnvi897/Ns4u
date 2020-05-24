package com.navneet.ns4u;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import java.util.ArrayList;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class Home extends AppCompatActivity implements LocationListener {


    private SpaceNavigationView space;
    String phoneno;

    protected LocationManager locationManager;
    protected LocationListener locationListener;

    String provider;
    protected String latitude, longitude;
    protected boolean gps_enabled, network_enabled;
    private TextView locationt;
    private String link;

    private ArrayList permissionsToRequest;
    private ArrayList permissionsRejected = new ArrayList();
    private ArrayList permissions = new ArrayList();

    private final static int ALL_PERMISSIONS_RESULT = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        permissions.add(ACCESS_FINE_LOCATION);
        permissions.add(ACCESS_COARSE_LOCATION);


        locationt =  findViewById(R.id.location);


        permissionsToRequest = findUnAskedPermissions(permissions);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


            if (permissionsToRequest.size() > 0)
                requestPermissions((String[]) permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flfragment, new Dashboard());
        ft.commit();


        space = (SpaceNavigationView) findViewById(R.id.space);

        space.showIconOnly();
        space.initWithSaveInstanceState(savedInstanceState);
        space.addSpaceItem(new SpaceItem("Home", R.drawable.ic_home_black_24dp));
        space.addSpaceItem(new SpaceItem("Profile", R.drawable.ic_person_black_24dp));
        space.addSpaceItem(new SpaceItem("Contacts", R.drawable.ic_people_black_24dp));
        space.addSpaceItem(new SpaceItem("About", R.drawable.ic_filter_tilt_shift_black_24dp));

        space.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                final Database database = new Database(getApplicationContext());
                phoneno = database.getNumber();
                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.SEND_SMS)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(Home.this,
                            Manifest.permission.SEND_SMS)) {
                    } else {
                        ActivityCompat.requestPermissions(Home.this,
                                new String[]{Manifest.permission.SEND_SMS},
                                0);
                    }

                }else if (phoneno == "") {

                    Toast.makeText(Home.this, "Add Contact first", Toast.LENGTH_SHORT).show();
                }
                    else{
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("+91"+phoneno, null, "oooooaaaa......help.. "+link, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                      }

                FirebaseDatabase.getInstance().getReference("all_number").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                             int i=0;
                             for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                                 if(i==0){
                                     i=1;
                                 }else {
                                     NumberModel model=snapshot.getValue(NumberModel.class);
                                    phoneno= model.getNumber();
                                     if (ContextCompat.checkSelfPermission(getApplicationContext(),
                                             Manifest.permission.SEND_SMS)
                                             != PackageManager.PERMISSION_GRANTED) {
                                         if (ActivityCompat.shouldShowRequestPermissionRationale(Home.this,
                                                 Manifest.permission.SEND_SMS)) {
                                         } else {
                                             ActivityCompat.requestPermissions(Home.this,
                                                     new String[]{Manifest.permission.SEND_SMS},
                                                     0);
                                         }}else{
                                         SmsManager smsManager = SmsManager.getDefault();
                                         smsManager.sendTextMessage(phoneno, null, "oooooaaaa......help.. ", null, null);
                                         Toast.makeText(getApplicationContext(), "SMS sent.",
                                                 Toast.LENGTH_LONG).show();
                                     }

                                 }                             }

                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(Home.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });


            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Toast.makeText(Home.this, itemName, Toast.LENGTH_SHORT).show();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                switch (itemIndex) {
                    case 0:
                        ft.replace(R.id.flfragment, new Dashboard());
                        ft.commit();

                        break;
                    case 1:
                        ft.replace(R.id.flfragment, new profile());
                        ft.commit();
                        break;
                    case 2:
                        ft.replace(R.id.flfragment, new contacts());
                        ft.commit();

                        break;
                    case 3:
                        ft.replace(R.id.flfragment, new About());
                        ft.commit();

                        break;

                }


            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(Home.this, itemName, Toast.LENGTH_SHORT).show();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                switch (itemIndex) {
                    case 0:
                        ft.replace(R.id.flfragment, new Dashboard());
                        ft.commit();

                        break;
                    case 1:
                        ft.replace(R.id.flfragment, new profile());
                        ft.commit();
                        break;
                    case 2:
                        ft.replace(R.id.flfragment, new contacts());
                        ft.commit();
                        break;

                    case 3:
                        ft.replace(R.id.flfragment, new About());
                        ft.commit();

                        break;
                }
            }
        });

        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }

    @Override
    public void onLocationChanged(Location location) {

        latitude=""+location.getLatitude();
        longitude=""+location.getLongitude();
        link="https://www.google.com/maps/@"+latitude+","+longitude+",19.43z";
        locationt.setText(link);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    private ArrayList findUnAskedPermissions(ArrayList wanted) {
        ArrayList result = new ArrayList();

        for (Object perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(Object permission) {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(String.valueOf(permission)) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }


}
