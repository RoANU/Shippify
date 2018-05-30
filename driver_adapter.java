package com.example.currentplacedetailsonmap;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class driver_adapter extends RecyclerView.Adapter<driver_adapter.viewholder> {
    Context context1;
    public static final String MyPREFERENCES = "MyPref" ;

    int x,a,b;
    int truck;
    int distance;
    String Fname[];
    String Fphone[];
    String Frating[];
    SharedPreferences sharedpreferences;
    public driver_adapter(String Fnam[],String Fphon[],String Fratin[],int y,Context context)
    {
        Log.e("adapter", String.valueOf(y));
        x=y;

        Fname=new String[x];
        Fphone=new String[x];
        Frating=new String[x];

        Fname=Fnam;
        Fphone=Fphon;
        Frating=Fratin;
        context1=context;
        sharedpreferences = context1.getSharedPreferences(MyPREFERENCES, context1.MODE_PRIVATE);
        truck=sharedpreferences.getInt("truck",40);
        distance=sharedpreferences.getInt("distance",100);
        a=truck*distance;
        b=a/4;
        Log.e("DrivarAdapter_seeNA", String.valueOf(truck));
    }

    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        Log.e("viewholder","msg");
        View view=layoutInflater.inflate(R.layout.driver,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(final viewholder holder, int position) {
        final String dname=Fname[position];Log.e("onBind","msg");
        final float rat=Float.parseFloat(Frating[position]);
        final int phone=Integer.parseInt(Fphone[position]);

        b= (int) (b*(rat/5));
        b=a+b;
        Log.e("price", String.valueOf(b));
        holder.price1.setText("Rs. "+String.valueOf(b)+" /-");

        holder.name.setText(dname);
        holder.rating_Bar.setRating(rat);
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent=new Intent(Intent.ACTION_CALL);
                Intent intent=callIntent.setData(Uri.parse("tel:" + phone ));
                context1.startActivity(intent);
            }
        });
        holder.booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
               builder.setMessage("Confirm Booking");
                builder.setPositiveButton(R.string.confirm_booking, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("isbooked","yes");
                        editor.putString("dname",dname);
                        editor.putFloat("rating",rat);
                        editor.putInt("phone",phone);
                        editor.putInt("price",b);
                        editor.commit();
                        context1.startActivity(new Intent(context1,status_booking.class));
                    }
                })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {

        return  x;
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView name,price1;
        RatingBar rating_Bar;
        Button call,booking;
        ImageView pp;
        public viewholder(View itemView) {
            super(itemView);Log.e("vholder","msg");
            name= (TextView) itemView.findViewById(R.id.driver_name);
            price1= (TextView) itemView.findViewById(R.id.price);
            rating_Bar=(RatingBar)itemView.findViewById(R.id.rating_bar);
            booking= (Button) itemView.findViewById(R.id.book);
            call= (Button) itemView.findViewById(R.id.call);
            pp= (ImageView) itemView.findViewById(R.id.image);
            price1.setText("500");


           }
    }
}
