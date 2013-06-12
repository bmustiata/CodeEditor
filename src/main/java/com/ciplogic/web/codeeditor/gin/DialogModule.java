/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.gin;

import com.ciplogic.web.codeeditor.dialog.CodeEditorDialogFactory;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.GinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;

public class DialogModule extends AbstractGinModule {
    @Override
    protected void configure() {
        GinModule codeEditorDialogFactory = new GinFactoryModuleBuilder()
            .build(CodeEditorDialogFactory.class);

        install(codeEditorDialogFactory);
    }
}
