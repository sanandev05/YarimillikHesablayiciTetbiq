package com.sananapp.yarimilqiymetlendirme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class KSQSayi_activity extends AppCompatActivity {


    Button ireli_button;
    CheckBox bsq_checkbox;
    EditText say_edittext;
    TextView info_text;
    int sayKSQ;
    boolean bsqVarmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ksqsayi);
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

    void Get() {
        ireli_button = findViewById(R.id.ireli_button);
        bsq_checkbox = findViewById(R.id.bsqvarmi_checkBox);
        say_edittext = findViewById(R.id.KSQsay_editText);
        info_text = findViewById(R.id.info_text);


    }

    void Do() {
        //Buttona basilanda
        ireli_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bsqVarmi = bsq_checkbox.isChecked();
                System.out.println(bsqVarmi);
                String txt=say_edittext.getText().toString();
                if(!txt.isEmpty()){
                    sayKSQ=Integer.parseInt(txt);
                }
                if (sayKSQ >= 3 && sayKSQ < 7) {
                    //Say duzgundur
                    Intent intent = new Intent(getApplicationContext(), Yarimilhesabla.class);
                    intent.putExtra("ksqSayi", sayKSQ);
                    intent.putExtra("bsqVarmi", bsqVarmi);
                    startActivity(intent);
                } else {

                    info_text.setText("*Zəhmə olmasa, KSQ sayını 3-6 aralığında girin.");
                }
            }
        });
    }
}