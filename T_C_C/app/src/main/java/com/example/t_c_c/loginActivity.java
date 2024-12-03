package com.example.t_c_c;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginActivity extends AppCompatActivity {


    private ImageView imvretorno;
    private EditText edtemail, edtsenha;
    private Button btlogin;
    private ProgressBar progressBar;
    private TextView txtTela_cadastrar;
    String[] mensagens = {"Preencha todos os campos! ", "Cadastro criado com sucesso!"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        IniciarComponentes();


        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtemail.getText().toString();
                String senha =  edtsenha.getText().toString();
                if (email.isEmpty() || senha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(view,mensagens[0],Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else {
                    AutenticarUsuario(view);
                }
            }
        });
        imvretorno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent voltaMain = new Intent(loginActivity.this,MainActivity.class);
                loginActivity.this.startActivities(new Intent[]{voltaMain});
            }
        });
        txtTela_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ircadastro = new Intent(loginActivity.this,CadastrarActivity.class);
                loginActivity.this.startActivities(new Intent[]{ircadastro});
            }
        });

    }

    private void AutenticarUsuario(View v){
        String email = edtemail.getText().toString();
        String senha =  edtsenha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    TelaPrincipal();
                }else {
                    String erro;
                    try {
                        throw task.getException();
                    }catch (Exception e){
                        erro = "Erro ao logar o usuario!";
                    }
                    Snackbar snackbar = Snackbar.make(v,erro,Snackbar.LENGTH_LONG);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();
        if (usuarioAtual != null){
            // TelaPrincipal();
        }
    }
    private void TelaPrincipal(){
        Intent irTelaprin = new Intent(loginActivity.this,Tela_Principal_Activity.class);
        loginActivity.this.startActivities(new Intent[]{irTelaprin});
        finish();
    }

    private void IniciarComponentes(){
        imvretorno = (ImageView) findViewById(R.id.imvretorno);

        edtemail= (EditText) findViewById(R.id.edtemail);
        edtsenha= (EditText) findViewById(R.id.edtsenha);

        txtTela_cadastrar = (TextView) findViewById(R.id.txtTela_cadastro);

        btlogin = (Button) findViewById(R.id.btlogin);
    }

    private void mensagemErroApresentar(String m){
        Toast.makeText(loginActivity.this, m, Toast.LENGTH_LONG).show();
}
}