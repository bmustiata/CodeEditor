/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.gin.config;

import com.ciplogic.web.codeeditor.editor.CodeEditorInjector;
import com.ciplogic.web.codeeditor.editor.tinymce.TinyMceHook;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class VisualEditorInjectorModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(CodeEditorInjector.class)
                .to(TinyMceHook.class)
                .in(Singleton.class);
    }
}
