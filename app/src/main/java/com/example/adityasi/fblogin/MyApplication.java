package com.example.adityasi.fblogin;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ADITYA SI on 3/30/2017.
 */

public class MyApplication extends Application {

    public void onCreate(){
        super.onCreate();
        hashkey();
    }

    public void hashkey(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.adityasi.fblogin",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("ADI-SIN", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }



}
