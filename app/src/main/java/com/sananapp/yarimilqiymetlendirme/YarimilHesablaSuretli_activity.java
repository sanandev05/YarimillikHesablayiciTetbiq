package com.sananapp.yarimilqiymetlendirme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;


import java.text.DecimalFormat;

public class YarimilHesablaSuretli_activity extends AppCompatActivity {



    //Deyisenler
    CheckBox bsqVarmi_checkBox;
    EditText KSQs_editText, BSQ_editText;
    TextView netice_txt;

    // Hesablama deyisenleri
    float umumiBal = 0, BSQbal = 0, edediOrtaKSQs = 0;
    boolean bsqVar = false;

    //Duzgunluk
    boolean ksqSayDuz = false, ksqBalDuz = false, bsqBalDuz = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yarimil_hesabla_suretli);
        Get();
        BSQvarmiDo();
        YoxlaEditTexts();

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

    void YoxlaEditTexts() {
        //KSQler
        KSQs_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                BSQvarmiDo();
                Hesabla();
            }

            @Override
            public void afterTextChanged(Editable s) {
                BSQvarmiDo();
                Hesabla();
            }
        });
        //BSQler
        BSQ_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                BSQvarmiDo();
                Hesabla();
            }

            @Override
            public void afterTextChanged(Editable s) {
                BSQvarmiDo();
                Hesabla();
            }
        });
        Hesabla();
    }

    void Hesabla() {
        String txt = KSQs_editText.getText().toString();
        float[] numbers;
        edediOrtaKSQs = 0;
        float cem = 0;
        try {
            String[] splixTxts = txt.split(",");
            numbers = new float[splixTxts.length];
            for (int i = 0; i < splixTxts.length; i++) {
                numbers[i] = Float.parseFloat(splixTxts[i]);
            }
            if (numbers.length >= 3 && numbers.length < 7) {
                ksqSayDuz = true;

                for (int i = 0; i < numbers.length; i++) {
                    if (numbers[i] >= 0 && numbers[i] < 101) {
                        cem = cem + numbers[i];
                        ksqBalDuz = true;
                    } else {
                        ksqBalDuz = false;
                        break;
                    }
                }
                edediOrtaKSQs = cem / numbers.length;
                if (bsqVar) {
                    BSQbal = Float.parseFloat(BSQ_editText.getText().toString());
                    if (BSQbal >= 0 && BSQbal < 101) {
                        bsqBalDuz = true;
                        umumiBal = (edediOrtaKSQs * 0.4f) + (BSQbal * 0.6f);
                    } else {
                        bsqBalDuz = false;
                    }
                } else {
                    umumiBal = edediOrtaKSQs;
                }

            } else {
                ksqSayDuz = false;

            }
            ShowInfos();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    void ShowInfos() {
        if (bsqVar) {
            if (ksqSayDuz && ksqBalDuz && bsqBalDuz) {
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String UmumiDecimal = decimalFormat.format(umumiBal);
                String KSQdecimal = decimalFormat.format(edediOrtaKSQs * 0.4f);
                String BSQdecimal = decimalFormat.format(BSQbal * 0.6f);
                netice_txt.setTextColor(Color.BLUE);
                // 2 QIYMETI
                if(umumiBal>=0&&umumiBal<=40) {
                    netice_txt.setText("Şagirdin Yarımillik balı:" + UmumiDecimal + "\n KSQ:" + KSQdecimal + "\n BSQ:" + BSQdecimal+"\nQiymət:2");
                }
                // 3 QIYMETI
                else if (umumiBal>40&&umumiBal<=60) {
                    netice_txt.setText("Şagirdin Yarımillik balı:" + UmumiDecimal + "\n KSQ:" + KSQdecimal + "\n BSQ:" + BSQdecimal+"\nQiymət:3");
                }
                // 4 QIYMETI
                else if (umumiBal>60&&umumiBal<=80) {
                    netice_txt.setText("Şagirdin Yarımillik balı:" + UmumiDecimal + "\n KSQ:" + KSQdecimal + "\n BSQ:" + BSQdecimal+"\nQiymət:4");
                }
                // 5 QIYMETI
                else if (umumiBal>80&&umumiBal<=100) {
                    netice_txt.setText("Şagirdin Yarımillik balı:" + UmumiDecimal + "\n KSQ:" + KSQdecimal + "\n BSQ:" + BSQdecimal+"\nQiymət:5");
                }
            } else if (ksqBalDuz && !ksqSayDuz) {
                netice_txt.setTextColor(Color.RED);
                netice_txt.setText("*Zəhmət olmasa,KSQ sayını düzgün daxil edin.");
            } else if (!ksqBalDuz && ksqSayDuz) {
                netice_txt.setTextColor(Color.RED);
                netice_txt.setText("*Zəhmət olmasa,KSQ ballarını 0-100 aralığında yazın.");
            } else {
                netice_txt.setTextColor(Color.RED);
                netice_txt.setText("*Zəhmət olmasa,KSQ sayını və ballarını düzgün daxil edin.");
            }
        } else {
            if (ksqSayDuz && ksqBalDuz) {
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String UmumiDecimal = decimalFormat.format(umumiBal);
                netice_txt.setTextColor(Color.BLUE);
                // 2 QIYMETI
                if(umumiBal>=0&&umumiBal<=40) {
                    netice_txt.setText("Şagirdin Yarımillik balı:" + UmumiDecimal+"\nQiymət:2");
                }
                // 3 QIYMETI
                else if (umumiBal>40&&umumiBal<=60) {
                    netice_txt.setText("Şagirdin Yarımillik balı:" + UmumiDecimal + "\nQiymət:3");
                }
                // 4 QIYMETI
                else if (umumiBal>60&&umumiBal<=80) {
                    netice_txt.setText("Şagirdin Yarımillik balı:" + UmumiDecimal + "\nQiymət:4");
                }
                // 5 QIYMETI
                else if (umumiBal>80&&umumiBal<=100) {
                    netice_txt.setText("Şagirdin Yarımillik balı:" + UmumiDecimal + "\nQiymət:5");
                }
            } else if (ksqBalDuz && !ksqSayDuz) {
                netice_txt.setTextColor(Color.RED);
                netice_txt.setText("*Zəhmət olmasa,KSQ sayını düzgün daxil edin.");
            } else if (!ksqBalDuz && ksqSayDuz) {
                netice_txt.setTextColor(Color.RED);
                netice_txt.setText("*Zəhmət olmasa,KSQ ballarını 0-100 aralığında yazın.");
            } else {
                netice_txt.setTextColor(Color.RED);
                netice_txt.setText("*Zəhmət olmasa,KSQ sayını və ballarını düzgün daxil edin.");
            }
        }
    }

    void BSQvarmiDo() {
        //Checkbox Listener
        bsqVarmi_checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    umumiBal = 0;
                    BSQ_editText.setVisibility(View.VISIBLE);
                    bsqVar = true;
                    Hesabla();
                } else {
                    umumiBal = 0;
                    BSQ_editText.setVisibility((View.GONE));
                    bsqVar = false;
                    Hesabla();
                }
            }
        });
    }

    void Get() {
        bsqVarmi_checkBox = findViewById(R.id.bsqVarmiKSQS_checkBox);
        KSQs_editText = findViewById(R.id.KSQler_editText);
        BSQ_editText = findViewById(R.id.BSQ_editText);
        netice_txt = findViewById(R.id.neticeKSQS_Text);
    }
}