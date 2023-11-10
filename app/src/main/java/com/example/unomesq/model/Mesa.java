package com.example.unomesq.model;

import com.example.unomesq.util.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Random;

public class Mesa {
    private ArrayList<Jogador> jogadores = new ArrayList<>();
    private Baralho baralho = new Baralho();
    private int minhaVez = 0;
    private boolean Sentido = true;
    private Carta descarte;

    private final int QTD_CARTAS_INICIAL = 7;

    public Carta getDescarte() {
        return descarte;
    }

    public void setDescarte(Carta descarte) {
        this.descarte = descarte;
    }

    public boolean getSentido() {
        return Sentido;
    }

    public void setSentido(boolean sentido) {

        Sentido = Sentido;
    }

    public Mesa(boolean sentido) {
        Sentido = sentido;
    }

    public Mesa() {
    }

    public void validarjogada(){



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

        for (int i = 0; i < QTD_CARTAS_INICIAL; i++) {
            idx = r.nextInt(baralho.getCartas().size());
            j.getHand().add(baralho.getCartas().get(idx));
            baralho.getCartas().remove(idx);
        }
    }

    public void comprarCarta(Jogador j){
        Random r = new Random();
        int idx;

        idx = r.nextInt(baralho.getCartas().size());
        j.getHand().add(baralho.getCartas().get(idx));
        baralho.getCartas().remove(idx);
    }

    public int jogFrente(int posi){
        switch (this.getJogadores().size()) {
            case 2: {
                if (posi == 0) return 1;
                else return 0;
            }
            case 3: {
                if (posi == 0) return 2;
                else if (posi == 1) return 0;
                else return 1;
            }
            case 4:{
                if (posi == 0) return 2;
                else if (posi == 1) return 3;
                else if (posi == 2) return 0;
                else return 1;
            }
        }
        return -1;
    }

    public int jogEsquerda(int posi){
        switch (this.getJogadores().size()) {
            case 3: {
                if (posi == 0) return 1;
                else if (posi == 1) return 2;
                else return 0;
            }
            case 4:{
                if (posi == 0) return 1;
                else if (posi == 1) return 2;
                else if (posi == 2) return 3;
                else return 0;
            }
        }
        return -1;
    }

    public int jogDireita(int posi){
        switch (this.getJogadores().size()) {
            case 4:{
                if (posi == 0) return 3;
                else if (posi == 1) return 0;
                else if (posi == 2) return 1;
                else return 2;
            }
        }
        return -1;
    }

}
