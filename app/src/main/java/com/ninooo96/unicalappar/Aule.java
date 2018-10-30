package com.ninooo96.unicalappar;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Aule {
    /**key = nome cubo, value = array di linked list*/
    public static HashMap<String, LinkedList<String>[]> auleMap;

    public Aule(){
//        LinkedList<String> auleCubo = new LinkedList<>();
        auleMap = new HashMap<>();
//        auleMap.put("0C", auleMap.get("0C"))
//        auleMap.get("0C")[0].add("ciao");
        init();
    }

    public HashMap<String, LinkedList<String>[]> getAule(){
        return auleMap;
    }

    public void dibest(){
        if(!auleMap.containsKey("32c"))
            auleMap.put("32c", listeAule());
        auleMap.get("32c")[5].add("CF3");

        if(!auleMap.containsKey("5c"))
            auleMap.put("5c", listeAule());
        auleMap.get("5c")[2].add("5C1");
        auleMap.get("5c")[2].add("5C2");
        auleMap.get("5c")[4].add("5C3");
        auleMap.get("5c")[4].add("5C4");

        if(!auleMap.containsKey("4d"))
            auleMap.put("4d", listeAule());
        auleMap.get("4d")[0].add("B5");
        auleMap.get("4d")[1].add("B6");

        if(!auleMap.containsKey("15b"))
            auleMap.put("15b", listeAule());
        auleMap.get("15b")[3].add("MAGNA");
        auleMap.get("15b")[3].add("C");
        auleMap.get("15b")[3].add("E");
        auleMap.get("15b")[1].add("M");
        auleMap.get("15b")[1].add("N");
        auleMap.get("15b")[3].add("AULA E");

        if(!auleMap.containsKey("5b"))
            auleMap.put("5b", listeAule());
        auleMap.get("5b")[4].add("5B2");
        auleMap.get("5b")[4].add("5B3");

        if(!auleMap.containsKey("4a"))
            auleMap.put("4a", listeAule());
        auleMap.get("4a")[1].add("L1");
        auleMap.get("4a")[1].add("L3");
        auleMap.get("4a")[1].add("L5");

        if(!auleMap.containsKey("12b"))
            auleMap.put("12b", listeAule());
        auleMap.get("12b")[4].add("AULA RESTAURO");
    }

    public void fisica(){
        if(!auleMap.containsKey("31c"))
            auleMap.put("31c", listeAule());
        auleMap.get("31c")[0].add("A");
        auleMap.get("31c")[0].add("B/C");

        if(!auleMap.containsKey("32c"))
            auleMap.put("32c", listeAule());
        auleMap.get("32c")[7].add("CF1");
        auleMap.get("32c")[7].add("CF2");
        auleMap.get("32c")[6].add("CF3");

        if(!auleMap.containsKey("31c"))
            auleMap.put("31c", listeAule());
        auleMap.get("31c")[0].add("D");

        if(!auleMap.containsKey("30c"))
            auleMap.put("30c", listeAule());
        auleMap.get("30c")[0].add("E/F");
        auleMap.get("30c")[2].add("FIS1");
        auleMap.get("30c")[2].add("FIS2");
        auleMap.get("30c")[2].add("FIS3");
        auleMap.get("30c")[2].add("FIS4");
        auleMap.get("30c")[0].add("G");
    }

    public void desf(){ //economia
        if(!auleMap.containsKey("2c"))
            auleMap.put("2c", listeAule());
        auleMap.get("2c")[7].add("Cons 1");

        if(!auleMap.containsKey("5b"))
            auleMap.put("5b", listeAule());
        auleMap.get("5b")[5].add("Cons 4");

        if(!auleMap.containsKey("1d"))
            auleMap.put("1d", listeAule());
        auleMap.get("1d")[0].add("EP1");
        auleMap.get("1d")[0].add("EP2");
        auleMap.get("1d")[0].add("EP3");
        auleMap.get("1d")[0].add("EP4");
        auleMap.get("1d")[0].add("EP5");
        auleMap.get("1d")[0].add("EP6");

        if(!auleMap.containsKey("13c"))
            auleMap.put("13c", listeAule());
        auleMap.get("13c")[7].add("ZENITH 1");
    }

    public void dinci(){
        if(!auleMap.containsKey("39b"))
            auleMap.put("39b", listeAule());
        auleMap.get("39b")[1].add("1A");
        auleMap.get("39b")[1].add("1B");
        auleMap.get("39b")[1].add("1C");

        if(!auleMap.containsKey("39c"))
            auleMap.put("39c", listeAule());
        auleMap.get("39c")[1].add("1A");
        auleMap.get("39c")[2].add("2A");
        auleMap.get("39c")[2].add("2B");
        auleMap.get("39c")[3].add("MOD3A");

        if(!auleMap.containsKey("40b"))
            auleMap.put("40b", listeAule());
        auleMap.get("40b")[5].add("Aula Consolidata");

        if(!auleMap.containsKey("41b"))
            auleMap.put("41b", listeAule());
        auleMap.get("41b")[0].add("0A");
        auleMap.get("41b")[7].add("6A - Aula Marone");
        auleMap.get("41b")[7].add("6B - Aula Studenti");

        if(!auleMap.containsKey("44b"))
            auleMap.put("44b", listeAule());
        auleMap.get("44b")[5].add("4A");

        if(!auleMap.containsKey("45b"))
            auleMap.put("45b", listeAule());
        auleMap.get("45b")[0].add("0A");
        auleMap.get("45b")[0].add("0B");
        auleMap.get("45b")[0].add("0C");
    }

    public void dimes(){
        if(!auleMap.containsKey("32b"))
            auleMap.put("32b", listeAule());
        auleMap.get("32b")[5].add("32B1");
        auleMap.get("32b")[7].add("32B2");
        auleMap.get("32b")[7].add("32B3");

        if(!auleMap.containsKey("41b"))
            auleMap.put("41b", listeAule());
        auleMap.get("41b")[2].add("DS5");
        auleMap.get("41b")[2].add("DS6");
        auleMap.get("41b")[2].add("DS7");
        auleMap.get("41b")[2].add("DS8");

        if(!auleMap.containsKey("42d"))
            auleMap.put("42d", listeAule());
        auleMap.get("42d")[0].add("I1");
        auleMap.get("42d")[0].add("I2");
        auleMap.get("42d")[0].add("I3");

        if(!auleMap.containsKey("39c"))
            auleMap.put("39c", listeAule());
        auleMap.get("39c")[3].add("MOD3");
        auleMap.get("39c")[4].add("MOD4");

        if(!auleMap.containsKey("43b"))
            auleMap.put("43b", listeAule());
        auleMap.get("43b")[6].add("P5");
        auleMap.get("43b")[6].add("P6");

        if(!auleMap.containsKey("42c"))
            auleMap.put("42c", listeAule());
        auleMap.get("42c")[3].add("Aula Automatica");
        auleMap.get("42c")[5].add("Aula Seminari");
    }

    public void dimeg(){
        if(!auleMap.containsKey("43b"))
            auleMap.put("43b", listeAule());
        auleMap.get("43b")[4].add("Aula 43B");

        if(!auleMap.containsKey("43c"))
            auleMap.put("43c", listeAule());
        auleMap.get("43c")[4].add("B");
        auleMap.get("43c")[6].add("P3");
        auleMap.get("43c")[6].add("P4");

        if(!auleMap.containsKey("40c"))
            auleMap.put("40c", listeAule());
        auleMap.get("40c")[6].add("P1");

        if(!auleMap.containsKey("44c"))
            auleMap.put("44c", listeAule());
        auleMap.get("44c")[1].add("M1");
        auleMap.get("44c")[1].add("M2");
        auleMap.get("44c")[1].add("M3");
        auleMap.get("44c")[2].add("M4");

        if(!auleMap.containsKey("41b"))
            auleMap.put("41b", listeAule());
        auleMap.get("41b")[0].add("DS4");
    }

    public void diatic(){
        if(!auleMap.containsKey("40c"))
            auleMap.put("40c", listeAule());
        auleMap.get("40c")[4].add("DIATIC A");

        if(!auleMap.containsKey("42b"))
            auleMap.put("42b", listeAule());
        auleMap.get("42b")[2].add("DIATIC B");
        auleMap.get("42b")[2].add("DIATIC C");
        auleMap.get("42b")[2].add("DIATIC D");

        if(!auleMap.containsKey("39c"))
            auleMap.put("39c", listeAule());
        auleMap.get("39c")[0].add("DIATIC E");
        auleMap.get("39c")[2].add("DIATIC F");
        auleMap.get("39c")[4].add("DIATIC G");
        auleMap.get("39c")[2].add("DIATIC H");

        if(!auleMap.containsKey("45a"))
            auleMap.put("45a", listeAule());
        auleMap.get("45a")[0].add("Aula Informatica");
    }

    public void lise(){ //lingue e scienze dell'educazione
        if(!auleMap.containsKey("20b"))
            auleMap.put("20b", listeAule());
        auleMap.get("20b")[0].add("A");
        auleMap.get("20b")[0].add("B");
        auleMap.get("20b")[2].add("E");
        auleMap.get("20b")[2].add("F");
        auleMap.get("20b")[2].add("Informatica 1");
        auleMap.get("20b")[0].add("Informatica 2");
        auleMap.get("20b")[2].add("Multimediale");

        if(!auleMap.containsKey("18b"))
            auleMap.put("18b", listeAule());
        auleMap.get("18b")[0].add("Apollo");
        auleMap.get("18b")[2].add("Athena");
        auleMap.get("18b")[2].add("Hera");
        auleMap.get("18b")[2].add("Zeus");

        if(!auleMap.containsKey("17b"))
            auleMap.put("17b", listeAule());
        auleMap.get("17b")[0].add("Aurora");
        auleMap.get("17b")[0].add("Nettuno");

        if(!auleMap.containsKey("19b"))
            auleMap.put("19b", listeAule());
        auleMap.get("19b")[6].add("Solano");
    }

    public void demacs(){
        if(!auleMap.containsKey("31b"))
            auleMap.put("31b", listeAule());
        auleMap.get("31b")[0].add("MT1");
        auleMap.get("31b")[0].add("MT2");
        auleMap.get("31b")[0].add("MT3");
        auleMap.get("31b")[2].add("MT13");
        auleMap.get("31b")[2].add("MT14");

        if(!auleMap.containsKey("30b"))
            auleMap.put("30b", listeAule());
        auleMap.get("30b")[0].add("MT4");
        auleMap.get("30b")[0].add("MT6");
        auleMap.get("30b")[0].add("MT8");

        if(!auleMap.containsKey("31a"))
            auleMap.put("31a", listeAule());
        auleMap.get("31a")[0].add("MT5");
        auleMap.get("31a")[0].add("MT5bis");
        auleMap.get("31a")[1].add("MT15");

        if(!auleMap.containsKey("30a"))
            auleMap.put("30a", listeAule());
        auleMap.get("30a")[0].add("Aula a");
    }

    public void dispes(){ //scienze politiche e sociali
        if(!auleMap.containsKey("29b"))
            auleMap.put("29b", listeAule());
        auleMap.get("29b")[4].add("Danilo Dolci");

        if(!auleMap.containsKey("1a"))
            auleMap.put("1a", listeAule());
        auleMap.get("1a")[0].add("SSP1");
        auleMap.get("1a")[1].add("SSP2");
        auleMap.get("1a")[1].add("SSP3");
        auleMap.get("1a")[1].add("SSP4");
        auleMap.get("1a")[1].add("SSP5");
    }

    public void disu() {//studi umanistici
        if(!auleMap.containsKey("17b"))
            auleMap.put("17b", listeAule());
        auleMap.get("17b")[0].add("Ares");
        auleMap.get("17b")[4].add("Aula seminari");
        auleMap.get("17b")[2].add("Dioniso");
        auleMap.get("17b")[2].add("Lab. Topografia Antica");
        auleMap.get("17b")[7].add("Lab. Documentazione");
        auleMap.get("17b")[2].add("PAN");
        auleMap.get("17b")[0].add("Sala consiglio");

        if(!auleMap.containsKey("13c"))
            auleMap.put("13c", listeAule());
        auleMap.get("13c")[6].add("ZENITH2");

        if(!auleMap.containsKey("18c"))
            auleMap.put("18c", listeAule());
        auleMap.get("18c")[6].add("Aula seminari");
        auleMap.get("18c")[2].add("F1");
        auleMap.get("18c")[2].add("F2");
        auleMap.get("18c")[2].add("F3");
        auleMap.get("18c")[0].add("N");
        if(!auleMap.containsKey("3a"))
            auleMap.put("3a", listeAule());
        auleMap.get("3a")[0].add("OA1");
        auleMap.get("3a")[0].add("OA2");

        if(!auleMap.containsKey("2b"))
            auleMap.put("2b", listeAule());
        auleMap.get("2b")[4].add("Consolidata 2B");

        if(!auleMap.containsKey("21b"))
            auleMap.put("21b", listeAule());
        auleMap.get("21b")[0].add("Aula seminari");
        auleMap.get("21b")[0].add("Giorgio Leone");
        auleMap.get("21b")[7].add("Lab. Archeologia");
        auleMap.get("21b")[6].add("Lab. storia lingua ita");
        auleMap.get("21b")[0].add("Spezzaferro");

        if(!auleMap.containsKey("27b"))
            auleMap.put("27b", listeAule());
        auleMap.get("27b")[7].add("Aula seminari");
        auleMap.get("27b")[4].add("Filol. 1");
        auleMap.get("27b")[4].add("Filol. 2");
        auleMap.get("27b")[4].add("Filol. 3");
        auleMap.get("27b")[4].add("Filol. 4");
        auleMap.get("27b")[6].add("Lab. storia lingua ita per stranieri");

        if(!auleMap.containsKey("28d"))
            auleMap.put("28d", listeAule());
        auleMap.get("28d")[0].add("Aula seminari");
        auleMap.get("28d")[1].add("Lab. filologia informatica");
        auleMap.get("28d")[0].add("Stor. 7");

        if(!auleMap.containsKey("28a"))
            auleMap.put("28a", listeAule());
        auleMap.get("28a")[1].add("CSDIM");

        if(!auleMap.containsKey("29c"))
            auleMap.put("29c", listeAule());
        auleMap.get("29c")[6].add("FAC. 1");
        auleMap.get("29c")[6].add("FAC. 2");
        auleMap.get("29c")[4].add("IRIS");

        if(!auleMap.containsKey("28b"))
            auleMap.put("28b", listeAule());
        auleMap.get("28b")[2].add("Filol. 5");
        auleMap.get("28b")[0].add("Filol. 9");
        auleMap.get("28b")[0].add("Mario Alcaro");

        if(!auleMap.containsKey("19b"))
            auleMap.put("19b", listeAule());
        auleMap.get("19b")[4].add("IANA");

        if(!auleMap.containsKey("28c"))
            auleMap.put("28c", listeAule());
        auleMap.get("28c")[0].add("Stor. 1");
        auleMap.get("28c")[0].add("Stor. 2A");
        auleMap.get("28c")[0].add("Stor. 2B");
        auleMap.get("28c")[0].add("Stor. 2C");
        auleMap.get("28c")[2].add("Stor. 3");
        auleMap.get("28c")[2].add("Stor. 4");
        auleMap.get("28c")[2].add("Stor. 5");
        auleMap.get("28c")[2].add("Stor. 6");
    }

    public void init(){
        dimeg();
        dimes();
        demacs();
        desf();
        diatic();
        dibest();
        dinci();
        dispes();
        disu();
        lise();
        fisica();
    }

//    @Override
//    public String toString() {
//        for (String s: auleMap.keySet()){
//            System.out.print(s+": ");
//            for (int i = 0; i<=7;i++){
//                for(String aula : auleMap.get(s)[i])
//                    System.out.print(aula+", ");
//            }
//            System.out.println();
//        }
//
//    }
    public LinkedList[] listeAule(){
        LinkedList[] liste = new LinkedList[9];
        for(int i = 0; i<= 8; i++){
            liste[i]=new LinkedList<String>();
        }
        return liste;
    }
    public void print(){
        List<String> keys = new LinkedList<>(auleMap.keySet());
        Collections.sort(keys);
        for (String s: keys){
            System.out.print(s+": ");
            for (int i = 0; i<=8;i++){
                for(String aula : auleMap.get(s)[i])
                    System.out.print(aula+", ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        Aule a = new Aule();
        a.print();
    }
}
