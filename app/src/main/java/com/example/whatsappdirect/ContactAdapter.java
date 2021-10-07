package com.example.whatsappdirect;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.Notes>{
    private List<Contact> contactList =new ArrayList<>();
    Context context;
    ContactActivity mActivity;
    public ContactAdapter(Context context,ContactActivity activity) {
        this.context = context;
        this.mActivity=activity;
    }
    @NonNull
    @Override
    public Notes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list,parent,false);
        return new Notes(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final Notes holder, final int position) {
        final Contact modelClass= contactList.get(position);
        holder.name.setText(modelClass.getName());
        holder.phoneno.setText(modelClass.getPhno());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                MainActivity.setContactNumber(modelClass.getPhno().replace("+91",""));

                  mActivity.finish();




            }
        });


    }
    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public void setList(List<Contact> countrylist){
        this.contactList =countrylist;
        notifyDataSetChanged();
    }


    public static class Notes extends RecyclerView.ViewHolder{

        CardView cardView;

        TextView name, phoneno;

        ImageView call,message;

        public Notes(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            phoneno =itemView.findViewById(R.id.phoneno);



            cardView=itemView.findViewById(R.id.cardview);
        }
    }

}