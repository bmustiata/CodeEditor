/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.render.html;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

public class StringFormat {
    public static String format(String str, String... replaceValues) {
        for (int i = 0; i < replaceValues.length; i++) {
            RegExp regexp = RegExp.compile("(\\{" + i + "\\})", "g");

            MatchResult match;
            while ((match = regexp.exec(str)) != null) {
                str = str.substring(0, match.getIndex()) +
                        replaceValues[i] +
                        str.substring(match.getIndex() + match.getGroup(1).length());

                regexp.setLastIndex(match.getIndex() + replaceValues[i].length());
            }
        }

        return str;
    }


}
