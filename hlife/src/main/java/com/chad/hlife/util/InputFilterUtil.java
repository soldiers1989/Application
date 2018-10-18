package com.chad.hlife.util;

import android.text.InputFilter;
import android.text.Spanned;

public class InputFilterUtil {

    public static class SpaceFilter implements InputFilter {

        @Override
        public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
            if (charSequence.equals(" ")) {
                return "";
            }
            return null;
        }
    }
}
