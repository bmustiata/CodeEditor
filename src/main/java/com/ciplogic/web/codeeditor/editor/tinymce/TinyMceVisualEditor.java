/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.editor.tinymce;

import com.ciplogic.web.codeeditor.editor.VisualEditor;
import com.ciplogic.web.codeeditor.render.RendererDetector;
import com.ciplogic.web.codeeditor.render.geshi.CssCodeClassManager;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class TinyMceVisualEditor implements VisualEditor {
    private JavaScriptObject editor;
    private RendererDetector rendererDetector;
    private CssCodeClassManager cssCodeClassManager;

    @Inject
    public TinyMceVisualEditor(RendererDetector rendererDetector,
                               @Assisted JavaScriptObject editor) {
        this.rendererDetector = rendererDetector;
        this.editor = editor;
        this.cssCodeClassManager = new CssCodeClassManager(this);
    }

    @Override
    public native Element getSelectedElement() /*-{
        var editor = this.@com.ciplogic.web.codeeditor.editor.tinymce.TinyMceVisualEditor::editor;

        return editor.selection.getStart();
    }-*/;

    @Override
    public native void insertHtml(String html) /*-{
        var editor = this.@com.ciplogic.web.codeeditor.editor.tinymce.TinyMceVisualEditor::editor;

        editor.execCommand('mceInsertContent', false, html);
    }-*/;

    @Override
    public RendererDetector getRendererDetector() {
        return rendererDetector;
    }

    @Override
    public CssCodeClassManager getCodeClassManager() {
        return cssCodeClassManager;
    }

    public JavaScriptObject getEditor() {
        return editor;
    }
}
