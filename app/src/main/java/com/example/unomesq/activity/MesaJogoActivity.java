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

    private Mesa mesa;
    private int posi;
    private int posiA, posiB, posiC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa_jogo);

        buscaMesa();

        inicializaRVMao();

        identificaPlayer();

        inicializaCartaDescarte();

    }

    private void inicializaRVMao(){
        rvCartasMao = findViewById(R.id.rv_cartas_mao);
        //Adicionando tratamento de clique
        rvCartasMao.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                rvCartasMao,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Carta carta =
                                mesa.getJogadores().get(posi).getHand().get(position);
                        mesa.getJogadores().get(posi).getHand().remove(position);
                        carta.salvar("descarte", "carta");

                        if (mesa.getMinhaVez() < mesa.getJogadores().size() - 1) {
                            mesa.setMinhaVez(mesa.getMinhaVez() + 1);
                        } else {
                            mesa.setMinhaVez(0);
                        }
                        mesa.salvar();

                        Toast.makeText(getApplicationContext(), "CLICK:", Toast.LENGTH_SHORT).show();
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

    private void inicializaCartaDescarte(){
        ivDescarte = findViewById(R.id.iv_carta_descarte);

        referenciaFirebase.child("descarte").child("carta").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Carta descarte = snapshot.getValue(Carta.class);
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

    private void atualizaHandTela(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvCartasMao.setLayoutManager(layoutManager);
        rvCartasMao.setAdapter(new MaoAdapter(getApplicationContext()
                , mesa.getJogadores().get(posi).getHand()));

        inicializaPosicoes();

        tvNomePlayer1 = findViewById(R.id.tv_nome_player1);
        tvNomePlayer2 = findViewById(R.id.tv_nome_player2);
        tvNomePlayer3 = findViewById(R.id.tv_nome_player3);

        if(mesa.getJogadores().size()>1) {
            tvNomePlayer1.setText(mesa.getJogadores().get(posiA).getNome());
        }
        if(mesa.getJogadores().size()>2) {
            tvNomePlayer2.setText(mesa.getJogadores().get(posiB).getNome());
        }
        if(mesa.getJogadores().size()>3) {
            tvNomePlayer3.setText(mesa.getJogadores().get(posiC).getNome());
        }

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
                //tvNomeJogador.setText(posi + " - " + tvNome.getText().toString());
            }
        });
        dialogo.create();
        dialogo.show();
        tvNome = v.findViewById(R.id.tv_nome);
    }

    private void inicializaPosicoes(){
        switch (posi) {
            case 0: {posiA = 1; posiB = 2; posiC = 3; break;}
            case 1: {posiA = 0; posiB = 3; posiC = 2; break;}
            case 2: {posiA = 3; posiB = 0; posiC = 1; break;}
            case 3: {posiA = 0; posiB = 1; posiC = 2; break;}

        }


    }

}