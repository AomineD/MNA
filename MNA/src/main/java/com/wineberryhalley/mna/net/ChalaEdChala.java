package com.wineberryhalley.mna.net;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

import com.facebook.ads.AudienceNetworkAds;

import java.util.UUID;

import static androidx.annotation.RestrictTo.Scope.LIBRARY;

@RestrictTo(LIBRARY)
public class ChalaEdChala extends ContentProvider {
    static Context context;
    static Jedleto jedleto;
    @Override
    public boolean onCreate() {
        context = getContext();
        AudienceNetworkAds.initialize(context);
jedleto = new Jedleto();
jedleto.loadData();
        Log.e("MAIN", "onCreate: go home" );
if(!savedUniqueId()){
    saveUniqueId();
}
        return false;
    }

    static boolean savedUniqueId(){

        SharedPreferences preferences = context.getSharedPreferences("usr_id", Context.MODE_PRIVATE);
        return !preferences.getString("usr_unique_id", "").equals("");
    }
    static String getUniqueId(){
        SharedPreferences preferences = context.getSharedPreferences("usr_id", Context.MODE_PRIVATE);
        return preferences.getString("usr_unique_id", "");
    }

    private void saveUniqueId(){
        String uniqueID = UUID.randomUUID().toString();

        SharedPreferences preferences = context.getSharedPreferences("usr_id", Context.MODE_PRIVATE);
        preferences.edit().putString("usr_unique_id", uniqueID).apply();
    }




    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
