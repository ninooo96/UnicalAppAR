package com.ninooo96.unicalappar;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

public class ListaCubi {
    public static LinkedList<Cubo> listaCubi = new LinkedList<>();
    private Context context;



    public ListaCubi(Context context){
        this.context = context;
        popola();
    }

    private void popola(){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open("posizione_cubi.txt")));
            String line;
            while ((line =br.readLine()) != null){
                line.replaceAll("-"," ");
                String[] row = line.split(" ");
                listaCubi.add(new Cubo(Float.valueOf(row[3]), Float.valueOf(row[5]), Integer.parseInt(row[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Cubo> getCubi(){
        return listaCubi;
    }

    public String toString(){
        int ind = 0;
        StringBuilder sb = new StringBuilder();
        while(ind<listaCubi.size()) {
            System.out.println(listaCubi.get(ind).id+" --" +listaCubi.get(ind).inizioCubo + " -- " + listaCubi.get(ind).fineCubo);
            sb.append(listaCubi.get(ind).id+" -- "+listaCubi.get(ind).inizioCubo + " -- " + listaCubi.get(ind).fineCubo);
            ind++;
        }
        return sb.toString();
    }
}
