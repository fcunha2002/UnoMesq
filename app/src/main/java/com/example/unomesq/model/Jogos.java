package com.example.unomesq.model;

import com.example.unomesq.util.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class Jogos {
    private ArrayList<Mesa> mesas = new ArrayList<>();

    public Jogos() {
    }

    public void salvar(){
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
        DatabaseReference jogosRef = firebaseRef.child("jogos");

        jogosRef.setValue(this);
    }

    public ArrayList<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(ArrayList<Mesa> mesas) {
        this.mesas = mesas;
    }
}
