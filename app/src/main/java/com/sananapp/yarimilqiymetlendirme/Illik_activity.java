package com.sananapp.yarimilqiymetlendirme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class Illik_activity extends AppCompatActivity {


    Button calc_btn;
    EditText editText1,editText2;
    TextView netice_txt;
    float umumiIllik=0;
    Boolean duzluk=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illik);

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

    private void Do() {
       calc_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Calculate();
           }
       });
    }

    private void Calculate() {
        try {
            float a = Float.parseFloat(editText1.getText().toString());
            float b = Float.parseFloat(editText2.getText().toString());
            umumiIllik = (a + b) / 2;
            if (a >= 0 && a < 101) {
                duzluk = true;
            } else {
                duzluk = false;
            }
            if (b >= 0 && b < 101) {
                duzluk = true;
            } else {
                duzluk = false;
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        ShowInfos();
    }

    private void ShowInfos() {
        netice_txt.setTextColor(Color.BLUE);
        if(duzluk) {
            netice_txt.setText("Şagirdin İllik Balı:" + umumiIllik);
        }
        else{
            netice_txt.setTextColor(Color.RED);
            netice_txt.setText("* Zəhmət olmasa,balları düzgün daxil edin.");
        }
    }

    private void Get() {
        calc_btn=findViewById(R.id.IllikHesabla_btn);
        editText1=findViewById(R.id.yarimBal1_editText);
        editText2=findViewById(R.id.yarimBal2_editText);
        netice_txt=findViewById(R.id.neticeIllik_txt);
    }
}