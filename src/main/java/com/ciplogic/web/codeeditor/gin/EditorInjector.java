/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.gin;

import com.ciplogic.web.codeeditor.editor.VisualEditorFactory;
import com.ciplogic.web.codeeditor.gin.config.CodeRendererModule;
import com.ciplogic.web.codeeditor.gin.config.VisualEditorModule;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules({
    VisualEditorModule.class,
    CodeRendererModule.class,
    EditorModule.class
})
public interface EditorInjector extends Ginjector {
    VisualEditorFactory factory();
}
