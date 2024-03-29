package com.example.firebaseapp.adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firebaseapp.R;
import com.example.firebaseapp.Upload;

import java.util.ArrayList;

public class ImagemAdapter extends RecyclerView.Adapter<ImagemAdapter.ImageVH> {
    private Context context;
    private ArrayList <Upload> listaUploads;

    private OnItemClickListener listener;

    public void setListener ( OnItemClickListener listener ){
        this.listener = listener;
    }

    public ImagemAdapter(Context c, ArrayList<Upload> l){
        this.context = c;
        this.listaUploads = l;
    }


    @NonNull
    @Override
    public ImageVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.image_item,parent, false);

        return new ImageVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageVH holder, int position) {
        Upload upload = listaUploads.get(position);
        holder.textNome.setText(upload.getNomeImagem());
        //setar a imagem -> Glide
        Glide.with(context)
                .load(upload.getUrl())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return listaUploads.size();
    }

    public class ImageVH extends  RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView textNome;
        ImageView imageView;

        public ImageVH(@NonNull View itemView) {
            super(itemView);

            textNome = itemView.findViewById(R.id.image_item_nome);
            imageView = itemView.findViewById(R.id.image_item_imageview);
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Selecionar ação");

            MenuItem deletar = menu.add(0,1,1, "Deletar");
            menu.add(0,2,2, "Atualizar");
            //evento de clique na opcao deletar
            deletar.setOnMenuItemClickListener(menuItem -> {
                if(listener!=null){
                    int position = getAdapterPosition();
                    listener.onDeleteClick(position);
                }
                return true;
        });
    }
    }
    public interface OnItemClickListener {
        void onDeleteClick(int position);

        void onUpdateClick(int position);
    }
}


