package com.example.unomesq.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unomesq.R;
import com.example.unomesq.model.Carta;
import com.example.unomesq.model.Jogador;
import com.example.unomesq.model.Mesa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference referenciaFirebase = FirebaseDatabase.getInstance().getReference();

    private Carta descarte;
    private Mesa mesa;
    private int posi;

    private ImageView ivDescarte;
    private TextView tvNome;
    private TextView tvNomeJogador;

    private ArrayList<ImageView> handView;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mesa.getJogadores().remove(posi);
        mesa.salvar();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNomeJogador = findViewById(R.id.tvNomeJogador);
        inicializaHandTela();

        buscaMesa();

        identificaPlayer();

        ivDescarte = findViewById(R.id.ivCartaDescarte);

        referenciaFirebase.child("descarte").child("carta").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                descarte = snapshot.getValue(Carta.class);
                if (descarte != null) {
                    ivDescarte.setImageDrawable
                            (AppCompatResources.getDrawable
                                    (getApplicationContext(), descarte.getImagem()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void animar(View view) {
        view.animate().scaleX(1.2f);
        view.animate().scaleY(1.2f);
        view.animate().z(100);
    }

    public void jogar(View view) {
        Carta c = mesa.getJogadores().get(posi).getHand().get(Integer.parseInt(view.getTag().toString()));
        mesa.getJogadores().get(posi).getHand().remove(Integer.parseInt(view.getTag().toString()));
        c.salvar("descarte", "carta");

        if (mesa.getMinhaVez() < mesa.getJogadores().size() - 1) {
            mesa.setMinhaVez(mesa.getMinhaVez() + 1);
        } else {
            mesa.setMinhaVez(0);
        }
        mesa.salvar();
    }

    private void identificaPlayer(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Informe seu nome");
        View v = getLayoutInflater().inflate(R.layout.activity_inicial, null);
        dialogo.setView(v);
        dialogo.setCancelable(false);
        dialogo.setPositiveButton("ENTRAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Jogador j = new Jogador(tvNome.getText().toString());
                //distribuir cartas
                mesa.distribuirCartas(j);
                mesa.getJogadores().add(j);
                mesa.salvar();
                posi = mesa.getJogadores().indexOf(j);
                //Atualizar Tela
                atualizaHandTela();
                tvNomeJogador.setText(posi + " - " + tvNome.getText().toString());
            }
        });
        dialogo.create();
        dialogo.show();
        tvNome = v.findViewById(R.id.tv_nome);
    }

    private void inicializaHandTela(){
        handView = new ArrayList<>();

        ImageView ivCarta1 = findViewById(R.id.ivCarta1);
        ImageView ivCarta2 = findViewById(R.id.ivCarta2);
        ImageView ivCarta3 = findViewById(R.id.ivCarta3);
        ImageView ivCarta4 = findViewById(R.id.ivCarta4);
        ImageView ivCarta5 = findViewById(R.id.ivCarta5);

        handView.add(ivCarta1);
        handView.add(ivCarta2);
        handView.add(ivCarta3);
        handView.add(ivCarta4);
        handView.add(ivCarta5);

    }

    private void atualizaHandTela(){

//        ivCarta3.animate().scaleX(1);
//        ivCarta3.animate().scaleY(1);
//        ivCarta3.animate().z(0);
        for (ImageView iv: handView) {
            iv.setVisibility(View.INVISIBLE);
            iv.setClickable(false);
            iv.setAlpha(0.5f);
        }

        for (int i=0; i< mesa.getJogadores().get(posi).getHand().size(); i++) {
            handView.get(i).setImageDrawable
                    (AppCompatResources.getDrawable
                            (getApplicationContext(),
                                    mesa.getJogadores().get(posi).getHand().get(i).getImagem()));
            handView.get(i).setVisibility(View.VISIBLE);
        }

        if (mesa.getMinhaVez() == posi) {
            for (ImageView iv: handView) {
                iv.setClickable(true);
                iv.setAlpha(1f);
            }
        }
    }

    private void buscaMesa(){
        referenciaFirebase.child("mesa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mesa = snapshot.getValue(Mesa.class);
                if (mesa == null){
                    mesa = new Mesa();
                    mesa.salvar();
                } else {
                    if (!mesa.getJogadores().isEmpty()) {
                        atualizaHandTela();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}