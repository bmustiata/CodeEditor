#!/usr/bin/env bash

mvn clean
rm -fr build
mkdir build

# MCE builds
mvn clean install -Pproduction,mce,html
. target/bin/package_joomla_25.sh
. target/bin/package_wordpress_35.sh

mvn clean install -Pproduction,mce,geshi
. target/bin/package_joomla_25.sh

# wordpress has a quite different GeSHi impl.
mvn clean install -Pproduction,mce,geshiwp
. target/bin/package_wordpress_35.sh

mvn clean install -Pproduction,mce,syntaxhighlight
. target/bin/package_wordpress_35.sh
. target/bin/package_joomla_25.sh
