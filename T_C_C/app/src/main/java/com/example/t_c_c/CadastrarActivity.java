package com.example.t_c_c;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CadastrarActivity extends AppCompatActivity {

    ImageView  imvretorno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastrar);


    imvretorno = (ImageView) findViewById(R.id.imvretorno);


        imvretorno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent voltaMain1 = new Intent(CadastrarActivity.this,MainActivity.class);
                CadastrarActivity.this.startActivities(new Intent[]{voltaMain1});
            }
        });
    }
}