package com.example.unomesq.model;

import com.example.unomesq.util.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Random;

public class Mesa {
    private ArrayList<Jogador> jogadores = new ArrayList<>();
    private Baralho baralho = new Baralho();
    private int minhaVez = 0;

    public Mesa() {
    }

    public void salvar(){
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
        DatabaseReference mesaRef = firebaseRef.child("mesa");

        mesaRef.setValue(this);
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public Baralho getBaralho() {
        return baralho;
    }

    public void setBaralho(Baralho baralho) {
        this.baralho = baralho;
    }

    public int getMinhaVez() {
        return minhaVez;
    }

    public void setMinhaVez(int minhaVez) {
        this.minhaVez = minhaVez;
    }

    public void distribuirCartas(Jogador j){
        Random r = new Random();
        int idx;

        for (int i = 0; i < 5; i++) {
            idx = r.nextInt(baralho.getCartas().size());
            j.getHand().add(baralho.getCartas().get(idx));
            baralho.getCartas().remove(idx);
        }
    }
}
