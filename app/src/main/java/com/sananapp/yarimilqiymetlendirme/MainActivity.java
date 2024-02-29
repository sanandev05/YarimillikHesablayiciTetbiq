package com.sananapp.yarimilqiymetlendirme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    Button yarimillik_btn;
    Button yarimillik_suretli_btn;
    Button Illik_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Get();
        ButtonActions();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.item2) {
            Intent intent=new Intent(getApplicationContext(),Credits_activity.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.item3) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }

    void Get(){
        yarimillik_btn=findViewById(R.id.yarimil_button);
        yarimillik_suretli_btn=findViewById(R.id.yarimil_suretli_button);
        Illik_btn=findViewById(R.id.Illik_button);
    }

    void ButtonActions(){
        //YARIMIL DUYMESI UCUN
        yarimillik_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), KSQSayi_activity.class);
                startActivity(intent);
            }
        });

        //YARIMIL SURETLI DUYMESI UCUN
        yarimillik_suretli_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), YarimilHesablaSuretli_activity.class);
                startActivity(intent);
            }
        });
        //İLLİK DUYMESI UCUN
        Illik_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Illik_activity.class);
                startActivity(intent);
            }
        });
    }
}
