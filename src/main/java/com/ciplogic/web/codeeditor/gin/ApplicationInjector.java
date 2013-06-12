/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.gin;

import com.ciplogic.web.codeeditor.CodeEditor;
import com.ciplogic.web.codeeditor.gin.config.VisualEditorInjectorModule;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules({
    VisualEditorInjectorModule.class,
    DialogModule.class
})
public interface ApplicationInjector extends Ginjector {
    CodeEditor codeEditorApplication();
}
