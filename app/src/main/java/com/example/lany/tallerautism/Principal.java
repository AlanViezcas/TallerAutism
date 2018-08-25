package com.example.lany.tallerautism;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void onClick(View view){
        Intent inDetalle = new Intent
                (this,TarjetasGridView.class);
        startActivity(inDetalle);
    }

    public void onClickNt(View view){
        Intent inDetalle = new Intent
                (this,NuevaTarjeta.class);
        startActivity(inDetalle);
    }

    public void onClickBlue(View view){
        Intent inDetalle = new Intent
                (this, Bluetooth.class);
        startActivity(inDetalle);
    }
}
