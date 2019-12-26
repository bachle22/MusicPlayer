package edu.uit.MusicPlayer.Adapter;

import android.app.Notification;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import edu.uit.MusicPlayer.Adapter.NotificationPlayback;
import edu.uit.MusicPlayer.Model.Baihat;
import edu.uit.MusicPlayer.R;

import java.util.ArrayList;

public class PlaynhacAdapter extends  RecyclerView.Adapter<PlaynhacAdapter.ViewHolder>{
    Context context;
    ArrayList<Baihat> mangbaihat;

    public PlaynhacAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_play_bai_hat,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat=mangbaihat.get(position);
        holder.txttencasi.setText(baihat.getCasi());
        holder.txtindex.setText(position+1+"");
        holder.txttenbaihat.setText(baihat.getTenbaihat());
    }



    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtindex,txttenbaihat,txttencasi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttencasi=itemView.findViewById(R.id.textviewplaynhactencasi);
            txtindex=itemView.findViewById(R.id.textviewplayindex);
            txttenbaihat=itemView.findViewById(R.id.textviewplaynhactenbaihat);

        }
    }
}
