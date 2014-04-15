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

        if ($wnd.tinyMCE.onAddEditor) {
            $wnd.tinyMCE.onAddEditor.add(function() {
                startInjectorMonitoring();
            });
        }

        function areEditorsAvailable() {
            return $wnd.tinyMCE && $wnd.tinyMCE.editors && $wnd.tinyMCE.editors.length;
        }

        function isAnchorNodePresent(editor) {
            return !!editor.getContainer().querySelector('a.mceButton') ||
                !!editor.getContainer().querySelector('div.mce-btn button');
        }

        function injectIntoEditor(editor) {
            var button;

            if (editor.controlManager === "4") {
                createEditorCommand(editor);
                button = createEditorButton(editor.controlManager);
                addButtonIntoEditor(editor, button);
            } else {
                injectIntoEditorVersion4(editor);
            }

            markEditorInjected(editor);
        }

        function injectIntoEditorVersion4(editor) {
            var codeRenderer = "${code.renderer}";
            var bodyDiv, divButton, button, img;

            var containerDiv = $doc.createElement("div");
            containerDiv.setAttribute("class", "mce-container mce-last mce-flow-layout-item mce-btn-group");

            bodyDiv = $doc.createElement("div");
            containerDiv.appendChild(bodyDiv);

            divButton = $doc.createElement("div");
            divButton.setAttribute("class", "mce-widget mce-btn mce-first mce-last");
            divButton.setAttribute("role", "button");
            divButton.setAttribute("tabindex", "-1");
            bodyDiv.appendChild( divButton );

            button = $doc.createElement("button");
            button.setAttribute("role", "presentation");
            button.setAttribute("type", "button");
            button.setAttribute("tabindex", "-1");
            divButton.appendChild(button);

            img = $doc.createElement("img");
            img.setAttribute("src", @com.google.gwt.core.client.GWT::getModuleBaseForStaticFiles()() + "img/" + codeRenderer + ".png");
            button.appendChild(img);

            editor.getContainer().querySelector('div.mce-toolbar-grp div.mce-toolbar div.mce-container-body').appendChild(containerDiv);

            var tinyMceVisualEditor = @com.ciplogic.web.codeeditor.CodeEditor::createEditor(Lcom/google/gwt/core/client/JavaScriptObject;)(editor);

            button.onclick = function() {
                @com.ciplogic.web.codeeditor.CodeEditor::showEditor(Lcom/ciplogic/web/codeeditor/editor/VisualEditor;)(tinyMceVisualEditor);
            }
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
