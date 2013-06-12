<?php
/*
Plugin Name: ${visual.editor.title} Ciplogic's CodeEditor (${code.renderer.title})
Plugin URI: http://codeeditor.ciplogic.com
Description: A ${visual.editor.title} visual code editor (${code.renderer.title} backend) that adds a button for adding code samples easily.
Version: ${project.version}
Author: Bogdan Mustiata
Author URI: http://ciplogic.com
License: GPLv3
*/

add_action('admin_init', 'ciplogic_${visual.editor}_${code.renderer}_load_plugin');

/**
Create Our Initialization Function
 */

function ciplogic_${visual.editor}_${code.renderer}_load_plugin() {

    if ( ! current_user_can('edit_posts') && ! current_user_can('edit_pages') ) {
        return;
    }

    if ( get_user_option('rich_editing') == 'true' ) {
        add_filter('the_editor', 'ciplogic_${visual.editor}_${code.renderer}_inject_into_editor');
    }

}

/**
Register TinyMCE Plugin
 */
function ciplogic_${visual.editor}_${code.renderer}_inject_into_editor($editor_html) {
    $url = plugins_url()."/ciplogic_codeeditor_${visual.editor}_${code.renderer}_wordpress_35";

    if ("${visual.editor}" == "mce") {
        $editor_html .= "<script src='${url}/codeeditor${visual.editor}${code.renderer}/js/tinymce-stage1-integration.js'></script>";
    }
    $editor_html .= "<script src='${url}/codeeditor${visual.editor}${code.renderer}/codeeditor${visual.editor}${code.renderer}.nocache.js'></script>";

    return $editor_html;
}

?>