/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor;

import com.ciplogic.web.codeeditor.dialog.CodeEditorDialogFactory;
import com.ciplogic.web.codeeditor.editor.CodeEditorInjector;
import com.ciplogic.web.codeeditor.editor.VisualEditor;
import com.ciplogic.web.codeeditor.gin.EditorInjector;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class CodeEditor {
    private static CodeEditorDialogFactory CODE_EDITOR_DIALOG_FACTORY;
    private static Provider<EditorInjector> editorInjectorProvider;

    @Inject
    public CodeEditor(CodeEditorDialogFactory codeEditorDialogFactory,
                      CodeEditorInjector codeEditorInjector,
                      Provider<EditorInjector> editorInjectorProvider
                  ) {
        CODE_EDITOR_DIALOG_FACTORY = codeEditorDialogFactory;
        CodeEditor.editorInjectorProvider = editorInjectorProvider;

        codeEditorInjector.injectIntoEditors();
    }

    public static void showEditor(VisualEditor visualEditor) {
        CODE_EDITOR_DIALOG_FACTORY.createCodeEditorDialog(visualEditor).show();
    }

    public static VisualEditor createEditor(JavaScriptObject javaScriptObject) {
        return editorInjectorProvider.get().factory().createEditor(javaScriptObject);
    }
}
