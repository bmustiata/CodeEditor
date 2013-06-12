/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.render;

import com.ciplogic.web.codeeditor.editor.VisualEditor;
import com.ciplogic.web.codeeditor.render.languages.LanguageDefinitionVO;
import com.google.gwt.dom.client.Element;

import java.util.List;

public interface CodeRenderer {
    /**
     * Get the current element that is used for editing code. In case the selection it's outside a code element,
     * return null.
     * @return
     */
    Element getCodeElement(Element cursorElement);

    /**
     * Render the code, by either updating an existing code element, or by calling visualEditor.insertHtml
     * if the codeElement is null.
     *
     * @param visualEditor
     * @param codeElement
     * @param code
     * @param programmingLanguage
     * @param showLines
     */
    void renderCode(VisualEditor visualEditor,
                      Element codeElement,
                      String code,
                      LanguageDefinitionVO programmingLanguage,
                      boolean showLines);

    /**
     * Returns the language for the given codeElement.
     * @param codeElement
     * @return
     */
    String getCodeElementLanguage(Element codeElement);

    /**
     * From the given codeElement, return the code that was set.
     * @param codeElement
     * @return
     */
    String getCode(Element codeElement);

    /**
     * From the given codeElement, return if showLines is set or not.
     * @param codeElement
     * @return
     */
    boolean isShowLines(Element codeElement);
}
