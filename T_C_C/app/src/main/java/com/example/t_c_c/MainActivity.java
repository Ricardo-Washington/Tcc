package com.example.t_c_c;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    Button btmarcar, btcadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btcadastrar = (Button) findViewById(R.id.btcadastrar);
        btmarcar = (Button) findViewById(R.id.btMarcar);

        btmarcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cadastrarusuario = new Intent(MainActivity.this, loginActivity.class);
                MainActivity.this.startActivities(new Intent[]{cadastrarusuario});
            }
        });

        btcadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ircadastrar = new Intent(MainActivity.this,CadastrarActivity.class);
                MainActivity.this.startActivities(new Intent[]{ircadastrar});
            }
        });




    }


}