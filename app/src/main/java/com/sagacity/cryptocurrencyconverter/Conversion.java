package com.sagacity.cryptocurrencyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class Conversion extends AppCompatActivity {

    private TextView txt1,txt2,txt3,txt4,txt5,convertedTxt;
    private EditText editText;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);
        txt1 =(TextView)findViewById(R.id.page);
        txt2 =(TextView)findViewById(R.id.country);
        txt3 = (TextView)findViewById(R.id.currency);
       txt4 = (TextView)findViewById(R.id.crypto);
        txt5 = (TextView)findViewById(R.id.countryCode);
        convertedTxt = (TextView) findViewById(R.id.convertedText);
        editText = (EditText) findViewById(R.id.editext);
        button = (Button) findViewById(R.id.convertButton);

        Intent intent = this.getIntent();


        if (intent!=null){

            String data = intent.getExtras().getString("bitcoins");
            //this handles the intent from bitcion page
            if (data.equals("bitcoins")){
                Bundle bundle = getIntent().getBundleExtra("bit");
                if (bundle==null){
                    finish();
                }else {
                  final String page = bundle.getString("BitIntent");
                     String country = bundle.getString("country");
                    String countryCode = bundle.getString("countryCode");
                    final String currency = bundle.getString("currency");

                    txt1.setText(page);
                    txt2.setText(country +":");
                    txt3.setText(currency);
                    txt5.setText(countryCode);

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            float bitcoinRate = Float.parseFloat(currency);
                            int bitcoin = 1;
                            double total;

                            String value2 = editText.getText().toString();
                            double inputAmount = Float.parseFloat(value2);
                            total = (inputAmount * bitcoin)/bitcoinRate;
                            txt4.setText(page);
                            convertedTxt.setText(String.format(Locale.getDefault(),"%.2f", total));
                        }
                    });
                }

            }
            //this hanles the intent from ethereum page
            if (data.equals("ethereum")){

                Bundle bundle = getIntent().getBundleExtra("bit");
                if (bundle==null){
                    finish();
                }else {
                   final  String page = bundle.getString("EthIntent");
                    String country = bundle.getString("country");
                    String countryCode = bundle.getString("countryCode");
                    final String currency = bundle.getString("currency");

                    txt1.setText(page);
                    txt2.setText(country+":");
                    txt3.setText(currency);
                    txt5.setText(countryCode);

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            float ethereumRate = Float.parseFloat(currency);
                            int ethereum = 1;
                            double total;

                            //this get the inputvalue from the edit text
                            String value2 = editText.getText().toString();

                            double inputAmount = Float.parseFloat(value2);
                            total = (inputAmount * ethereum)/ethereumRate;
                            txt4.setText(page);

                            //this show results
                            convertedTxt.setText(String.format(Locale.getDefault(),"%.2f", total));
                        }
                    });

                }
            }
        }

    }


}
