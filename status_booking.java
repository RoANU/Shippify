package com.example.currentplacedetailsonmap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class status_booking extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPref" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_booking);

        final SharedPreferences sharedPreferences=getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);
        String shared=sharedPreferences.getString("isbooked","");

        Toast.makeText(getApplicationContext(),shared,Toast.LENGTH_SHORT).show();

        String drivername=sharedPreferences.getString("dname","");
        float rating=sharedPreferences.getFloat("rating",3);
        int phone=sharedPreferences.getInt("phone",0);
        int p=sharedPreferences.getInt("price",200);
        int truck=sharedPreferences.getInt("truck",40);

        ImageView dimage= (ImageView) findViewById(R.id.driverImage);
        TextView dname= (TextView) findViewById(R.id.dname);
        TextView ttype= (TextView) findViewById(R.id.truckType);
        RatingBar ratingBar= (RatingBar) findViewById(R.id.ratingBar);
        TextView price=(TextView)findViewById(R.id.price);
        Button cancel= (Button) findViewById(R.id.cancel);

        dname.setText(drivername);
        ratingBar.setRating(rating);
        price.setText("Rs. "+String.valueOf(p)+" /-");

        if(truck==40)
            ttype.setText("mini");
        else if(truck==60)
            ttype.setText("pickup");
        else if(truck==100)
            ttype.setText("Tipper");
        else if(truck==150)
            ttype.setText("Truck");
        else if(truck==200)
            ttype.setText("Big Truck");

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().clear().commit();
                Intent i=new Intent(status_booking.this,MapsActivityCurrentPlace.class);
                startActivity(i);
            }
        });
    }
}
