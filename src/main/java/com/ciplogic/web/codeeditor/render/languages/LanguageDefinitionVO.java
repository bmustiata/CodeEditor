/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.render.languages;

import com.ciplogic.gwtui.ProgrammingLanguage;

/**
 * A language definition describes a language that can appear in the editor, and its corresponding
 * mapping for the renderer.
 */
public class LanguageDefinitionVO {
    public String visualName;       // 1 These two are both unique, in the context of a renderer.
    public String codeRendererName; // 2
    public ProgrammingLanguage programmingLanguage;

    public LanguageDefinitionVO(String visualName, String codeRendererName, ProgrammingLanguage programmingLanguage) {
        this.visualName = visualName;
        this.codeRendererName = codeRendererName;
        this.programmingLanguage = programmingLanguage;
    }

    public String getVisualName() {
        return visualName;
    }

    public void setVisualName(String visualName) {
        this.visualName = visualName;
    }

    public String getCodeRendererName() {
        return codeRendererName;
    }

    public void setCodeRendererName(String codeRendererName) {
        this.codeRendererName = codeRendererName;
    }

    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }
}
