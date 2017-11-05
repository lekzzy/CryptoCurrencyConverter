package com.sagacity.cryptocurrencyconverter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Bitcoin extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private RecyclerView.Adapter adapter;
    String currenciesCodes = "NGN,USD,EUR,GBP,EGP,OMR,QAR,GHC,KES,CNY,AUD,RUB,KWD,BSD,ZAR,CHF,SAR,JPY,CAD,INR";
    private String URL = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=ETH,BTC&tsyms=" + currenciesCodes;
    String NGN,USD,EUR,GBP,EGP,OMR,QAR,GHC,KES,CNY,AUD,RUB,KWD,BSD,ZAR,CHF,SAR,JPY,CAD,INR;

    private List<CurrencyList> currencyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitcoin);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        currencyList = new ArrayList<>();

        new GetCurrencies().execute();

        //this handles the recyclerview click to the conversion page

        recyclerView.addOnItemTouchListener(new MyRecyclerClickListener(this, recyclerView, new MyRecyclerClickListener
                .OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //handle click events here
                CurrencyList item = currencyList.get(position);
                Bundle args = new Bundle();
                args.putString("currency", item.getCurrency());
                args.putString("country", item.getCountry());
                args.putString("countryCode", item.getCountryCode());

                args.putString("BitIntent", "BITCOINS");

                Intent i = new Intent();
                i.setClass(Bitcoin.this, Conversion.class);
                i.putExtra("bit", args);
                i.putExtra("bitcoins", "bitcoins");
                Bitcoin.this.startActivity(i);

            }

            @Override
            public void onItemLongClick(View view, int position) {
                //handle longClick if any
            }
        }));
    }

    // Async task class

    private class GetCurrencies extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(Bitcoin.this);
            progressDialog.setMessage("Loading");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            HttpHandler sh = new HttpHandler();

            //making a request

            String jsonStr = sh.makeServiceCall(URL);

            if (jsonStr!=null){
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    String BTC = jsonObj.getString("BTC");
                    JSONObject o = new JSONObject(BTC);
                    //get the xchange rate from the api
                    NGN = o.getString("NGN");
                    USD = o.getString("USD");
                    EUR = o.getString("EUR");
                    GBP = o.getString("GBP");
                    RUB = o.getString("RUB");
                    KWD = o.getString("KWD");
                    BSD = o.getString("BSD");
                    SAR = o.getString("SAR");
                    JPY = o.getString("JPY");
                    CAD = o.getString("CAD");
                    GHC = o.getString("GHC");
                    OMR = o.getString("OMR");
                    KES = o.getString("KES");
                    CNY = o.getString("CNY");
                    AUD = o.getString("AUD");
                    ZAR = o.getString("ZAR");
                    CHF = o.getString("CHF");
                    QAR = o.getString("QAR");
                    EGP = o.getString("EGP");
                    INR = o.getString("INR");



                    //add countries, country_codes n amount
                    CurrencyList currency = new CurrencyList("USA", "USD", USD);
                    currencyList.add(currency);

                    currency = new CurrencyList("Nigeria", "NGN", NGN);
                    currencyList.add(currency);

                    currency = new CurrencyList("Germany", "EUR", EUR);
                    currencyList.add(currency);

                    currency = new CurrencyList("UK", "GBP", GBP);
                    currencyList.add(currency);

                    currency = new CurrencyList("Russia", "RUS", RUB);
                    currencyList.add(currency);

                    currency = new CurrencyList("Kuwait", "KWT", KWD);
                    currencyList.add(currency);

                    currency = new CurrencyList("Bahamas", "BHS", BSD);
                    currencyList.add(currency);

                    currency = new CurrencyList("Saudi Arabia", "SAU", SAR);
                    currencyList.add(currency);

                    currency = new CurrencyList("Japan", "JPN", JPY);
                    currencyList.add(currency);

                    currency = new CurrencyList("Canada", "CAN", CAD);
                    currencyList.add(currency);

                    currency = new CurrencyList("Ghana", "GHN", GHC);
                    currencyList.add(currency);

                    currency = new CurrencyList("Oman", "OMN", OMR);
                    currencyList.add(currency);

                    currency = new CurrencyList("Kenya", "KEN", KES);
                    currencyList.add(currency);

                    currency = new CurrencyList("China", "CHN", CNY);
                    currencyList.add(currency);

                    currency = new CurrencyList("Australia", "AUS", AUD);
                    currencyList.add(currency);

                    currency = new CurrencyList("South Africa", "ZAF", ZAR);
                    currencyList.add(currency);

                    currency = new CurrencyList("Switzerland", "CHE", CHF);
                    currencyList.add(currency);

                    currency = new CurrencyList("Qatar", "QAT", QAR);
                    currencyList.add(currency);

                    currency = new CurrencyList("Egypt", "EGY", EGP);
                    currencyList.add(currency);

                    currency = new CurrencyList("India", "IND", INR);
                    currencyList.add(currency);

                    // Add the rest of the countries



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (progressDialog.isShowing())
                progressDialog.dismiss();

            adapter = new MyRecycler(currencyList, getApplicationContext());
            recyclerView.setAdapter(adapter);
        }
    }
}
