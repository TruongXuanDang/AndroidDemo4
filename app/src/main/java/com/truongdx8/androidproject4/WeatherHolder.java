package com.truongdx8.androidproject4;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherHolder extends RecyclerView.ViewHolder{
    ImageView ivImage;
    TextView tvDate;
    TextView tvTemperature;

    public WeatherHolder(@NonNull View itemView) {
        super(itemView);
        ivImage = itemView.findViewById(R.id.ivImage);
        tvDate = itemView.findViewById(R.id.tvDate);
        tvTemperature = itemView.findViewById(R.id.tvTemperature);
    }
}
