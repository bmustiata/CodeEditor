/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.render.geshi;

public class GeSHiConfig {
    private String langAttribute;
    private String linesAttributeName;
    private String linesAttributeValue;

    public GeSHiConfig(String langAttribute, String linesAttributeName, String linesAttributeValue) {
        this.langAttribute = langAttribute;
        this.linesAttributeName = linesAttributeName;
        this.linesAttributeValue = linesAttributeValue;
    }

    public String getLangAttribute() {
        return langAttribute;
    }

    public void setLangAttribute(String langAttribute) {
        this.langAttribute = langAttribute;
    }

    public String getLinesAttributeName() {
        return linesAttributeName;
    }

    public void setLinesAttributeName(String linesAttributeName) {
        this.linesAttributeName = linesAttributeName;
    }

    public String getLinesAttributeValue() {
        return linesAttributeValue;
    }

    public void setLinesAttributeValue(String linesAttributeValue) {
        this.linesAttributeValue = linesAttributeValue;
    }
}
