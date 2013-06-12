/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.dialog;

import com.ciplogic.web.codeeditor.editor.VisualEditor;
import com.ciplogic.web.codeeditor.render.RendererDetector;

public interface CodeEditorDialogFactory {
    CodeEditorDialog createCodeEditorDialog(VisualEditor visualEditor);
}
