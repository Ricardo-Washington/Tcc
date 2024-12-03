package com.example.t_c_c;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class loginActivity extends AppCompatActivity {

    ImageView imvretorno;
    EditText txtemail, txtsenha;
    Button btlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        imvretorno = (ImageView) findViewById(R.id.imvretorno);


        imvretorno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent voltaMain = new Intent(loginActivity.this,MainActivity.class);
                loginActivity.this.startActivities(new Intent[]{voltaMain});
            }
        });
    }
}