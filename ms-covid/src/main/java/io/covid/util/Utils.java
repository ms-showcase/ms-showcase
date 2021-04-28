package io.covid.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Utils {

    public static Float getFloatValue(final String value){
        if(value.isEmpty()){
            return 0f;
        }
        return Float.parseFloat(value);
    }

    public static String sum(final String one, final String two){
        return String.valueOf(getFloatValue(one) + getFloatValue(two));
    }
}
