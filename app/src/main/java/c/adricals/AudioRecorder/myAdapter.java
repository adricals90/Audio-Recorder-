package c.adricals.AudioRecorder;

import android.content.Context;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.holder> {

    List<records> data = new LinkedList<>();
    private Context mContext;

    public myAdapter(Context context, List<records> items) {
        this.mContext = context;
        data = items;

    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View newView = inflater.inflate(R.layout.items_view, viewGroup, false);

        holder myVholder = new holder(newView);


        return myVholder;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int i) {


        holder.recName.setText(data.get(i).recordName);
        holder.detailsName.setText(data.get(i).details);
        holder.myImage.setImageResource(R.drawable.ic_music_note_black_24dp);


    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    public records getRecordAt(int position) {
        return data.get(position);
    }
    public class holder extends RecyclerView.ViewHolder {
        TextView recName;
        TextView detailsName;
        ImageView myImage;

        public holder(@NonNull View itemView) {
            super(itemView);

            recName = itemView.findViewById(R.id.recordName);
            detailsName = itemView.findViewById(R.id.recordDetails);
            myImage = itemView.findViewById(R.id.imageV);
        }
    }
}
