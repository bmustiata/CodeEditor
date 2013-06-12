(function() {
    var injectChangeSettingsInterval;

    var EXTENDED_PRE_DEFINITION = "-pre[xml::lang|lang|lines|line|id|align|class|dir<ltr?rtl|onclick|ondblclick|onkeydown|onkeypress|onkeyup|onmousedown|onmousemove|onmouseout|onmouseover|onmouseup|style|title|width|ciplogiccode]";

    injectChangeSettingsInterval = setInterval(function() {
        if (window.tinyMCE && !!window.tinyMCE.settings) {
            addSettingsOption(window.tinyMCE.settings);
            clearInterval(injectChangeSettingsInterval);
        }
    }, 1);

    function addSettingsOption(editorSettings) {
        // remove the cleanup plugin if it exists.
        editorSettings.plugins = editorSettings.plugins.replace(/,cleanup,/, ",");

        // add the pre element that supports lines, with the correct attribute order for Geshi
        if (editorSettings.extended_valid_elements) {
            editorSettings.extended_valid_elements = editorSettings.extended_valid_elements + "," +
                EXTENDED_PRE_DEFINITION;
        } else {
            editorSettings.extended_valid_elements = EXTENDED_PRE_DEFINITION;
        }
    }
})();
