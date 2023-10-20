package com.example.unomesq.util;

//import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebase {
    private static DatabaseReference database;
    //private static FirebaseAuth autenticacao;

    //retorna a instância do FirabaseDatabase
    public static DatabaseReference getFirebaseDatabase(){
        if (database == null) {
            database = FirebaseDatabase.getInstance().getReference();
        }
        return database;
    }

//    //retorna a instância do FirebaseAuth
//    public static FirebaseAuth getFirebaseAutenticacao(){
//        if (autenticacao == null) {
//            autenticacao = FirebaseAuth.getInstance();
//        }
//        return autenticacao;
//    }
}
