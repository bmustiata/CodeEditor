/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.editor;

import com.ciplogic.web.codeeditor.render.RendererDetector;
import com.ciplogic.web.codeeditor.render.geshi.CssCodeClassManager;
import com.google.gwt.dom.client.Element;

public interface VisualEditor {
    /**
     * Get the current element where either is the cursor, or where the beginning of the selection starts.
     * @return
     */
    Element getSelectedElement();

    /**
     * Insert the HTML into  the editor.
     * @param html
     */
    void insertHtml(String html);

    /**
     * Gets a renderer detector for the editor, that can return the language, the element, etc.
     * @return
     */
    RendererDetector getRendererDetector();

    /**
     * Gives access to the Css Class Manager associated with this editor.
     * @return
     */
    CssCodeClassManager getCodeClassManager();
}
