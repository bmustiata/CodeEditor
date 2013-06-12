/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.gin.config;

import com.ciplogic.web.codeeditor.editor.VisualEditorFactory;
import com.ciplogic.web.codeeditor.editor.tinymce.TinyMceAssistedFactory;
import com.ciplogic.web.codeeditor.editor.tinymce.TinyMceFactory;
import com.ciplogic.web.codeeditor.render.RendererEditorFix;
import com.ciplogic.web.codeeditor.render.geshi.TinyMceSerializerFixes;
import com.ciplogic.web.codeeditor.render.geshi.TinyMceVisualCssFixes;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.GinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

public class VisualEditorModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(VisualEditorFactory.class)
                .to(TinyMceFactory.class)
                .in(Singleton.class);

        GinModule tinyMceAssistedFactory = new GinFactoryModuleBuilder()
                .build(TinyMceAssistedFactory.class);

        install(tinyMceAssistedFactory);
    }

    // FIXME: these should be moved in the MCE module, since they only apply for TinyMCE.
    // Furthermore, isApplicable becomes redundant if the configuration can figure it out.
    @Provides
    @Singleton
    public List<RendererEditorFix> getRendererFixes(TinyMceVisualCssFixes tinyMceVisualCssFixes) {
        List<RendererEditorFix> result = new ArrayList<RendererEditorFix>();

        result.add( new TinyMceSerializerFixes() );
        result.add( tinyMceVisualCssFixes );

        return result;
    }
}
