package com.google.sample.cloudvision;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by Gupta on 31-03-2018.
 */

public class AuthorityAdapter extends RecyclerView.Adapter<AuthorityAdapter.ViewHolder> {
    Context context;
    List<User> UserList;
    private DatabaseReference dbref= FirebaseDatabase.getInstance().getReference();

    public AuthorityAdapter(Context context, List<User> TempList) {

        this.UserList = TempList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cb = (CheckBox) view.findViewById(R.id.cb);

        holder.cb.setChecked(false);

        holder.cb
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        //   if(isChecked==true){
//                            String childr=dbref.getKey();
//                            dbref.child(childr).child("Status").setValue("done");
                        holder.status.setText("done");
                        holder.cb.setEnabled(false);}
                    //}
                });

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final User UserInfo = UserList.get(position);
        dbref = FirebaseDatabase.getInstance().getReference();

        holder.date.setText(UserInfo.getDate());
        holder.location.setText(UserInfo.getLocation());
        holder.prob.setText(UserInfo.getProb());
        holder.status.setText(UserInfo.getStatus());

        //Loading image from Glide library.
        if(UserInfo.getImages()!=null)
            Glide.with(context).load(Uri.parse(UserInfo.getImages())).into(holder.iv);

    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date,location,prob,status;
        public ImageView iv;
        public CheckBox cb;
        public ViewHolder(View itemView) {
            super(itemView);
            date=(TextView) itemView.findViewById(R.id.date);
            location=(TextView) itemView.findViewById(R.id.location);
            prob=(TextView) itemView.findViewById(R.id.problem);
            status=(TextView) itemView.findViewById(R.id.status);
            iv=(ImageView) itemView.findViewById(R.id.iv);
            cb=(CheckBox) itemView.findViewById(R.id.cb);
        }
    }
}
