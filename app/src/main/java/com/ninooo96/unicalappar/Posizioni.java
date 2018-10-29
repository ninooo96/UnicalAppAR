//package com.ninooo96.unicalappar;
//
//import android.util.Pair;
//import java.util.*;
//
//public class Posizioni<V> {
//
//    Map<Coppia,V> m= new HashMap<>();
//
//
//    public int size() {
//        return m.size();
//    }
//
//    public boolean isEmpty() {
//        return m.isEmpty();
//    }
//
//    public boolean containsKey(float lat, float lon) {
//        return m.containsKey(new Coppia(lat,lon));
//    }
//
//    public boolean containsValue(V v) {
//        return m.containsValue(v);
//    }
//
//    public V get(float lat, float lon) {
//        Coppia min=null;
//        Coppia curr= new Coppia(lat,lon);
//        for(Coppia c: m.keySet())
//            if(c.compareTo(curr)==0)
//                return m.get(c);
//            else if(min==null|| curr.toPoint().distance(min.toPoint())>curr.toPoint().distance(c.toPoint()))
//                min=c;
//        return m.get(min);
//    }
//
//    public V put(float lat, float lon, V value) {
//        return m.put(new Coppia(lat,lon),value);
//    }
//
//    public V remove(float lat, float lon) {
//        return m.remove(new Coppia(lat,lon));
//    }
//
//
//
//    public void clear() {
//        m.clear();
//
//    }
//
//    public Set<Coppia> keySet() {
//        return m.keySet();
//    }
//
//    public Collection<V> values() {
//        return m.values();
//    }
//
//
//
//    class Coppia extends Pair<Float,Float> implements Comparable<Pair<Float,Float>>{
//
//        /**
//         * Creates a new pair
//         *
//         * @param key   The key for this pair
//         * @param value The value to use for this pair
//         */
//        public Coppia(Float key, Float value) {
//            super(key, value);
//        }
//
//        @Override
//        public int compareTo(Pair<Float, Float> o) {
//            Point2D.Float p1= new Point2D.Float(getKey(),getValue());
//            Point2D.Float p2= new Point2D.Float(o.getKey(),o.getValue());
//            if (p1.distance(p2)<5)
//                return 0;
//            else
//                return 1;
//        }
//
//        public Point2D.Float toPoint(){
//            return new Point2D.Float(getKey(),getValue());}
//    }
//
//}
