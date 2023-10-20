package com.example.unomesq.model;

import com.example.unomesq.util.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class Jogador {
    private String nome;
    private ArrayList<Carta> hand = new ArrayList<>();

    public Jogador() {
    }

    public Jogador(String nome) {
        this.nome = nome;
    }

    public void salvar(){
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
        DatabaseReference jogadorRef = firebaseRef.child("jogadores");

        jogadorRef.setValue(this);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Carta> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Carta> hand) {
        this.hand = hand;
    }
}
