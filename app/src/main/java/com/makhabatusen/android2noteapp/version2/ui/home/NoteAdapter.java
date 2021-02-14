package com.makhabatusen.android2noteapp.version2.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makhabatusen.android2noteapp.R;
import com.makhabatusen.android2noteapp.version2.models.Note;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private ArrayList<Note> list;
    private OnItemOnCLickListener listener;


    public NoteAdapter() {
        list =  new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(Note note) {
        list.add(note);
        notifyItemInserted(list.indexOf(note));
    }

    public void setListener(OnItemOnCLickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNote;
        private TextView tvDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNote = itemView.findViewById(R.id.tv_note);
            tvDate = itemView.findViewById(R.id.tv_date);
        }

        public void bind(Note note) {

                tvNote.setText(note.getNote());
                tvDate.setText(note.getCreatedAt());

            // setting background for item view, orange for even and blue for odd numbers
            if (getAdapterPosition() %2==0)
                itemView.setBackgroundResource(R.color.orange);
            else
                itemView.setBackgroundResource(R.color.blue);

            itemView.setOnClickListener(v-> {
                listener.onCLick(getAdapterPosition());
            });

            itemView.setOnLongClickListener(v-> {
                listener.onLongClick(getAdapterPosition());
                return true;
            });
        }
    }
}
