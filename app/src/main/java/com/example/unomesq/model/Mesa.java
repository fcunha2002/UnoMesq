package com.example.unomesq.model;

import com.example.unomesq.util.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Random;

public class Mesa {
    private String id;
    private ArrayList<Jogador> jogadores = new ArrayList<>();
    private Baralho baralho;
    private int minhaVez = 0;
    private boolean sentido = true;
    private Carta descarte;

    private final int QTD_CARTAS_INICIAL = 7;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Mesa() {
        this.baralho = new Baralho();
        primeiroDescarte();
    }

    public void salvar(int idx){
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
        DatabaseReference mesaRef = firebaseRef.child("jogos").child("mesas").child(String.valueOf(idx));

        mesaRef.setValue(this);
    }

    public void atribuiId(){
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();

        //Gera uma chave única (ID) com o comando push()
        DatabaseReference mesaRef = firebaseRef.child("jogos").child("mesa").push();
        // Pega a chave única (ID) gerada com o comando push()
        this.id = mesaRef.getKey();
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

    public Carta getDescarte() {
        return descarte;
    }

    public void setDescarte(Carta descarte) {
        this.descarte = descarte;
    }

    public boolean isSentido() {
        return sentido;
    }

    public void setSentido(boolean sentido) {
        this.sentido = sentido;
    }

    private void primeiroDescarte(){
        Random r = new Random();
        int idx = r.nextInt(baralho.getCartas().size());
        descarte = baralho.getCartas().get(idx);
        baralho.getCartas().remove(idx);
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

    public boolean validarJogada(Carta jogada){

        if ((this.descarte.getCor() == jogada.getCor()) ||
        (this.descarte.getNumero() == jogada.getNumero())){
            if (jogada.getNumero() == 11){
                this.sentido = !this.sentido;
            }
            defineProximo();
            return true;
        }

        return false;
    }

    private void defineProximo(){
        if (this.sentido) {
            if (this.minhaVez < this.jogadores.size() - 1) {
                this.minhaVez = this.minhaVez + 1;
            } else {
                this.minhaVez = 0;
            }
        } else {
            if (this.minhaVez == 0){
                this.minhaVez = this.jogadores.size() - 1;
            } else {
                this.minhaVez = this.minhaVez - 1;
            }
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
