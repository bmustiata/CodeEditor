/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.render.geshi;

import com.ciplogic.web.codeeditor.editor.VisualEditor;
import com.ciplogic.web.codeeditor.editor.tinymce.TinyMceVisualEditor;
import com.ciplogic.web.codeeditor.render.CodeRenderer;
import com.ciplogic.web.codeeditor.render.RendererEditorFix;
import com.google.gwt.core.client.JavaScriptObject;

public class TinyMceSerializerFixes implements RendererEditorFix {
    public boolean isApplicable(VisualEditor visualEditor, CodeRenderer codeRenderer) {
        return visualEditor instanceof TinyMceVisualEditor &&
                codeRenderer instanceof GeshiCodeRenderer;
    }

    public void applyFix(VisualEditor visualEditor, CodeRenderer codeRenderer) {
        JavaScriptObject editor = ((TinyMceVisualEditor) visualEditor).getEditor();

        applyFixNative(editor);
    }

    public native void applyFixNative(JavaScriptObject editor) /*-{
        function postProcess(wnd, data) {
            var match,
                content,
                geshiTagRegexp = /<pre .*?xml:lang=.*?>[\s\S]*?<\/pre>/gi;

            content = data.content;

            while (match = geshiTagRegexp.exec(content)) {
                geshiTagRegexp.lastIndex = match.index + 1;

                content = content.substr(0, match.index) +
                        match[0].replace(/&amp;/g, "&") +
                        content.substr(match.index + match[0].length);
            }

            data.content = content;
        }

        editor.serializer.onPostProcess.add(function(wnd, data) {
            return postProcess(wnd, data);
        });
    }-*/;
}
