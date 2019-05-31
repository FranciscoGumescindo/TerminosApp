package com.android.gumeoficial.terminosapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.gumeoficial.terminosapp.model.Terminos;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Posts extends AppCompatActivity implements View.OnClickListener{
    //Declaracion de las variables respecto a la galeria...
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    private Button btnRegistrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);


        //Casteo de las variables para el uso de la galeria
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Crreacion de variable y referencia a base de datos en Firebase...
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef= mFirebaseDatabase.getReference("Definicion");
        //Metodo para el boton de salida...

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(this);


    }
    @Override
    protected void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<Terminos, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Terminos, ViewHolder>(
                        Terminos.class,
                        R.layout.row,
                        ViewHolder.class,
                        mRef
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Terminos model, int position) {

                        viewHolder.setDetails(getApplicationContext(),model.getTitulo(),model.getSubtitulo(),model.getTexto());


                    }
                };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnRegistrar:
                Intent intents = new Intent(Posts.this, MainActivity.class);
                startActivity(intents);
                finish();
                break;
        }
    }
}
