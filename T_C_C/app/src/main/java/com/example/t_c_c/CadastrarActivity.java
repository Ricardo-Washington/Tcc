package com.example.t_c_c;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CadastrarActivity extends AppCompatActivity {

    private ImageView  imvretorno;
    private Button brcadastrar;
    private EditText edtnome, edttelefone, edtemail, edtsenha;
    String[] mensagens = {"Preencha todos os campos! ", "Cadastro criado com sucesso!"};
    String usuarioId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastrar);

        IniciarComponentes();

        imvretorno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent voltaMain1 = new Intent(CadastrarActivity.this,MainActivity.class);
                CadastrarActivity.this.startActivities(new Intent[]{voltaMain1});
            }
        });

        brcadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = edtnome.getText().toString();
                String telefone = edttelefone.getText().toString();
                String email = edtemail.getText().toString();
                String senha = edtsenha.getText().toString();


                if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty() || senha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(view,mensagens[0],Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                } else {
                    CadastrarUsuario(view);

                }
            }
        });

    }
    private void mensagemErroApresentar(String m){
        Toast.makeText(CadastrarActivity.this, m, Toast.LENGTH_LONG).show();
    }

    private void CadastrarUsuario(View view){

        String email = edtemail.getText().toString();
        String senha = edtsenha.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    SalvarDadosUsuario();
                    Snackbar snackbar = Snackbar.make(view,mensagens[1],Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else {
                    String erro;
                    try {
                        throw task.getException();

                    }catch (FirebaseAuthWeakPasswordException e){
                        erro = "Digite uma senha com no minimo 6 caracteres!";
                    }catch (FirebaseAuthUserCollisionException e){
                        erro = "Esta conta ja foi cadastrada!";
                    } catch (FirebaseAuthInvalidCredentialsException e){
                        erro = "E-mail invalido!";
                    }catch (Exception e){
                        erro = "Erro ao cadastra usuario!";
                    }
                    Snackbar snackbar = Snackbar.make(view,erro,Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                }
            }
        });
    }
    private void SalvarDadosUsuario(){
        String nome = edtnome.getText().toString();
        String telefone = edttelefone.getText().toString();
        String email = edtemail.getText().toString();
        String senha = edtsenha.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String,Object> usuarios = new HashMap<>();
        usuarios.put("nome",nome);
        usuarios.put("email",email);
        usuarios.put("telefone",telefone);

        usuarioId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioId);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("db", "Sucesso ao salvar os dados");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("db_erro", "Erro ao salvar os dados" + e.toString());
            }
        });

    }
    private void IniciarComponentes(){
        imvretorno = (ImageView) findViewById(R.id.imvretorno);

        brcadastrar = (Button) findViewById(R.id.btlogin);

        edtnome = (EditText)findViewById(R.id.edtnome);
        edttelefone = (EditText) findViewById(R.id.edttelefone);
        edtemail = (EditText) findViewById(R.id.edtemail);
        edtsenha = (EditText) findViewById(R.id.edtsenha);

    }
}