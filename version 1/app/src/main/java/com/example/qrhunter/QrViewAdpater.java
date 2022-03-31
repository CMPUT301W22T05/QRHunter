package com.example.qrhunter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class QrViewAdpater extends FirebaseRecyclerAdapter<Qrdata,QrViewAdpater.myviewholder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public QrViewAdpater(@NonNull FirebaseRecyclerOptions<Qrdata> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Qrdata qrdata) {
        holder.description.setText(qrdata.getDescription());
        holder.lag.setText(qrdata.getLag().toString());
        holder.lat.setText(qrdata.getLat().toString());
        holder.title.setText(qrdata.getTitle());
        holder.worth.setText(qrdata.getWorth());

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_qr_design,parent,false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img1;
        TextView description,title,worth,lag,lat;;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            //img1=itemView.findViewById(R.id.img1);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
            worth=itemView.findViewById(R.id.worth);
            lag= itemView.findViewById(R.id.lag);
            lat = itemView.findViewById(R.id.lat);
        }
    }


}
