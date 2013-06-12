/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.render.languages;

import com.ciplogic.web.codeeditor.render.CodeRenderer;
import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Map;

// FIXE: this should be some sort of factory.
public class RendererLanguagesHolder {
    private Map<CodeRenderer, LanguageDefinitionsHolder> rendererLanguages = new HashMap<CodeRenderer, LanguageDefinitionsHolder>();

    @Inject
    public RendererLanguagesHolder(CodeRenderer codeRenderer, LanguageDefinitionsHolder languageDefinitionsHolder) {
        rendererLanguages.put( codeRenderer, languageDefinitionsHolder );
    }

    public Map<CodeRenderer, LanguageDefinitionsHolder> getRendererLanguages() {
        return rendererLanguages;
    }

    public LanguageDefinitionsHolder getLanguageDefinitionHolder(CodeRenderer codeRenderer) {
        return rendererLanguages.get(codeRenderer);
    }
}
