/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.gin;

import com.ciplogic.web.codeeditor.render.RendererDetector;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class EditorModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(RendererDetector.class)
                .in(Singleton.class);
    }
}
