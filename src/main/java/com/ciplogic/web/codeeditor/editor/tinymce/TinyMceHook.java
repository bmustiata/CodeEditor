/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.editor.tinymce;

import com.ciplogic.web.codeeditor.editor.CodeEditorInjector;

public class TinyMceHook implements CodeEditorInjector {
    @Override
    public native void injectIntoEditors() /*-{
        var injectEditorInterval;

        function startInjectorMonitoring() {
            injectEditorInterval = setInterval(function() {
                if (areEditorsAvailable()) {
                    for (var i = 0; i < $wnd.tinyMCE.editors.length; i++) {
                        var editor = $wnd.tinyMCE.editors[i];

                        if (isAnchorNodePresent(editor) && !isEditorInjected(editor)) {
                            injectIntoEditor(editor);
                            clearInterval(injectEditorInterval);
                        }
                    }
                }
            }, 100);
        }

        startInjectorMonitoring();

        $wnd.tinyMCE.onAddEditor.add(function() {
            startInjectorMonitoring();
        });

        function areEditorsAvailable() {
            return $wnd.tinyMCE && $wnd.tinyMCE.editors && $wnd.tinyMCE.editors.length;
        }

        function isAnchorNodePresent(editor) {
            return !!editor.getContainer().querySelector('a.mceButton');
        }

        function injectIntoEditor(editor) {
            var button;

            createEditorCommand(editor);
            button = createEditorButton(editor.controlManager);
            addButtonIntoEditor(editor, button);
            markEditorInjected(editor);
        }

        function createEditorCommand(editor){
            var tinyMceVisualEditor = @com.ciplogic.web.codeeditor.CodeEditor::createEditor(Lcom/google/gwt/core/client/JavaScriptObject;)(editor);

            editor.editorCommands.addCommands({
                "ciplogic.${code.renderer}codeeditor" : function() {
                    @com.ciplogic.web.codeeditor.CodeEditor::showEditor(Lcom/ciplogic/web/codeeditor/editor/VisualEditor;)(tinyMceVisualEditor);
                }
            });
        }

        function createEditorButton(controlManager){
            var codeRenderer = "${code.renderer}";
            return controlManager.createButton("ciplogic.${code.renderer}codeeditor", {
                title : "Code",
                cmd   : "ciplogic.${code.renderer}codeeditor",
                image : @com.google.gwt.core.client.GWT::getModuleBaseForStaticFiles()() + "img/" + codeRenderer + ".png"
            });
        }

        function addButtonIntoEditor(editor, button){
            var anchorNode = editor.getContainer().querySelector('a.mceButton');

            var buttonContainer = $doc.createElement('td');
            buttonContainer.setAttribute("style", "position : relative");

            var parentOfButton = anchorNode.parentNode.parentNode;
            parentOfButton.insertBefore( buttonContainer, parentOfButton.querySelector(".mceToolbarEnd.mceLast") );

            button.renderTo(buttonContainer);
            button.postRender();
        }


        function isEditorInjected(editor) {
            return !!editor["_ciplogicCodeEditorInjected" + "${code.renderer}"];
        }

        function markEditorInjected(editor) {
            editor["_ciplogicCodeEditorInjected" + "${code.renderer}"] = true;
        }
    }-*/;
}
