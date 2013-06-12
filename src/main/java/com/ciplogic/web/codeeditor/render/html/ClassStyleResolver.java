/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.render.html;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

import java.util.HashMap;
import java.util.Map;

public class ClassStyleResolver {
    public static final String NULL_CLASS_MARKER = "";

    private Map<String, Map<String, String>> cache = new HashMap<String, Map<String, String>>();
    private Element element = createElement();

    private Element createElement() {
        Element containerDiv = DOM.createDiv();

        containerDiv.setAttribute("style", "display : none");
        containerDiv.setClassName("CodeMirror");
        containerDiv.addClassName("cm-s-default");

        Element div = DOM.createDiv();
        containerDiv.appendChild(div);

        Element preElement = DOM.createElement("pre");
        div.appendChild(preElement);

        Element spanElement = DOM.createSpan();
        preElement.appendChild(spanElement);

        Document.get().getBody().appendChild(containerDiv);

        return spanElement;
    }

    /**
     * Gets the value of a property for a given css class.
     * @param cssClassName
     * @param cssProperty
     * @return
     */
    public String getStyle(String cssClassName, String cssProperty) {
        if (cssClassName == null) {
            cssClassName = NULL_CLASS_MARKER;
        }

        Map<String, String> classAttributeCache = cache.get(cssClassName);

        if (classAttributeCache == null) {
            classAttributeCache = new HashMap<String, String>();
            cache.put(cssClassName, classAttributeCache);
        }

        if (classAttributeCache.containsKey(cssProperty)) {
            return classAttributeCache.get(cssProperty);
        } else {
            String result = readCssAttributeValue(cssClassName, cssProperty);
            classAttributeCache.put(cssProperty, result);

            return result;
        }
    }

    private String readCssAttributeValue(String cssClassName, String cssProperty) {
        element.setClassName(cssClassName);

        return getStyleProperty(element, cssProperty);
    }

    /**
     *
     * https://developer.mozilla.org/En/DOM:window.getComputedStyle
     * http://help.dottoro.com/ljswreks.php
     *
     * @param el - dom element
     * @param prop - get the properties style, like width, fontFamily, fontSize, fontSizeAdjust. property must be camelCase
     * @return
     */
    private static native String getStyleProperty(Element el, String prop)  /*-{
        var computedStyle;
        if ($doc.defaultView && $doc.defaultView.getComputedStyle) { // standard (includes ie9)
            computedStyle = $doc.defaultView.getComputedStyle(el, null)[prop];

        } else if (el.currentStyle) { // IE older
            computedStyle = el.currentStyle[prop];

        } else { // inline style
            computedStyle = el.style[prop];
        }
        return computedStyle;
    }-*/;
}