package com.example.unomesq.model;

import com.example.unomesq.util.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

public class Carta {
    private int cor;
    private int numero;
    private int tipoCarta;
    private int imagem;

    public Carta() {
    }

    public Carta(int cor, int numero, int tipoCarta, int imagem) {
        this.cor = cor;
        this.numero = numero;
        this.tipoCarta = tipoCarta;
        this.imagem = imagem;
    }

    public void salvar(String player, String id){
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
        DatabaseReference cartaRef = firebaseRef.child(player).child(id);

        cartaRef.setValue(this);
    }


    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getTipoCarta() {
        return tipoCarta;
    }

    public void setTipoCarta(int tipoCarta) {
        this.tipoCarta = tipoCarta;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
