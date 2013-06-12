/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor;

import com.ciplogic.web.codeeditor.gin.ApplicationInjector;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class CodeEditorEntryPoint implements EntryPoint, GWT.UncaughtExceptionHandler {
    @Override
    public void onModuleLoad() {
        ApplicationInjector injector = GWT.create(ApplicationInjector.class);

        injector.codeEditorApplication();
    }

    @Override
    public void onUncaughtException(Throwable e) {
        GWT.log(e.getMessage(), e);
    }
}
