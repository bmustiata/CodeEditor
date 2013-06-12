/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.gin.config;

import com.ciplogic.web.codeeditor.render.CodeRenderer;
import com.ciplogic.web.codeeditor.render.languages.LanguageDefinitionsHolder;
import com.ciplogic.web.codeeditor.render.syntaxhighlight.SyntaxHighlightCodeRenderer;
import com.ciplogic.web.codeeditor.render.syntaxhighlight.SyntaxHighlighterLanguageDefinitionsHolder;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class CodeRendererModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(CodeRenderer.class)
            .to(SyntaxHighlightCodeRenderer.class)
            .in(Singleton.class);

        bind(LanguageDefinitionsHolder.class)
            .to(SyntaxHighlighterLanguageDefinitionsHolder.class)
            .in(Singleton.class);
    }
}
