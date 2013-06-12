/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.editor.tinymce;

import com.ciplogic.web.codeeditor.editor.VisualEditor;
import com.ciplogic.web.codeeditor.editor.VisualEditorFactory;
import com.ciplogic.web.codeeditor.render.CodeRenderer;
import com.ciplogic.web.codeeditor.render.RendererEditorFix;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.inject.Inject;

import java.util.List;

public class TinyMceFactory implements VisualEditorFactory {
    private List<RendererEditorFix> editorFixes;
    private CodeRenderer codeRenderer;
    private TinyMceAssistedFactory tinyMceAssistedFactory;

    @Inject
    public TinyMceFactory(List<RendererEditorFix> editorFixes,
                          CodeRenderer codeRenderer,
                          TinyMceAssistedFactory tinyMceAssistedFactory) {
        this.editorFixes = editorFixes;
        this.codeRenderer = codeRenderer;
        this.tinyMceAssistedFactory = tinyMceAssistedFactory;
    }

    @Override
    public VisualEditor createEditor(JavaScriptObject editor) {
        TinyMceVisualEditor result = tinyMceAssistedFactory.createEditor(editor);

        applyFixes(result);

        return result;
    }

    private void applyFixes(VisualEditor visualEditor) {
        for (RendererEditorFix fix : editorFixes) {
            try {
                if (fix.isApplicable(visualEditor, codeRenderer)) {
                    fix.applyFix(visualEditor, codeRenderer);
                }
            } catch (Exception e) {
                GWT.log("Failed to apply fix: " + fix.getClass().getName());
            }
        }
    }

}
