/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.render.html;

import com.ciplogic.gwtui.ProgrammingLanguage;
import com.ciplogic.web.codeeditor.editor.VisualEditor;
import com.ciplogic.web.codeeditor.render.CodeRenderer;
import com.ciplogic.web.codeeditor.render.languages.LanguageDefinitionVO;
import com.google.gwt.dom.client.Element;
import com.google.inject.Inject;

/**
 * An HTML code renderer that doesn't needs another external resource (plugin or CSS) for rendering.
 */
public class HtmlCodeRenderer implements CodeRenderer {
    public static final String SHOW_LINE_CLASS_MARKER = "ciplogic-show-lines";
    public static final String CIPLOGIC_CODE = "ciplogic-code-";
    private CodeMirrorRender codeMirrorRender;

    private ClassStyleResolver classStyleResolver;

    @Inject
    public HtmlCodeRenderer(CodeMirrorRender codeMirrorRender, ClassStyleResolver classStyleResolver) {
        this.codeMirrorRender = codeMirrorRender;
        this.classStyleResolver = classStyleResolver;
    }

    @Override
    public Element getCodeElement(Element element) {
        while (!"BODY".equals(element.getTagName())) {
            if (hasClass(element, "ciplogic-code")) {
                return element;
            }

            element = element.getParentElement();
        }

        return null;
    }

    private boolean hasClass(Element element, String cssClassName) {
        String[] cssClasses = getCssClasses(element);

        for (String cssClass : cssClasses) {
            if (cssClassName.equals(cssClass)) {
                return true;
            }
        }

        return false;
    }

    private String[] getCssClasses(Element element) {
        String classAttribute = element.getAttribute("class");
        if (classAttribute == null) {
            return new String[0];
        }

        return classAttribute.split("\\s+");
    }

    @Override
    public void renderCode(VisualEditor visualEditor, Element codeElement, String code, LanguageDefinitionVO programmingLanguage, boolean showLines) {
        if (codeElement != null) {
            updateCodeElement(codeElement, code, programmingLanguage.getProgrammingLanguage(), showLines);
        } else {
            createCodeElement(visualEditor, code, programmingLanguage.getProgrammingLanguage(), showLines);
        }
    }

    private void updateCodeElement(Element codeElement, String code, ProgrammingLanguage programmingLanguage, boolean showLines) {
        String renderedCode = renderInternalCode(code, programmingLanguage, showLines);
        codeElement.setInnerHTML(renderedCode);

        updateShowLines(codeElement, showLines);
        updateLanguage(codeElement, programmingLanguage);
    }

    private void updateShowLines(Element codeElement, boolean showLines) {
        if (showLines) {
            codeElement.addClassName(SHOW_LINE_CLASS_MARKER);
        } else {
            codeElement.removeClassName(SHOW_LINE_CLASS_MARKER);
        }
    }

    private void updateLanguage(Element codeElement, ProgrammingLanguage programmingLanguage) {
        String[] cssClasses = getCssClasses(codeElement);

        for (String cssClass : cssClasses) {
            if (cssClass.startsWith(CIPLOGIC_CODE)) {
                codeElement.removeClassName(cssClass);
            }
        }

        codeElement.addClassName(CIPLOGIC_CODE + programmingLanguage.toString());
    }

    private void createCodeElement(VisualEditor visualEditor, String code, ProgrammingLanguage programmingLanguage, boolean showLines) {
        String internalCodeAsHtml = renderInternalCode(code, programmingLanguage, showLines);
        String renderedCode = renderContainer(programmingLanguage, showLines, internalCodeAsHtml);

        visualEditor.insertHtml(renderedCode);
    }

    private String renderContainer(ProgrammingLanguage programmingLanguage, boolean showLines, String internalCode) {
        return StringFormat.format("<div " +
                "class='ciplogic-code ciplogic-code-{0} {1}' " +
                "style='font-family : {2}; border : gray solid 1px; font-size: 100%'>" +
                "{3}" +
                "</div>",
                programmingLanguage.toString(),
                showLines ? SHOW_LINE_CLASS_MARKER : "",
                classStyleResolver.getStyle(null, "fontFamily"),
                internalCode
        );
    }

    private String renderInternalCode(String code, ProgrammingLanguage programmingLanguage, boolean showLines) {
        return StringFormat.format(
                (showLines ? getShowLinesHtml(code) : "") +
                        "<pre style='margin:0;" +
                        "font-family:{0};" +
                        "font-size:100%;" +
                        "font-style:normal;" +
                        "line-height:1.3;" +
                        "overflow-x:auto;" +
                        "overflow-y:hidden;" +
                        "padding:0;" +
                        "background-color:{1};'>" +
                        "{2}" +
                        "</pre>",
                classStyleResolver.getStyle(null, "fontFamily"),
                classStyleResolver.getStyle("CodeMirror", "backgroundColor"),
                codeMirrorRender.parseCode(code, programmingLanguage)
        );
    }

    @Override
    public String getCodeElementLanguage(Element codeElement) {
        String[] cssClasses = getCssClasses(codeElement);
        for (String cssClass : cssClasses) {
            if (cssClass.startsWith(CIPLOGIC_CODE)) {
                return cssClass.substring(CIPLOGIC_CODE.length());
            }
        }

        return null;
    }

    @Override
    public String getCode(Element codeElement) {
        return codeElement.getElementsByTagName("pre").getItem(0).getInnerText();
    }

    @Override
    public boolean isShowLines(Element codeElement) {
        return hasClass(codeElement, SHOW_LINE_CLASS_MARKER);
    }

    public String getShowLinesHtml(String code) {
        StringBuilder stringBuilder = new StringBuilder("");

        int lineCount = countNumberOfLines(code);
        int numberSpaces = computeNumberSpaces(lineCount);

        stringBuilder.append(StringFormat.format("<div " +
                "style='" +
                    "background-color:{0};" +
                    "border-right:{1};" +
                    "font-family:{2};" +
                    "color:{3};" +
                    "padding-right:{4};" +
                    "display:inline-block;" +
                    "float:left;" +
                    "line-height:1.3;" +
                    "width:{5}em;" +
                    "text-align:right;" +
                    "font-size:100%" +
                "'>",
            classStyleResolver.getStyle("CodeMirror-linenumber CodeMirror-gutters", "backgroundColor"),
            classStyleResolver.getStyle("CodeMirror-linenumber CodeMirror-gutters", "borderRight"),
            classStyleResolver.getStyle("CodeMirror-linenumber CodeMirror-gutters", "fontFamily"),
            classStyleResolver.getStyle("CodeMirror-linenumber CodeMirror-gutters", "color"),
            classStyleResolver.getStyle("CodeMirror-linenumber CodeMirror-gutters", "paddingRight"),
            String.valueOf(numberSpaces > 2 ? numberSpaces : 2)
        ));

        for (int i = 1; i <= lineCount; i++) {
            stringBuilder.append("<div>")
                    .append(i)
                    .append("</div>");
        }

        stringBuilder.append("</div>");

        return stringBuilder.toString();
    }

    private int countNumberOfLines(String code) {
        int lineCount = 1; // there is always a line, even if it's empty
        int lastIndex = -1;

        while ((lastIndex = code.indexOf("\n", lastIndex + 1)) != -1) {
            lineCount++;
        }

        return lineCount;
    }

    private int computeNumberSpaces(int lineCount) {
        int result = 0;
        while (lineCount > 0) {
            result++;
            lineCount = lineCount / 10;
        }

        return result;
    }
}
