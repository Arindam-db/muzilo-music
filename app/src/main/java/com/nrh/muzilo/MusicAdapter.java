package com.nrh.muzilo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nrh.muzilo.R;

import java.util.ArrayList;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {

    private List<Music> musicList;
    private List<Music> filteredList;

    public MusicAdapter(List<Music> musicList) {
        this.musicList = musicList;
        this.filteredList = new ArrayList<>(musicList); // Initialize filtered list
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music, parent, false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        Music music = filteredList.get(position);
        holder.title.setText(music.getTitle());
        holder.artist.setText(music.getArtist());
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void filter(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(musicList); // Show all items if query is empty
        } else {
            for (Music music : musicList) {
                if (music.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        music.getArtist().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(music);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class MusicViewHolder extends RecyclerView.ViewHolder {
        TextView title, artist;

        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            artist = itemView.findViewById(R.id.artist);
        }
    }
}
