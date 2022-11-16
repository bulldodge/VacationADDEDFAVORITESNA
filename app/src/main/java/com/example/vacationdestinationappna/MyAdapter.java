package com.example.vacationdestinationappna;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private ArrayList<VacationDestination> destinationList;

    public MyAdapter(){
        destinationList = Database.getData();
    }
    //Creates an empty view of a single row
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vacation_rowitem, parent, false);

        System.out.println("DONE CREATING A SINGLE ROW'S VIEW");

        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return destinationList.size();
    }
    //BINDS data to an empty row view
    //position - index in the list that you want to show
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        VacationDestination vd =destinationList.get(position);
        holder.setData(vd, position);
        System.out.println("DONE POPULATING A ROW: " + position + " " + vd.getName());
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        // handles to the views inside one row
        private ImageView imvPicture;
        private TextView tvName;
        private Boolean favStat;
        private ImageView imvDelete;
        private ImageView imvMakeCopy;
        private ImageView imvFav;

        private int currentPositionInList = -1;
        private VacationDestination currentDest = null;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imvPicture = itemView.findViewById(R.id.imvPlace);
            tvName = itemView.findViewById(R.id.tvPlaceName);
            imvDelete = itemView.findViewById(R.id.imvDelete);
            imvMakeCopy = itemView.findViewById(R.id.imvMakeCopy);
            imvFav = itemView.findViewById(R.id.imvFav);

            imvDelete.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                destinationList.remove(currentPositionInList);
                notifyItemRemoved(currentPositionInList);
                notifyItemRangeChanged(currentPositionInList, destinationList.size());

                }
            });
            imvMakeCopy.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    destinationList.add(currentPositionInList, currentDest);
                    notifyItemInserted(currentPositionInList);
                    notifyItemRangeChanged(currentPositionInList, destinationList.size());

                }
            });
            imvFav.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    setBOOL();

                }
            });
        }
        public void setData(VacationDestination vd, int position){
            imvPicture.setImageResource(vd.getImageId());
            imvFav.setImageResource(vd.getHeart());
            favStat = vd.getFT();
            tvName.setText(vd.getName());
            currentPositionInList = position;
            currentDest = vd;


        }
        public void setBOOL(){
            favStat = currentDest.setFT();
        }
    }
}
