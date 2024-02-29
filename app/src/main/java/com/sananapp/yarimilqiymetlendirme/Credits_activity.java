package com.sananapp.yarimilqiymetlendirme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Credits_activity extends AppCompatActivity {
    //rating


    ImageButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);


        Get();
        Do();
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

        void Do(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(getApplicationContext(), "Sanan Apps", Toast.LENGTH_SHORT).show();
            }
        });
        }
    private void Get() {
        btn=findViewById(R.id.backButton);
    }
}