package tr.com.erenkaya.mobileopticalscanner.utils;

import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;


/**
 * Created by Eren on 1.3.2017.
 */

public class Message {
    public static void message(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void styleableMessage(Context context, String message) {
        StyleableToast styleableToast = new StyleableToast(context, message, Toast.LENGTH_SHORT);
        styleableToast.setBackgroundColor(Color.parseColor("#ff5a5f"));
        styleableToast.setTextColor(Color.WHITE);
        styleableToast.spinIcon();
        styleableToast.setMaxAlpha();
        styleableToast.show();

}

}
