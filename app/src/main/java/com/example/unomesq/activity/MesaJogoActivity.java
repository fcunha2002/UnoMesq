package com.example.unomesq.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unomesq.R;
import com.example.unomesq.model.Carta;
import com.example.unomesq.model.Jogador;
import com.example.unomesq.model.Jogos;
import com.example.unomesq.model.Mesa;
import com.example.unomesq.util.RecyclerItemClickListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MesaJogoActivity extends AppCompatActivity {
    private DatabaseReference referenciaFirebase = FirebaseDatabase.getInstance().getReference();

    private RecyclerView rvCartasMao;
    private TextView tvNome;
    private TextView tvNomePlayer1;
    private TextView tvNomePlayer2;
    private TextView tvNomePlayer3;
    private ImageView ivDescarte;
    private ImageView ivBaralho;

    private Jogos jogos;
    private Mesa mesa;
    private int mesaID = -1;
    private int posi;
    private int posiFrente, posiEsquerda, posiDireita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa_jogo);

        ivBaralho = findViewById(R.id.iv_baralho);
        ivDescarte = findViewById(R.id.iv_carta_descarte);

        defineMesa();

        inicializaRVMao();

        identificaPlayer();

    }

    private void inicializaRVMao(){
        rvCartasMao = findViewById(R.id.rv_cartas_mao);
        rvCartasMao.setVisibility(View.INVISIBLE);

        //Adicionando tratamento de clique. A carta clicada é a carta a ser descartada
        //TODO Falta validar a jogada
        rvCartasMao.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                rvCartasMao,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (rvCartasMao.isClickable()) {
                            Carta carta =
                                    mesa.getJogadores().get(posi).getHand().get(position);
                            int result = mesa.validarJogada(carta);

                            if (result == 0) {
                                mesa.getJogadores().get(posi).getHand().remove(position);
                                mesa.setDescarte(carta);

                                mesa.salvar(mesaID);
                            } else if (result == 1){
                                escolherCores();
//                                mesa.getJogadores().get(posi).getHand().remove(position);
//                                mesa.setDescarte(carta);
//
//                                mesa.salvar(mesaID);
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Jogada inválida", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Toast.makeText(
                                getApplicationContext(),
                                "LONG CLICK",
                                Toast.LENGTH_SHORT
                        ).show();
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }
        ));
    }

    private void defineMesa(){
        ValueEventListener vel = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                jogos = snapshot.getValue(Jogos.class);
                if (jogos == null){
                    jogos = new Jogos();
                    mesa = new Mesa();
                    mesa.atribuiId();
                    mesaID = 0;
                    jogos.getMesas().add(mesa);
                    jogos.salvar();
                } else {
                    if (mesaID == -1) {
                        for (Mesa m: jogos.getMesas()) {
                            if (m.getJogadores().size() < 4){
                                mesaID = jogos.getMesas().indexOf(m);
                            }
                        }
                        if (mesaID == -1){
                            mesa = new Mesa();
                            mesa.atribuiId();
                            jogos.getMesas().add(mesa);
                            mesaID = jogos.getMesas().size() - 1;
                            jogos.salvar();
                        }
                    }
                    mesa = jogos.getMesas().get(mesaID);
                    if (!mesa.getJogadores().isEmpty()) {
                        atualizaHandTela();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        referenciaFirebase.child("jogos").addValueEventListener(vel);
    }

    private void escolherCores(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("ESCOLHA A COR");
        dialogo.setCancelable(true);
        dialogo.create();
        dialogo.show();
    }

    private void mostraVencedor(Jogador j){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        View v = getLayoutInflater().inflate(R.layout.tela_ganhador, null);
        dialogo.setView(v);
        TextView tv = v.findViewById(R.id.tv_vencedor);
        tv.setText(j.getNome());
        dialogo.setCancelable(true);
        dialogo.create();
        dialogo.show();
    }

    private void verificaVencedor(){
        for (Jogador j: mesa.getJogadores()) {
            if (j.getHand().size() == 0){
                mostraVencedor(j);
                jogos.getMesas().remove(mesa);
                jogos.salvar();
            }
        }
    }

    private void atualizaHandTela(){

        //VerificarVencedor
        verificaVencedor();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvCartasMao.setLayoutManager(layoutManager);
        rvCartasMao.setAdapter(new MaoAdapter(getApplicationContext()
                , mesa.getJogadores().get(posi).getHand()));

        rvCartasMao.setClickable(false);
        rvCartasMao.setAlpha(0.25f);
        ivBaralho.setClickable(false);

        if (mesa.getMinhaVez() == posi) {
            rvCartasMao.setClickable(true);
            rvCartasMao.setAlpha(1f);
            ivBaralho.setClickable(true);
        }

        inicializaPosicoes();

        tvNomePlayer1 = findViewById(R.id.tv_nome_player1);
        tvNomePlayer2 = findViewById(R.id.tv_nome_player2);
        tvNomePlayer3 = findViewById(R.id.tv_nome_player3);

        if(mesa.getJogadores().size()>1) {
            tvNomePlayer1.setText(mesa.getJogadores().get(posiFrente).getNome());
        }
        if(mesa.getJogadores().size()>2) {
            tvNomePlayer2.setText(mesa.getJogadores().get(posiEsquerda).getNome());
        }
        if(mesa.getJogadores().size()>3) {
            tvNomePlayer3.setText(mesa.getJogadores().get(posiDireita).getNome());
        }

        ivDescarte.setImageDrawable
                (AppCompatResources.getDrawable
                        (getApplicationContext(), mesa.getDescarte().getImagem()));

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
                mesa.salvar(mesaID);
                posi = mesa.getJogadores().indexOf(j);

                //Atualizar Tela
                atualizaHandTela();
                rvCartasMao.setVisibility(View.VISIBLE);

                //tvNomeJogador.setText(posi + " - " + tvNome.getText().toString());
            }
        });
        dialogo.create();
        dialogo.show();
        tvNome = v.findViewById(R.id.tv_nome);
    }

    private void inicializaPosicoes(){
        posiFrente = mesa.jogFrente(posi);
        posiEsquerda = mesa.jogEsquerda(posi);
        posiDireita = mesa.jogDireita(posi);
    }

    public void comprar(View view) {
        mesa.comprarCarta(mesa.getJogadores().get(posi));
        mesa.salvar(mesaID);
    }

}