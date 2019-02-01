package com.example.navigation;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {
    private final ArrayList<cGames> games;
    private int viewRes;
    private Context context;
    private Resources resource;
    private LayoutInflater layoutInflater;
    private View view;
    private final OnItemClickListener listener;

    //Constructeur
    public GamesAdapter(Context context, int textViewResourceId, ArrayList<cGames> game, OnItemClickListener listener)
    {
        this.context = context;
        this.resource = context.getResources();
        this.viewRes = textViewResourceId;
        this.games = game;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.view = null;
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView text;
        ImageView avatar;
        private View view;

        //Constructeur
        public ViewHolder(View v) {
            super(v);
            this.view = v;
            this.name = (TextView) v.findViewById(R.id.name);
            this.text = (TextView) v.findViewById(R.id.text);
            this.avatar = (ImageView) v.findViewById(R.id.avatar);
        }

        //Ajout de l'event OnClick
        public void bind(final cGames item, final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        view = layoutInflater.from(parent.getContext()).inflate(viewRes,parent,false);

        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        cGames planet = games.get(i);

        if(planet!=null){
            viewHolder.name.setText(planet.getName());
            viewHolder.text.setText(planet.getText());
            viewHolder.avatar.setImageBitmap(planet.getAvatar());
        }
        viewHolder.bind(games.get(i), listener);
    }



    @Override
    public int getItemCount() {
        return games.size();
    }

    public interface OnItemClickListener {
        void onItemClick(cGames item);
    }
}


