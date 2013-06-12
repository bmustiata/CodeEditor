/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.render.geshi;

import com.ciplogic.web.codeeditor.editor.tinymce.TinyMceVisualEditor;
import com.google.gwt.core.client.JavaScriptObject;

public class CssCodeClassManager {
    private TinyMceVisualEditor tinyMceVisualEditor;

    public CssCodeClassManager(TinyMceVisualEditor tinyMceVisualEditor) {
        this.tinyMceVisualEditor = tinyMceVisualEditor;
    }

    public void createCss(String selector, String visual) {
        nativeCreateClass(tinyMceVisualEditor.getEditor(), selector, "border:black solid 1px;" +
                "margin:0;" +
                "padding:2px;" +
                "font-family:monospace");
        nativeCreateClass(tinyMceVisualEditor.getEditor(), selector + ":before",
                     "content : '" + visual + " '; float : right; color : gray");
    }

    public native void nativeCreateClass(JavaScriptObject editor, String className, String classRule) /*-{
        var doc = editor.dom.doc;

        if (doc.all) {
            doc.styleSheets[0].addRule(className, classRule)
        } else if (document.getElementById) {
            doc.styleSheets[0].insertRule(className + " { " + classRule + " }", 0);
        }
    }-*/;
}
