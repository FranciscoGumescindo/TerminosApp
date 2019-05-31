package com.android.gumeoficial.terminosapp;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.gumeoficial.terminosapp.model.Terminos;
import com.google.firebase.database.DatabaseReference;
import android.util.Log;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import android.widget.Toolbar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    DatabaseReference mRootReference;
    Button mButtonSubirDatosFirebase,Regresar;
    EditText Titulo, Subtitulo, Texto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Declaracion de la orientacion de la pantalla
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        mButtonSubirDatosFirebase = findViewById(R.id.Guardar);
        mButtonSubirDatosFirebase.setOnClickListener(this);
        Titulo = findViewById(R.id.Titulo);
        Subtitulo = findViewById(R.id.Subtitulo);
        Texto = findViewById(R.id.Texto);
        Regresar = findViewById(R.id.Regresar);
        Regresar.setOnClickListener(this);

        mRootReference = FirebaseDatabase.getInstance().getReference();

        solicitarDatosFirebase();

    }

    private void solicitarDatosFirebase() {
        mRootReference.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    mRootReference.child("Usuario").child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Terminos user = snapshot.getValue(Terminos.class);
                            String titulo = user.getTitulo();
                            String subtitulo = user.getSubtitulo();
                            String texto = user.getTexto();

                            Log.e("Titulo:", "" + titulo);
                            Log.e("Subtitulo:", "" + subtitulo);
                            Log.e("Texto:", "" + texto);
                            Log.e("Datos:", "" + snapshot.getValue());
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void cargarDatosFirebase(String titulo, String subtitulo, String texto) {

        Map<String, Object> datosUsuario = new HashMap<>();
        datosUsuario.put("titulo", titulo);
        datosUsuario.put("subtitulo", subtitulo);
        datosUsuario.put("texto", texto);

        mRootReference.child("Definicion").push().setValue(datosUsuario);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.Guardar:
                String titulo = Titulo.getText().toString();
                String subtitulo = Subtitulo.getText().toString();
                String texto = Texto.getText().toString();
                cargarDatosFirebase(titulo, subtitulo, texto);
                limpiarCajas();
                Mensaje();
                break;
            case R.id.Regresar:
                Intent intents = new Intent(MainActivity.this, Posts.class);
                startActivity(intents);
                finish();
                break;
        }

    }

    private void limpiarCajas() {
        Titulo.setText("");
        Subtitulo.setText("");
        Texto.setText("");
    }

    private void Mensaje(){
        Toast.makeText(this, "Registro realizado con exito!", Toast.LENGTH_SHORT).show();
    }



}
