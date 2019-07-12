package com.example.recycleviewpractice;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jointfragment.R;

import java.util.List;

//step1: extend  RecyclerView.Adapter  NB: do not generate yet
public class PersonAdaptor extends RecyclerView.Adapter<PersonAdaptor.ViewHolder>{

    private List<Person> item;

    public PersonAdaptor(List<Person> item) {
        this.item = item;
    }

    @NonNull
    @Override
//here we are creating our inflator
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        here we are inflating out views, and this woill generally b the main procedure to inflate views
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.person_item,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Person person= item.get(position);


        holder.tvName.setText(person.getName());
        holder.tvPhone.setText(person.getNumber());
        Glide.with(holder.ivPhoto.getContext()).load(person.getImage()).into(holder.ivPhoto);

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    //Step2;    create the vview holder &ext RecyclerView.ViewHolder and generate
    public class ViewHolder extends RecyclerView.ViewHolder{


        ImageView ivPhoto;
        TextView tvName, tvPhone;

//Step3:        pass the viewHolder in the Adapter up in the first line where we did the first extend
//Step 4:    generate the main extend at the top.
    private ViewHolder(@NonNull View itemView) {
        super(itemView);
        ivPhoto = itemView.findViewById(R.id.iv_photo);
        tvName = itemView.findViewById(R.id.tv_name);
        tvPhone = itemView.findViewById(R.id.tv_phone);


    }
}

}
