/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.render.geshi;

import com.ciplogic.web.codeeditor.editor.VisualEditor;
import com.ciplogic.web.codeeditor.render.CodeRenderer;
import com.ciplogic.web.codeeditor.render.languages.LanguageDefinitionVO;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.inject.Inject;

import static com.ciplogic.web.codeeditor.render.html.StringFormat.format;

public class GeshiCodeRenderer implements CodeRenderer {
    private String langAttribute;
    private String linesAttribute;
    private String linesAttributeValue;

    @Inject
    public GeshiCodeRenderer(GeSHiConfig config) {
        langAttribute = config.getLangAttribute();
        linesAttribute = config.getLinesAttributeName();
        linesAttributeValue = config.getLinesAttributeValue();
    }

    @Override
    public Element getCodeElement(Element element) {
        while (!"BODY".equals(element.getTagName())) {
            if (hasAttribute(element, langAttribute)) {
                return element;
            }

            element = element.getParentElement();
        }

        return null;
    }

    @Override
    public void renderCode(VisualEditor visualEditor,
                             Element codeElement,
                             String code,
                             LanguageDefinitionVO programmingLanguage,
                             boolean showLines) {
        Element container = renderCodeIntoContainer(visualEditor, code, programmingLanguage, showLines);

        if (codeElement == null) {
            insertNewElement(visualEditor, container);
        } else {
            updateExistingElement(visualEditor, codeElement, container, programmingLanguage, showLines);
        }
    }

    @Override
    public String getCodeElementLanguage(Element codeElement) {
        return codeElement.getAttribute(langAttribute);
    }

    @Override
    public String getCode(Element codeElement) {
        return codeElement.getInnerText();
    }

    @Override
    public boolean isShowLines(Element codeElement) {
        return codeElement.hasAttribute(linesAttribute) &&
                linesAttributeValue.equalsIgnoreCase(
                        codeElement.getAttribute(linesAttribute)
                );
    }

    private void insertNewElement(VisualEditor visualEditor, Element container) {
        visualEditor.insertHtml(container.getInnerHTML());
    }

    private void updateExistingElement(VisualEditor visualEditor,
                                       Element codeElement,
                                       Element createdContainer,
                                       LanguageDefinitionVO programmingLanguage,
                                       boolean showLines) {
        codeElement.setInnerHTML(createdContainer.getFirstChildElement().getInnerHTML());
        if (showLines) {
            codeElement.setAttribute(linesAttribute, linesAttributeValue);
        } else {
            codeElement.removeAttribute(linesAttribute);
        }
        codeElement.setAttribute(langAttribute, programmingLanguage.getCodeRendererName());

        createCss(visualEditor, programmingLanguage);
    }

    private boolean hasAttribute(Element element, String s) {
        return !"".equals(element.getAttribute(s));
    }

    private Element renderCodeIntoContainer(VisualEditor visualEditor, String code,
                                            LanguageDefinitionVO programmingLanguage,
                                            boolean showLines) {
        // Adding attributes does not guarantee order in the outputted HTML. Geshi is really picky if class comes first
        // instead of xml:lang
        Element container = DOM.createElement("div");

        String showLinesCode = showLines ? format("{0}='{1}'", linesAttribute, linesAttributeValue) : "";

        String preElementHtml = format("<pre " +
                "{0}='{1}' {2}></pre>",
                langAttribute,
                programmingLanguage.getCodeRendererName(),
                showLinesCode
        );

        createCss(visualEditor, programmingLanguage);

        container.setInnerHTML(preElementHtml);
        container.getFirstChildElement().setInnerText(code);

        return container;
    }

    private void createCss(VisualEditor visualEditor, LanguageDefinitionVO programmingLanguage) {
        String cssSelector = format("pre[{0}='{1}']",
                langAttribute.replace(":", "\\:"),
                programmingLanguage.getCodeRendererName()
        );

        visualEditor.getCodeClassManager().createCss(cssSelector, programmingLanguage.getVisualName());
    }

}
