/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.render;

import com.ciplogic.web.codeeditor.render.languages.LanguageDefinitionVO;
import com.ciplogic.web.codeeditor.render.languages.LanguageDefinitionsHolder;
import com.ciplogic.web.codeeditor.render.languages.RendererLanguagesHolder;
import com.google.gwt.dom.client.Element;
import com.google.inject.Inject;

public class RendererDetector {
    private RendererLanguagesHolder rendererLanguagesHolder;
    private CodeRenderer codeRenderer;

    @Inject
    public RendererDetector(CodeRenderer codeRenderer,
                            RendererLanguagesHolder rendererLanguagesHolder) {
        this.codeRenderer = codeRenderer;
        this.rendererLanguagesHolder = rendererLanguagesHolder;
    }

    public DetectedRenderer getRenderer(Element selectedElement) {
        Element codeElement = codeRenderer.getCodeElement(selectedElement);

        if (codeElement != null) {
            LanguageDefinitionVO languageDefinitionVO = getLanguageDefinition(codeRenderer, codeElement);
            LanguageDefinitionsHolder languageDefinitionHolder = rendererLanguagesHolder.getLanguageDefinitionHolder(codeRenderer);

            return new DetectedRenderer(codeRenderer,
                    codeElement,
                    languageDefinitionVO,
                    codeRenderer.getCode(codeElement),
                    codeRenderer.isShowLines(codeElement),
                    languageDefinitionHolder);
        }

        LanguageDefinitionsHolder languageDefinitionHolder = rendererLanguagesHolder.getLanguageDefinitionHolder(codeRenderer);
        LanguageDefinitionVO defaultLanguage = languageDefinitionHolder.getDefaultLanguage();

        return new DetectedRenderer(codeRenderer,
                null,
                defaultLanguage,
                "",
                true,
                languageDefinitionHolder);
    }

    private LanguageDefinitionVO getLanguageDefinition(CodeRenderer codeRenderer, Element codeElement) {
        String rendererCodeElementLanguage = codeRenderer.getCodeElementLanguage(codeElement);
        LanguageDefinitionsHolder languageDefinitionsHolder = rendererLanguagesHolder.getRendererLanguages().get(codeRenderer);

        return languageDefinitionsHolder.getLanguageFromRenderer(rendererCodeElementLanguage);
    }
}
