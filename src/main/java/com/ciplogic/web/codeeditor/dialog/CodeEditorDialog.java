/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.dialog;

import com.ciplogic.gwtui.editor.CodeTextEditor;
import com.ciplogic.web.codeeditor.editor.VisualEditor;
import com.ciplogic.web.codeeditor.render.DetectedRenderer;
import com.ciplogic.web.codeeditor.render.languages.LanguageDefinitionVO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import java.util.List;

import static com.google.gwt.user.client.Event.NativePreviewEvent;

public class CodeEditorDialog extends DialogBox {
    private DetectedRenderer detectedRenderer;

    interface MyUiBinder extends UiBinder<DockLayoutPanel, CodeEditorDialog> {}
    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    Button closeButton;

    @UiField
    Button updateButton;

    @UiField
    CheckBox showLinesCheckBox;

    @UiField
    ListBox selectedLanguageCombo;

    @UiField(provided = true)
    CodeTextEditor codeEditor;

    private VisualEditor visualEditor;

    private LanguageDefinitionVO selectedLanguage;
    private boolean showLines;

    @Inject
    public CodeEditorDialog(@Assisted VisualEditor visualEditor) {
        super(false, true); // modal dialog, so events outside the dialog are dropped.

        this.visualEditor = visualEditor;

        Element selectedElement = visualEditor.getSelectedElement();
        detectedRenderer = visualEditor.getRendererDetector().getRenderer(selectedElement);

        showLines = detectedRenderer.isShowLines();
        selectedLanguage = detectedRenderer.getLanguageDefinition();

        setGlassEnabled(true);
        setHTML("${visual.editor.title} Ciplogic's Code Editor (${code.renderer.title})");

        createCodeEditor();

        setWidget(uiBinder.createAndBindUi(this));

        showLinesCheckBox.setValue(showLines);

        initializeComboLanguageValues(detectedRenderer);

        center();

        initializeEvents();
    }

    private void initializeComboLanguageValues(DetectedRenderer detectedRenderer) {
        int selectedIndex = -1;

        List<LanguageDefinitionVO> availableLanguages = detectedRenderer.getLanguageDefinitionsHolder().getAvailableLanguages();
        for (int i = 0; i < availableLanguages.size(); i++) {
            LanguageDefinitionVO availableLanguage = availableLanguages.get(i);
            selectedLanguageCombo.addItem(availableLanguage.getVisualName(), availableLanguage.getCodeRendererName());

            if (availableLanguage == detectedRenderer.getLanguageDefinition()) {
                selectedIndex = i;
            }
        }

        if (selectedIndex >= 0) {
            selectedLanguageCombo.setSelectedIndex( selectedIndex );
        }
    }

    private void createCodeEditor() {
        codeEditor = new CodeTextEditor(selectedLanguage.getProgrammingLanguage(), showLines);

        codeEditor.setValue( detectedRenderer.getCode() );
    }

    @Override
    public void show() {
        super.show();

        new Timer() {
            @Override
            public void run() {
                codeEditor.onResize(null, null);
            }
        }.scheduleRepeating(1000);

    }

    private void initializeEvents() {
        selectedLanguageCombo.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                String value = selectedLanguageCombo.getValue(selectedLanguageCombo.getSelectedIndex());
                selectedLanguage = detectedRenderer.getLanguageDefinitionsHolder().getLanguageFromRenderer(value);

                codeEditor.setProgrammingLanguage(selectedLanguage.getProgrammingLanguage());
            }
        });

        updateButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                detectedRenderer.getCodeRender().renderCode(
                        visualEditor,
                        detectedRenderer.getCodeElement(),
                        codeEditor.getValue(),
                        selectedLanguage,
                        showLines
                );

                CodeEditorDialog.this.hide(true);
            }
        });

        closeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                CodeEditorDialog.this.hide(true);
            }
        });

        showLinesCheckBox.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                showLines = event.getValue();
                codeEditor.setShowLineNumbers(showLines);
            }
        });
    }

    @Override
    protected void onPreviewNativeEvent(NativePreviewEvent event) {
        super.onPreviewNativeEvent(event);

        switch (event.getTypeInt()) {
            case Event.ONKEYDOWN:
                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
                    hide(true);
                }
                break;
        }
    }
}
