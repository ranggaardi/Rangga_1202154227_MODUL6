package com.example.android.rangga_1202154227_modul6;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ranggaardi on 4/1/2018.
 */

public class AdapterC extends RecyclerView.Adapter<AdapterC.ViewHolder> {
    Context con;
    List<DatabaseCo> list;

    public AdapterC(Context con, List<DatabaseCo> list) {
        this.con = con;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(con).inflate(R.layout.comment, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DatabaseCo cur = list.get(position);
        holder.yangkomen.setText(cur.getYangkomen());
        holder.komennya.setText(cur.getKomennya());

    }

    @Override
    public int getItemCount() {
        return list.size();

    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView yangkomen, komennya;

        public ViewHolder(View itemView) {
            super(itemView);
            yangkomen = itemView.findViewById(R.id.yangkomen);
            komennya = itemView.findViewById(R.id.komennya);
        }
    }
}

