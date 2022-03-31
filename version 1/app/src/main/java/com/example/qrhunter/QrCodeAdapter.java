package com.example.qrhunter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.protobuf.StringValue;

import java.util.List;

public class QrCodeAdapter extends RecyclerView.Adapter<QrCodeAdapter.ViewHolder> {
    public List<QrCodes> qr;
    public QrCodeAdapter(List<QrCodes> qr){
        this.qr = qr;

    }
    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_qr_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.description.setText(qr.get(position).getDescription());
        String Longtitude = Double.toString(qr.get(position).getLag());
        String latitude = Double.toString(qr.get(position).getLat());
        holder.lag.setText(Longtitude);
        holder.lat.setText(latitude);
        holder.title.setText(qr.get(position).getTitle());
        String Worth = String.valueOf(qr.get(position).getWorth());
        holder.worth.setText(Worth);

    }

    @Override
    public int getItemCount() {
        return qr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView description,title,worth,lag,lat;;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                mView = itemView;
                title=itemView.findViewById(R.id.title);
                description=itemView.findViewById(R.id.description);
                worth=itemView.findViewById(R.id.worth);
                lag= itemView.findViewById(R.id.lag);
                lat = itemView.findViewById(R.id.lat);
            }
        }
}
