/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.render.syntaxhighlight;

import com.ciplogic.web.codeeditor.editor.VisualEditor;
import com.ciplogic.web.codeeditor.render.CodeRenderer;
import com.ciplogic.web.codeeditor.render.languages.LanguageDefinitionVO;
import com.google.gwt.dom.client.Element;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.DOM;

import static com.ciplogic.web.codeeditor.render.html.StringFormat.format;

public class SyntaxHighlightCodeRenderer implements CodeRenderer {

    public static final String SHOW_LINES_CLASS_ATTRIBUTE = "gutter";

    @Override
    public Element getCodeElement(Element element) {
        while (!"BODY".equals(element.getTagName())) {
            if (hasBrushCssAttribute(element) ) {
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
        return getCssAttribute(codeElement, "brush");
    }

    @Override
    public String getCode(Element codeElement) {
        return codeElement.getInnerText();
    }

    @Override
    public boolean isShowLines(Element codeElement) {
        return "true".equals(getCssAttribute(codeElement, SHOW_LINES_CLASS_ATTRIBUTE));
    }

    private boolean hasBrushCssAttribute(Element element) {
        return getCssAttribute(element, "brush") != null;
    }

    private String getCssAttribute(Element element, String cssAttribute) {
        RegExp regexp = RegExp.compile(cssAttribute + "\\s*:\\s*([^;]+)");
        String classAttribute = element.getAttribute("class");

        MatchResult match = regexp.exec(classAttribute);

        if (match == null) {
            return null;
        } else {
            return match.getGroup(1);
        }
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

        codeElement.setAttribute("class", format("brush: {0}; " + SHOW_LINES_CLASS_ATTRIBUTE + ": {1}",
                programmingLanguage.getCodeRendererName(),
                Boolean.toString(showLines)
        ));

        createCss(visualEditor, programmingLanguage);
    }

    private Element renderCodeIntoContainer(VisualEditor visualEditor, String code,
                                            LanguageDefinitionVO programmingLanguage,
                                            boolean showLines) {
        // Adding attributes does not guarantee order in the outputted HTML. Geshi is really picky if class comes first
        // instead of xml:lang
        Element container = DOM.createElement("div");

        String preElementHtml = format("<pre class='brush: {0}; " + SHOW_LINES_CLASS_ATTRIBUTE + ": {1}'></pre>",
                programmingLanguage.getCodeRendererName(),
                Boolean.toString(showLines)
        );

        createCss(visualEditor, programmingLanguage);

        container.setInnerHTML(preElementHtml);
        container.getFirstChildElement().setInnerText(code);

        return container;
    }

    private void createCss(VisualEditor visualEditor, LanguageDefinitionVO programmingLanguage) {
        String cssSelector = format("pre[class*='brush: {0};']",
            programmingLanguage.getCodeRendererName()
        );

        // FIXME:
        visualEditor.getCodeClassManager().createCss(cssSelector, programmingLanguage.getVisualName());
    }
}
