/**
 * Copyright (c) 2013, ciplogic.com - Bogdan Mustiata. All rights reserved.
 * For licensing, see the LICENSE file from the project's root folder.
 */
package com.ciplogic.web.codeeditor.render.html;

import com.ciplogic.web.codeeditor.render.languages.LanguageDefinitionsHolder;

import static com.ciplogic.gwtui.ProgrammingLanguage.*;

public class HtmlLanguageDefinitionsHolder extends LanguageDefinitionsHolder {
    public HtmlLanguageDefinitionsHolder() {
        addLanguage("Text", TEXT.toString(), TEXT);
        addLanguage("Assembler (ARM)", ASM_ARM.toString(), ASM_ARM);
        addLanguage("Assembler (x86)", ASM_X86.toString(), ASM_X86);
        addLanguage("Visual Basic (.NET)", VB_NET.toString(), VB_NET);
        addLanguage("C", C.toString(), C);
        addLanguage("C++", CPP.toString(), CPP);
        addLanguage("C#", CSHARP.toString(), CSHARP);
        addLanguage("CSS", CSS.toString(), CSS);
        addLanguage("Diff", DIFF.toString(), DIFF);
        addLanguage("Groovy", GROOVY.toString(), GROOVY);
        addLanguage("HTML 4", HTML.toString(), HTML);
        addLanguage("INI", PROPERTIES_FILE.toString(), PROPERTIES_FILE);
        addLanguage("Java", JAVA.toString(), JAVA);
        addLanguage("JavaScript", JAVASCRIPT.toString(), JAVASCRIPT);
        addLanguage("MySql", "MYSQL", MARIA_DB);
        addLanguage("Perl", PERL.toString(), PERL);
        addLanguage("PHP", PHP.toString(), PHP);
        addLanguage("PHP + HTML", PHP_HTML.toString(), PHP_HTML);
        addLanguage("PowerShell", POWERSHELL.toString(), POWERSHELL);
        addLanguage("Python", PYTHON.toString(), PYTHON);
        addLanguage("Ruby", RUBY.toString(), RUBY);
        addLanguage("Shell", SHELL.toString(), SHELL);
        addLanguage("SQL", SQL.toString(), SQL);
        addLanguage("XML", XML.toString(), XML);
    }
}
