/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.render.languages;

import com.ciplogic.gwtui.ProgrammingLanguage;

import java.util.*;

public abstract class LanguageDefinitionsHolder {
    private ArrayList<LanguageDefinitionVO> availableLanguages = new ArrayList<LanguageDefinitionVO>();
    private Map<String, LanguageDefinitionVO> rendererDefinitions = new HashMap<String, LanguageDefinitionVO>();
    private Map<String, LanguageDefinitionVO> visualDefinitions = new LinkedHashMap<String, LanguageDefinitionVO>();

    protected LanguageDefinitionsHolder() {
    }

    protected LanguageDefinitionVO addLanguage(String visualName, String codeRendererName, ProgrammingLanguage codeProgrammingLanguage) {
        LanguageDefinitionVO definitionVO = new LanguageDefinitionVO(visualName, codeRendererName, codeProgrammingLanguage);

        availableLanguages.add(definitionVO);
        rendererDefinitions.put(codeRendererName, definitionVO);
        visualDefinitions.put(visualName, definitionVO);

        return definitionVO;
    }

    public List<LanguageDefinitionVO> getAvailableLanguages() {
        return availableLanguages;
    }

    public LanguageDefinitionVO getLanguageFromRenderer(String rendererProgrammingLanguage) {
        LanguageDefinitionVO result = rendererDefinitions.get(rendererProgrammingLanguage);

        if (result == null) {
            result = addLanguage(rendererProgrammingLanguage, rendererProgrammingLanguage, ProgrammingLanguage.TEXT);
        }

        return result;
    }

    public LanguageDefinitionVO getDefaultLanguage() {
        return visualDefinitions.values().iterator().next();
    }
}
