/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.render;

import com.ciplogic.web.codeeditor.editor.VisualEditor;

public interface RendererEditorFix {
    boolean isApplicable(VisualEditor visualEditor, CodeRenderer codeRenderer);

    public void applyFix(VisualEditor visualEditor, CodeRenderer codeRenderer);
}
