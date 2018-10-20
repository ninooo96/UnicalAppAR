package com.ninooo96.unicalappar;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import eu.bitm.NominatimReverseGeocoding.NominatimReverseGeocodingJAPI;

public class ReverseGeocoding {
    private Context mContext;
    List<Address> addresses;

    public ReverseGeocoding(Context mContext){
        this.mContext = mContext;
    }

    public String getAddress(double lat, double lon) {
        Geocoder geo = new Geocoder(mContext, Locale.ITALY);
        try {
            addresses = geo.getFromLocation(lat, lon, 2);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return addresses.get(0).getSubLocality();
    }

    public String getSimplifiedAddress(double latitude, double longitude) {
        String location = "";
        try {
            Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);

                for (int y=0;y<address.getMaxAddressLineIndex();y++)
                    System.out.print(address.getAddressLine(y)+"\n");

                String admin = address.getAdminArea();
                String subLocality = address.getSubLocality();
                String locality = address.getLocality();
                if (admin.length() > 10) {
                    admin = admin.substring(0, 10) + "..";
                }
                if (locality != null && subLocality != null) {
                    location = subLocality + "," + locality+", "+address.getFeatureName();
                } else if (subLocality != null) {
                    location = subLocality + "," + admin;
                } else {
                    location = locality + "," + admin;

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return location;
    }

    public String getCompleteAddress(double latitude, double longitude) {
        String street = "";
        try {
            Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
//                String state, city, zip, street;
//                if (address.getAdminArea() != null) {
//                    state = address.getAdminArea();
//                } else {
//                    state = "";
//                }
//                if (address.getLocality() != null) {
//                    city = address.getLocality();
//                } else {
//                    city = "";
//                }
//                if (address.getPostalCode() != null) {
//                    zip = address.getPostalCode();
//                } else {
//                    zip = "";
//                }


                if (address.getThoroughfare() != null) {
                    street = address.getThoroughfare();
                } else {
                    street = address.getFeatureName();
                }
//                location = street + "," + city + "," + zip + "," + state;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return street;
    }

//    public String getAddressOSM(double lat, double lon){
//        NominatimReverseGeocodingJAPI nominatim1 = new NominatimReverseGeocodingJAPI(18);
//        eu.bitm.NominatimReverseGeocoding.Address addr = nominatim1.getAdress(lat,lon);
//        String s = addr.getRoad() + addr.toString();
//        return s;
//    }


}
