package com.wineberryhalley.mna.cons;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

public class Cons {
    public static final String a_b_aw = "e_cn";
    public static final String e_mw_a = "a_qkal";

    public static String nendmoete() {return "api/index.php";}
    public static String getD(final String s) {
        // Receiving side
        byte[] data1 = Base64.decode(s, Base64.DEFAULT);
        String text1 = "";
        try {
            text1 = new String(data1, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    return text1;
    }


}
