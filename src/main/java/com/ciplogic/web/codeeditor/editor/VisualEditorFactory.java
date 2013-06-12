/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.editor;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * The factory will be created when the application is initialized. Thus it should also
 * add its hooks in order to integrate with the editor.
 */
public interface VisualEditorFactory {
    public VisualEditor createEditor(JavaScriptObject javaScriptObject);
}
