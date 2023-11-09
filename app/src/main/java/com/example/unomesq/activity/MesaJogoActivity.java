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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unomesq.R;
import com.example.unomesq.model.Jogador;
import com.example.unomesq.model.Mesa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MesaJogoActivity extends AppCompatActivity {
    private DatabaseReference referenciaFirebase = FirebaseDatabase.getInstance().getReference();

    private RecyclerView rvCartasMao;
    private TextView tvNome;

    private Mesa mesa;
    private int posi = 0;//voltar aqui para arrumar a gambiarra

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa_jogo);

        rvCartasMao = findViewById(R.id.rv_cartas_mao);

        buscaMesa();

        identificaPlayer();
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


}