package com.android.gumeoficial.terminosapp;

import android.support.v7.widget.RecyclerView;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;
    public ViewHolder(View itemView) {
        super(itemView);

        mView = itemView;
    }

    public void setDetails(Context ctx, String titulo, String subtitulo, String texto){
        TextView mTitle= mView.findViewById(R.id.rTitle);
        TextView mSubtitle = mView.findViewById(R.id.rSubtitle);
        TextView mTexto = mView.findViewById(R.id.rTexto);
        //Enviar los datos a la vista....
        mTitle.setText(titulo);
        mSubtitle.setText(subtitulo);
        mTexto.setText(texto);
    }
}
