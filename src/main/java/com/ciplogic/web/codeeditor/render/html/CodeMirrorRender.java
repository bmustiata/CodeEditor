/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.render.html;

import com.ciplogic.gwtui.ProgrammingLanguage;
import com.google.inject.Inject;

public class CodeMirrorRender {
    private String html = "";

    private boolean emptyLine;

    private ClassStyleResolver classStyleResolver;

    @Inject
    public CodeMirrorRender(ClassStyleResolver classStyleResolver) {
        this.classStyleResolver = classStyleResolver;
    }

    /**
     * Render code as HTML. The language is the CodeMirror language.
     * @param code
     * @param language
     * @return
     */
    public native String parseCode(String code, ProgrammingLanguage language) /*-{
        var _that = this;
        var parseNode = function(text, clazz) {
            _that.@com.ciplogic.web.codeeditor.render.html.CodeMirrorRender::parseNode(Ljava/lang/String;Ljava/lang/String;)(text, clazz);
        };

        this.@com.ciplogic.web.codeeditor.render.html.CodeMirrorRender::html = "";

        var languageName = language.@com.ciplogic.gwtui.ProgrammingLanguage::getLanguage()();
        var architecture = language.@com.ciplogic.gwtui.ProgrammingLanguage::getArchitecture()();

        if (!!architecture) {
            $wnd.CodeMirror.runMode(code, { mode : {
                name : languageName,
                architecture: architecture
            }}, parseNode);
        } else {
            $wnd.CodeMirror.runMode(code, languageName, parseNode);
        }

        return this.@com.ciplogic.web.codeeditor.render.html.CodeMirrorRender::html;
    }-*/;

    private void parseNode(String text, String clazz) {
        if (isEol(text)) {
            html += emptyLine ? "<span>&nbsp;</span>\n" : "\n";
            emptyLine = true;
            return;
        }

        emptyLine = false;

        String actualClass = null;
        if (clazz != null) {
            actualClass = "cm-" + clazz;
        }

        html += StringFormat.format("<span style='font-weight : {0}; color : {1}'>{2}</span>",
                classStyleResolver.getStyle(actualClass, "fontWeight"),
                classStyleResolver.getStyle(actualClass, "color"),
                escapeText(text)
        );
    }

    private String escapeText(String text) {
        return text.replaceAll("&", "&amp;")
                .replaceAll("<", "&lt;")
                .replaceAll(" ", "&nbsp;");
    }

    private boolean isEol(String text) {
        return "\n".equals(text) || "\r".equals(text) || "\r\n".equals(text);
    }
}
