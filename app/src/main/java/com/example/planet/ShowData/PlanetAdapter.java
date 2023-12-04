package com.example.planet.ShowData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planet.AddPlanetData.Planet;
import com.example.planet.FirebaseServices;
import com.example.planet.R;

import java.util.ArrayList;

public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.ViewHolder> {
    private ArrayList<Planet> dataPlanetList;
    private LayoutInflater tInflater;
    private Context context;
    private FirebaseServices fbs;
    public PlanetAdapter(ArrayList<Planet> dataPlanetList , Context context)
    {
        this.context = context;
        this.dataPlanetList = dataPlanetList;
        this.fbs = FirebaseServices.getInstance();
    }

    @NonNull
    @Override
    public PlanetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.planet_info,parent,false);
        return new PlanetAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Planet planet = dataPlanetList.get(position);
        holder.tvPlanetName.setText(planet.getName());
        holder.tvPlanetSize.setText(planet.getSize());
        holder.tvPlanetOrbit.setText(planet.getOrbit());
        holder.tvPlanetPopulation.setText(planet.getPopulation());
    }
    @Override
    public int getItemCount() {
        return dataPlanetList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvPlanetName, tvPlanetSize, tvPlanetOrbit , tvPlanetPopulation;
        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPlanetName = itemView.findViewById(R.id.tvPlanetNamePlanetInfo);
            tvPlanetSize = itemView.findViewById(R.id.tvPlanetSizePlanetInfo);
            tvPlanetOrbit = itemView.findViewById(R.id.tvPlanetOrbitPlanetInfo);
            tvPlanetPopulation = itemView.findViewById(R.id.tvPlanetPopulationPlanetInfo);
        }
    }
}
