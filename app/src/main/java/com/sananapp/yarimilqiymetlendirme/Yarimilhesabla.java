package com.sananapp.yarimilqiymetlendirme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

public class Yarimilhesabla extends AppCompatActivity {


    int silksqSay;
    boolean bsqVarmi = false, Duzdumu = false, DuzdumuBSQ;
    Button calculate;
    //Ballar
    float KSQbalOrtalama, umumiOrtalama, umumiOrtalama_ancqKSQ, bsqBali = 0;
    float[] ballar = new float[6];
    // Views Variables
    EditText[] ksqBallari_editText = new EditText[6];
    EditText editText1, editText2, editText3, editText4, editText5, editText6, bsqEditText;
    LinearLayout lyBSQ, ly4, ly5, ly6;
    TextView neticeGosteren_txt;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yarimilhesabla);



        getData();
        Do();
        Calculate();

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

    void ShowTheInfos() {

        if (!bsqVarmi) {
            if (Duzdumu) {
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String bal=decimalFormat.format(KSQbalOrtalama);
                neticeGosteren_txt.setTextColor(Color.BLUE);
                // 2 QIYMETI
                if(KSQbalOrtalama>=0&&KSQbalOrtalama<=40) {
                    neticeGosteren_txt.setText("Şagirdin Yarımillik balı:" + bal+"\nQiymət:2");
                }
                // 3 QIYMETI
                else if (KSQbalOrtalama>40&&KSQbalOrtalama<=60) {
                    neticeGosteren_txt.setText("Şagirdin Yarımillik balı:" + bal+"\nQiymət:3");
                }
                // 4 QIYMETI
                else if (KSQbalOrtalama>60&&KSQbalOrtalama<=80) {
                    neticeGosteren_txt.setText("Şagirdin Yarımillik balı:" + bal+"\nQiymət:4");
                }
                // 5 QIYMETI
                else if (KSQbalOrtalama>80&&KSQbalOrtalama<=100) {
                    neticeGosteren_txt.setText("Şagirdin Yarımillik balı:" + bal+"\nQiymət:5");
                }
            } else {
                neticeGosteren_txt.setText("Zəhmət olmasa, balları 0-100 aralığında daxil edin.");
                neticeGosteren_txt.setTextColor(Color.RED);
            }
        } else {
            if (Duzdumu && DuzdumuBSQ) {
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String UmumiDecimal=decimalFormat.format(umumiOrtalama);
                String KSQdecimal=decimalFormat.format(KSQbalOrtalama*0.4f);
                String BSQdecimal=decimalFormat.format(bsqBali*0.6f);
                neticeGosteren_txt.setTextColor(Color.BLUE);
                // 2 QIYMETI
                if(umumiOrtalama>=0&&umumiOrtalama<=40) {
                    neticeGosteren_txt.setText("Şagirdin Yarımillik balı:" + umumiOrtalama + "\n KSQ:" + KSQdecimal + "\n BSQ:" + BSQdecimal+"\nQiymət:2");
                }
                // 3 QIYMETI
                else if (umumiOrtalama>40&&umumiOrtalama<=60) {
                    neticeGosteren_txt.setText("Şagirdin Yarımillik balı:" + umumiOrtalama + "\n KSQ:" + KSQdecimal + "\n BSQ:" + BSQdecimal+"\nQiymət:3");
                }
                // 4 QIYMETI
                else if (umumiOrtalama>60&&umumiOrtalama<=80) {
                    neticeGosteren_txt.setText("Şagirdin Yarımillik balı:" + umumiOrtalama + "\n KSQ:" + KSQdecimal + "\n BSQ:" + BSQdecimal+"\nQiymət:4");
                }
                // 5 QIYMETI
                else if (umumiOrtalama>80&&umumiOrtalama<=100) {
                    neticeGosteren_txt.setText("Şagirdin Yarımillik balı:" + umumiOrtalama + "\n KSQ:" + KSQdecimal + "\n BSQ:" + BSQdecimal+"\nQiymət:5");
                }
            } else {
                neticeGosteren_txt.setText("Zəhmət olmasa, balları 0-100 aralığında daxil edin.");
                neticeGosteren_txt.setTextColor(Color.RED);
            }
        }

    }

    void Calculate() {
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float cem = 0;
                //Ballar arrayina deyisenleri ver
                for (int i = 0; i < silksqSay; i++) {

                    try {
                        ballar[i] = Float.parseFloat(ksqBallari_editText[i].getText().toString());
                        if (ballar[i] >= 0 && ballar[i] <= 100) {
                            cem = cem + ballar[i];
                            Duzdumu = true;
                        } else {
                            Duzdumu = false;
                            break;
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                }
                //Ortalama KSQveBSQ tap
                KSQbalOrtalama = cem / silksqSay;
                if (bsqVarmi) {
                    try {
                        bsqBali = Float.parseFloat(bsqEditText.getText().toString());
                    } catch (NumberFormatException e1) {
                        e1.printStackTrace();
                    }
                    if (bsqBali >= 0 && bsqBali <= 100) {
                        DuzdumuBSQ = true;
                        umumiOrtalama = (KSQbalOrtalama * 0.4f) + (bsqBali * 0.6f);

                    } else {
                        DuzdumuBSQ = false;
                    }
                }
                ShowTheInfos();
            }
        });
    }

    void Do() {
        switch (silksqSay) {
            case 3:
                ly4.removeAllViewsInLayout();
                ly5.removeAllViewsInLayout();
                ly6.removeAllViewsInLayout();
                break;
            case 4:
                ly5.removeAllViewsInLayout();
                ly6.removeAllViewsInLayout();
                break;
            case 5:
                ly6.removeAllViewsInLayout();
                break;
        }
        if (!bsqVarmi) {
            lyBSQ.removeAllViewsInLayout();
        }
    }


    void getData() {
        Bundle bundle = getIntent().getExtras();
        calculate = findViewById(R.id.Calculate_btn);
        ly4 = findViewById(R.id.ly4);
        ly5 = findViewById(R.id.ly5);
        ly6 = findViewById(R.id.ly6);
        lyBSQ = findViewById(R.id.lyBSQ);
        silksqSay = bundle.getInt("ksqSayi");
        bsqVarmi = bundle.getBoolean("bsqVarmi");
        //Neticeni gostren txt
        neticeGosteren_txt = findViewById(R.id.neticeYarimIl_txt);
        //Edit Textlerin hamisini elde edek
        editText1 = findViewById(R.id.txt);
        editText2 = findViewById(R.id.txt2);
        editText3 = findViewById(R.id.txt3);
        editText4 = findViewById(R.id.txt4);
        editText5 = findViewById(R.id.txt5);
        editText6 = findViewById(R.id.txt6);
        bsqEditText = findViewById(R.id.txtBSQ);

        //EditText[] elde edir (KSq ballarini)
        ksqBallari_editText[0] = editText1;
        ksqBallari_editText[1] = editText2;
        ksqBallari_editText[2] = editText3;
        ksqBallari_editText[3] = editText4;
        ksqBallari_editText[4] = editText5;
        ksqBallari_editText[5] = editText6;

    }
}