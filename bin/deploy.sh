
# cleanup existing folders
sudo rm -fr /var/www/joomla_25/plugins/editors-xtd/geshicodeeditor/codeeditor

# update resources.
sudo cp -R target/codeeditor-1.0/codeeditor /var/www/joomla_25/plugins/editors-xtd/ciplogiccodeeditor

sudo cp src/main/php/joomla_25/codeeditor.php /var/www/joomla_25/plugins/editors-xtd/ciplogiccodeeditor/geshicodeeditor.php
