package com.example.android.rangga_1202154227_modul6;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by ranggaardi on 4/1/2018.
 */

public class AdapterP extends  RecyclerView.Adapter<AdapterP.ViewHolder> {
    private List<Database> list;
    private Context con;


    public AdapterP(List<Database> list, Context con) {
        this.list = list;
        this.con = con;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(con).inflate(R.layout.feed, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Database current = list.get(position);
        String [] user = current.user.split("@");
        holder.user.setText(user[0]);
        holder.user.setTag(current.getKey());
        holder.title.setText(current.getTitle());
        holder.caption.setText(current.getCaption());
        holder.caption.setTag(current.getImage());
        Glide.with(con).load(current.getImage()).placeholder(R.drawable.add_image).override(450, 450).into(holder.image);
    }



    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image; TextView user, title, caption;
        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.postgambarnya);
            user = itemView.findViewById(R.id.postpengupload);
            title = itemView.findViewById(R.id.postjudul);
            caption = itemView.findViewById(R.id.postdeskripsi);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent pindah = new Intent(con, PostActivity.class);
                    pindah.putExtra("user", user.getText());
                    pindah.putExtra("key", user.getTag().toString());
                    pindah.putExtra("title", title.getText());
                    pindah.putExtra("caption", caption.getText());
                    pindah.putExtra("image", caption.getTag().toString());
                    con.startActivity(pindah);
                }
            });
        }
    }
}
