package c.adricals.AudioRecorder.MainActivity;

import android.content.Context;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import c.adricals.AudioRecorder.R;

public class myAdapter extends RecyclerView.Adapter<myAdapter.holder> {

    List<Record> data ;
    private Context mContext;
    private OnItemClickListener mListener;




    public myAdapter(Context context, List<Record> items) {
        this.mContext = context;
        data = items;

    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View newView = inflater.inflate(R.layout.items_view, viewGroup, false);

        holder myVholder = new holder(newView, mListener);


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

    public Record getRecordAt(int position) {
        return data.get(position);
    }

    public static class holder extends RecyclerView.ViewHolder  {
        TextView recName;
        TextView detailsName;
        ImageView myImage;

        public holder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            recName = itemView.findViewById(R.id.recordName);
            detailsName = itemView.findViewById(R.id.recordDetails);
            myImage = itemView.findViewById(R.id.imageV);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }


    }

}
