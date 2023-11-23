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
        carta = new Carta(Color.RED, 0, 100, R.drawable.r0);
        cartas.add(carta);
        carta = new Carta(Color.RED, 1, 100, R.drawable.r1);
        cartas.add(carta);
        carta = new Carta(Color.RED, 1, 100, R.drawable.r1);
        cartas.add(carta);
        carta = new Carta(Color.RED, 2, 100, R.drawable.r2);
        cartas.add(carta);
        carta = new Carta(Color.RED, 2, 100, R.drawable.r2);
        cartas.add(carta);
        carta = new Carta(Color.RED, 3, 100, R.drawable.r3);
        cartas.add(carta);
        carta = new Carta(Color.RED, 3, 100, R.drawable.r3);
        cartas.add(carta);
        carta = new Carta(Color.RED, 4, 100, R.drawable.r4);
        cartas.add(carta);
        carta = new Carta(Color.RED, 4, 100, R.drawable.r4);
        cartas.add(carta);
        carta = new Carta(Color.RED, 5, 100, R.drawable.r5);
        cartas.add(carta);
        carta = new Carta(Color.RED, 5, 100, R.drawable.r5);
        cartas.add(carta);
        carta = new Carta(Color.RED, 6, 100, R.drawable.r6);
        cartas.add(carta);
        carta = new Carta(Color.RED, 6, 100, R.drawable.r6);
        cartas.add(carta);
        carta = new Carta(Color.RED, 7, 100, R.drawable.r7);
        cartas.add(carta);
        carta = new Carta(Color.RED, 7, 100, R.drawable.r7);
        cartas.add(carta);
        carta = new Carta(Color.RED, 8, 100, R.drawable.r8);
        cartas.add(carta);
        carta = new Carta(Color.RED, 8, 100, R.drawable.r8);
        cartas.add(carta);
        carta = new Carta(Color.RED, 10, 200, R.drawable.rbloq);
        cartas.add(carta);
        carta = new Carta(Color.RED, 10, 200, R.drawable.rbloq);
        cartas.add(carta);
        carta = new Carta(Color.RED, 11, 300, R.drawable.rrev);
        cartas.add(carta);
        carta = new Carta(Color.RED, 11, 300, R.drawable.rrev);
        cartas.add(carta);
        carta = new Carta(Color.RED, 12, 400, R.drawable.rsoma2);
        cartas.add(carta);
        carta = new Carta(Color.RED, 12, 400, R.drawable.rsoma2);
        cartas.add(carta);

        //Amarelo
        carta = new Carta(Color.YELLOW, 0, 100, R.drawable.y0);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 1, 100, R.drawable.y1);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 1, 100, R.drawable.y1);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 2, 100, R.drawable.y2);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 2, 100, R.drawable.y2);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 3, 100, R.drawable.y3);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 3, 100, R.drawable.y3);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 4, 100, R.drawable.y4);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 4, 100, R.drawable.y4);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 5, 100, R.drawable.y5);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 5, 100, R.drawable.y5);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 6, 100, R.drawable.y6);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 6, 100, R.drawable.y6);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 7, 100, R.drawable.y7);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 7, 100, R.drawable.y7);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 8, 100, R.drawable.y8);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 8, 100, R.drawable.y8);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 10, 200, R.drawable.ybloq);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 10, 200, R.drawable.ybloq);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 11, 300, R.drawable.yrev);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 11, 300, R.drawable.yrev);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 12, 400, R.drawable.ysoma2);
        cartas.add(carta);
        carta = new Carta(Color.YELLOW, 12, 400, R.drawable.ysoma2);
        cartas.add(carta);

        //Verde
        carta = new Carta(Color.GREEN, 0, 100, R.drawable.g0);
        cartas.add(carta);
        carta = new Carta(Color.GREEN, 1, 100, R.drawable.g1);
        cartas.add(carta);
        carta = new Carta(Color.GREEN, 1, 100, R.drawable.g1);
        cartas.add(carta);
        carta = new Carta(Color.GREEN, 2, 100, R.drawable.g2);
        cartas.add(carta);
        carta = new Carta(Color.GREEN, 2, 100, R.drawable.g2);
        cartas.add(carta);
        carta = new Carta(Color.GREEN, 3, 100, R.drawable.g3);
        cartas.add(carta);
        carta = new Carta(Color.GREEN, 3, 100, R.drawable.g3);
        cartas.add(carta);
        carta = new Carta(Color.GREEN, 4, 100, R.drawable.g4);
        cartas.add(carta);
        carta = new Carta(Color.GREEN, 4, 100, R.drawable.g4);
        cartas.add(carta);
        carta = new Carta(Color.GREEN, 10, 200, R.drawable.gbloq);
        cartas.add(carta);
        carta = new Carta(Color.GREEN, 10, 200, R.drawable.gbloq);
        cartas.add(carta);
        carta = new Carta(Color.GREEN, 11, 300, R.drawable.grev);
        cartas.add(carta);
        carta = new Carta(Color.GREEN, 11, 300, R.drawable.grev);
        cartas.add(carta);
        carta = new Carta(Color.GREEN, 12, 400, R.drawable.gsoma2);
        cartas.add(carta);
        carta = new Carta(Color.GREEN, 12, 400, R.drawable.gsoma2);
        cartas.add(carta);

        //Azul
        carta = new Carta(Color.BLUE, 0, 100, R.drawable.b0);
        cartas.add(carta);
        carta = new Carta(Color.BLUE, 1, 100, R.drawable.b1);
        cartas.add(carta);
        carta = new Carta(Color.BLUE, 1, 100, R.drawable.b1);
        cartas.add(carta);
        carta = new Carta(Color.BLUE, 2, 100, R.drawable.b2);
        cartas.add(carta);
        carta = new Carta(Color.BLUE, 2, 100, R.drawable.b2);
        cartas.add(carta);
        carta = new Carta(Color.BLUE, 3, 100, R.drawable.b3);
        cartas.add(carta);
        carta = new Carta(Color.BLUE, 3, 100, R.drawable.b3);
        cartas.add(carta);
        carta = new Carta(Color.BLUE, 4, 100, R.drawable.b4);
        cartas.add(carta);
        carta = new Carta(Color.BLUE, 4, 100, R.drawable.b4);
        cartas.add(carta);
        carta = new Carta(Color.BLUE, 10, 200, R.drawable.bbloq);
        cartas.add(carta);
        carta = new Carta(Color.BLUE, 10, 200, R.drawable.bbloq);
        cartas.add(carta);
        carta = new Carta(Color.BLUE, 11, 300, R.drawable.brev);
        cartas.add(carta);
        carta = new Carta(Color.BLUE, 11, 300, R.drawable.brev);
        cartas.add(carta);
        carta = new Carta(Color.BLUE, 12, 400, R.drawable.bsoma2);
        cartas.add(carta);
        carta = new Carta(Color.BLUE, 12, 400, R.drawable.bsoma2);
        cartas.add(carta);

    }

    private void embaralhar(){
        //TODO Criar o método de embaralhamento
    }

    private void distribuir_cartas(){
        //TODO Criar o método de distribuição de cartas
    }

}
