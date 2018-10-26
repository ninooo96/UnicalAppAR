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

    private class Cubo{
        int id;
        float inizioCubo;
        float fineCubo;

        public Cubo(float inizioCubo, float fineCubo,int id){
            this.inizioCubo = inizioCubo;
            this.fineCubo = fineCubo;
            this.id = id;
        }

        public int getId(){
            return id;
        }
    }

    public ListaCubi(Context context){
        this.context = context;
//        listaCubi.add(new Cubo(32.44f, 37.3f, 0));
//        listaCubi.add(new Cubo(76.44f, 67.3f, 1));
//        listaCubi.add(new Cubo(92.44f, 137.3f, 2));
//
//        ListIterator li = listaCubi.listIterator(0);
//        System.out.println(li.next());System.out.println(((Cubo)li.next()).getId());System.out.println(li.next());
//        System.out.println(li.previous());System.out.println(li.previous());
        popola();
    }

//    private void popola(){
//        //Cubo 0
//        listaCubi.add(new Cubo(0f,25.21f,0));
//
//        //Cubo1
//        listaCubi.add(new Cubo(30.39f,51.23f,1));
//
//        //Cubo2
//        listaCubi.add(new Cubo(55.87f,75.18f,2));
//
//        //Cubo 3
//        listaCubi.add(new Cubo(81.0f,100.28f,3));
//
//        //Cubo4
//        listaCubi.add(new Cubo(104.70f,120.0f,4));
//
//        //Cubo 5
//        listaCubi.add(new Cubo(132.12f,153.27f,5));
//
//        //Cubo 6
//
//
//    }

    private void popola(){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open("posizione_cubi.txt")));
            String line;
            while ((line =br.readLine()) != null){
                String[] row = line.split(" -");
                listaCubi.add(new Cubo(Float.valueOf(row[3]), Float.valueOf(row[5]), Integer.parseInt(row[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Cubo> getCubi(){
        return listaCubi;
    }
//    private Cubo aux,cor,succ;
//    private int ind = 0;
//    private static ListaCubi listaCubi;
//
//    private void add(Cubo c){
//        if(ind == 0){
//            cor = c;
//            c.prev = null;
//            ind++;
//        }
//        else{
//            int indTmp = 0;
//            while (indTmp<ind){
//                aux = cor;
//                cor = aux.next;
//                indTmp++;
//            }
//            aux =cor;
//            cor.next = c;
//            cor = c;
//            cor.prev = aux;
//            ind++;
//        }
//    }
//
//    private void popola(){
//        Cubo c = new Cubo(34.3453563f, 324.323424242f);
//        listaCubi.add(new Cubo(12.23f,213.43f));
//        listaCubi.add(c);
//    }


    public static void main(String[] args){
//        ListaCubi l = new ListaCubi();
////        listaCubi.popola();
////        System.out.println(listaCubi.cor.prev.inizioCubo);
////        listaCubi = new LinkedList<>();
//        ListIterator li = listaCubi.listIterator(0);
//        System.out.println(li.next());System.out.println(li.next());System.out.println(li.next());
//        System.out.println(li.previous());System.out.println(li.previous());
//        ListaCubi l = new ListaCubi(MainActivity.this);

    }
}
