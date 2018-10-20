package com.ninooo96.unicalappar;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import eu.bitm.NominatimReverseGeocoding.NominatimReverseGeocodingJAPI;

public class AddressActivity extends AppCompatActivity {
    public static List<Address> addresses = null;
    public TextView addr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        addr = findViewById(R.id.address);
    }

    @Override
    protected void onResume(){
        super.onResume();
        String address = (new ReverseGeocoding(this)).getCompleteAddress(39.364166, 16.225764);
//        String address = (new ReverseGeocoding(this)).getAddressOSM(39.364166, 16.225764);
        addr.setText(address);
    }

//    public AddressActivity(){
//        boh();
//    }
//
//    public List<Address> boh(){
//        Geocoder geo = new Geocoder(this, Locale.ITALY);
//        try {
//            addresses = geo.getFromLocation(38.357065, 15.843158,2);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return addresses;
//    }
//    public static void main(String[] args){
////        NominatimReverseGeocodingJAPI nominatim1 = new NominatimReverseGeocodingJAPI(18);
//        new AddressActivity();
//        System.out.print.get(0));ln(addresses
//
////        System.out.println(nominatim1.getAdress(38.357065, 15.843158));
//    }
}
