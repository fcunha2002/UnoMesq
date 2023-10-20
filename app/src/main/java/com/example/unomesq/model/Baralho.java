package com.example.unomesq.model;

import android.graphics.Color;

import com.example.unomesq.R;

import java.util.ArrayList;

public class Baralho {
    private ArrayList<Carta> cartas = new ArrayList<>();

    public Baralho() {
        inicializaBaralho();
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    private void inicializaBaralho(){
        Carta carta;
        //Vermelho
        carta = new Carta(Color.RED, 0, 0, R.drawable.r0);
        cartas.add(carta);
        carta = new Carta(Color.RED, 1, 1, R.drawable.r1);
        cartas.add(carta);
        carta = new Carta(Color.RED, 1, 1, R.drawable.r1);
        cartas.add(carta);
        carta = new Carta(Color.RED, 2, 2, R.drawable.r2);
        cartas.add(carta);
        carta = new Carta(Color.RED, 2, 2, R.drawable.r2);
        cartas.add(carta);
        carta = new Carta(Color.RED, 3, 3, R.drawable.r3);
        cartas.add(carta);
        carta = new Carta(Color.RED, 3, 3, R.drawable.r3);
        cartas.add(carta);
        carta = new Carta(Color.RED, 4, 4, R.drawable.r4);
        cartas.add(carta);
        carta = new Carta(Color.RED, 4, 4, R.drawable.r4);
        cartas.add(carta);

        //Amarelo
        carta = new Carta(Color.YELLOW, 0, 0, R.drawable.y0);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 1, 1, R.drawable.y1);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 1, 1, R.drawable.y1);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 2, 2, R.drawable.y2);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 2, 2, R.drawable.y2);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 3, 3, R.drawable.y3);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 3, 3, R.drawable.y3);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 4, 4, R.drawable.y4);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 4, 4, R.drawable.y4);
        cartas.add(carta);

        //Verde
        carta = new Carta(Color.GREEN, 0, 0, R.drawable.g0);
        cartas.add(carta);
        carta = new Carta(Color.GREEN, 1, 1, R.drawable.g1);
        cartas.add(carta);
        carta = new Carta(Color.GREEN, 1, 1, R.drawable.g1);
        cartas.add(carta);
        carta = new Carta(Color.GREEN, 2, 2, R.drawable.g2);
        cartas.add(carta);
        carta = new Carta(Color.GREEN, 2, 2, R.drawable.g2);
        cartas.add(carta);
        carta = new Carta(Color.GREEN, 3, 3, R.drawable.g3);
        cartas.add(carta);
        carta = new Carta(Color.GREEN, 3, 3, R.drawable.g3);
        cartas.add(carta);
        carta = new Carta(Color.GREEN, 4, 4, R.drawable.g4);
        cartas.add(carta);
        carta = new Carta(Color.GREEN, 4, 4, R.drawable.g4);
        cartas.add(carta);

        //Azul
        carta = new Carta(Color.BLUE, 0, 0, R.drawable.b0);
        cartas.add(carta);
        carta = new Carta(Color.BLUE, 1, 1, R.drawable.b1);
        cartas.add(carta);
        carta = new Carta(Color.BLUE, 1, 1, R.drawable.b1);
        cartas.add(carta);
        carta = new Carta(Color.BLUE, 2, 2, R.drawable.b2);
        cartas.add(carta);
        carta = new Carta(Color.BLUE, 2, 2, R.drawable.b2);
        cartas.add(carta);
        carta = new Carta(Color.BLUE, 3, 3, R.drawable.b3);
        cartas.add(carta);
        carta = new Carta(Color.BLUE, 3, 3, R.drawable.b3);
        cartas.add(carta);
        carta = new Carta(Color.BLUE, 4, 4, R.drawable.b4);
        cartas.add(carta);
        carta = new Carta(Color.BLUE, 4, 4, R.drawable.b4);
        cartas.add(carta);

    }

    private void embaralhar(){
        //TODO Criar o método de embaralhamento
    }

    private void distribuir_cartas(){
        //TODO Criar o método de distribuição de cartas
    }

}
