/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.render.geshi;

import com.ciplogic.web.codeeditor.editor.VisualEditor;
import com.ciplogic.web.codeeditor.editor.tinymce.TinyMceVisualEditor;
import com.ciplogic.web.codeeditor.render.CodeRenderer;
import com.ciplogic.web.codeeditor.render.RendererEditorFix;
import com.ciplogic.web.codeeditor.render.languages.LanguageDefinitionVO;
import com.ciplogic.web.codeeditor.render.languages.LanguageDefinitionsHolder;
import com.google.gwt.dom.client.Element;
import com.google.inject.Inject;

public class TinyMceVisualCssFixes implements RendererEditorFix {
    private LanguageDefinitionsHolder languageDefinitionsHolder;

    @Inject
    public TinyMceVisualCssFixes(LanguageDefinitionsHolder languageDefinitionsHolder) {
        this.languageDefinitionsHolder = languageDefinitionsHolder;
    }

    @Override
    public boolean isApplicable(VisualEditor visualEditor, CodeRenderer codeRenderer) {
        return visualEditor instanceof TinyMceVisualEditor &&
                codeRenderer instanceof GeshiCodeRenderer;
    }

    @Override
    public void applyFix(VisualEditor visualEditor, CodeRenderer codeRenderer) {
        applyFixNative(visualEditor);
    }

    public native void applyFixNative(VisualEditor editor) /*-{
        var mceEditor = editor.@com.ciplogic.web.codeeditor.editor.tinymce.TinyMceVisualEditor::getEditor()();
        var codeElements = mceEditor.dom.doc.querySelectorAll(".ciplogic-geshi");

        for (var i = 0; i < codeElements.length; i++) {
            this.@com.ciplogic.web.codeeditor.render.geshi.TinyMceVisualCssFixes::createCssClass(Lcom/ciplogic/web/codeeditor/editor/VisualEditor;Lcom/google/gwt/dom/client/Element;)(editor, codeElements[i]);
        }
    }-*/;

    public void createCssClass(VisualEditor visualEditor, Element foundElement) {
        String language = foundElement.getAttribute("xml:lang");
        LanguageDefinitionVO editorLanguage = languageDefinitionsHolder.getLanguageFromRenderer(language);

        visualEditor.getCodeClassManager().createCss(
                "ciplogic-geshi-" + editorLanguage.getProgrammingLanguage(),
                editorLanguage.getVisualName()
        );
    }
}
