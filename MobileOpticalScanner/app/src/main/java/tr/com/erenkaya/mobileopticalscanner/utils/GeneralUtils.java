package tr.com.erenkaya.mobileopticalscanner.utils;

import android.text.TextUtils;
import android.util.Patterns;


/**
 * Created by Eren on 29.3.2017.
 */

public class GeneralUtils {
    public static boolean isValidPhoneNumber(String phoneNumber){
        return !TextUtils.isEmpty(phoneNumber)&& Patterns.PHONE.matcher(phoneNumber).matches();
    }

}
