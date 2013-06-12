<?php
/**
 * @version     ${project.version}
 * @package     Joomla
 * @subpackage  System
 * @copyright   Copyright (C) 2013 Bogdan Mustiata <bogdan.mustiata@gmail.com>
 * @license     GNU GPL v3.0
 */

defined('_JEXEC') or die;

class plgButtonCiplogic${visual.editor}${code.renderer}codeeditor extends JPlugin {
    public function onDisplay($name, $asset, $author) {
        if ("${visual.editor}" == "mce") {
            JFactory::getDocument()->addScript(JURI::root().'plugins/editors-xtd/ciplogic${visual.editor}${code.renderer}codeeditor/codeeditor${visual.editor}${code.renderer}/js/tinymce-stage1-integration.js');
        }
        JFactory::getDocument()->addScript(JURI::root().'plugins/editors-xtd/ciplogic${visual.editor}${code.renderer}codeeditor/codeeditor${visual.editor}${code.renderer}/codeeditor${visual.editor}${code.renderer}.nocache.js');

        return false;
    }
}

?>
