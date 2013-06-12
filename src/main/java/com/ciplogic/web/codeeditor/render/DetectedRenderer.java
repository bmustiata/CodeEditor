/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.render;

import com.ciplogic.web.codeeditor.render.languages.LanguageDefinitionVO;
import com.ciplogic.web.codeeditor.render.languages.LanguageDefinitionsHolder;
import com.google.gwt.dom.client.Element;

public class DetectedRenderer {
    private CodeRenderer codeRender;
    private Element codeElement;
    private LanguageDefinitionVO programmingLanguage;
    private String code;
    private boolean showLines;

    private LanguageDefinitionsHolder languageDefinitionsHolder;

    public DetectedRenderer(CodeRenderer codeRenderer,
                            Element codeElement,
                            LanguageDefinitionVO programmingLanguage,
                            String code,
                            boolean showLines,
                            LanguageDefinitionsHolder languageDefinitionsHolder) {
        this.codeRender = codeRenderer;
        this.codeElement = codeElement;
        this.programmingLanguage = programmingLanguage;
        this.code = code;
        this.showLines = showLines;
        this.languageDefinitionsHolder = languageDefinitionsHolder;
    }

    public CodeRenderer getCodeRender() {
        return codeRender;
    }

    public void setCodeRender(CodeRenderer codeRender) {
        this.codeRender = codeRender;
    }

    public Element getCodeElement() {
        return codeElement;
    }

    public void setCodeElement(Element codeElement) {
        this.codeElement = codeElement;
    }

    public LanguageDefinitionVO getLanguageDefinition() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(LanguageDefinitionVO programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public LanguageDefinitionsHolder getLanguageDefinitionsHolder() {
        return languageDefinitionsHolder;
    }

    public void setLanguageDefinitionsHolder(LanguageDefinitionsHolder languageDefinitionsHolder) {
        this.languageDefinitionsHolder = languageDefinitionsHolder;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isShowLines() {
        return showLines;
    }

    public void setShowLines(boolean showLines) {
        this.showLines = showLines;
    }
}
