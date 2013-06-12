#!/usr/bin/env bash

rm -fr target/joomla_2_5
mkdir target/joomla_2_5

# FIXME: probably can live without this.
cp -R target/php/joomla_25/* target/joomla_2_5
cp -R target/${project.artifactId}-${project.version}/codeeditor${visual.editor}${code.renderer} target/joomla_2_5

pushd target/joomla_2_5
mv ciplogiccodeeditor.php ciplogic${visual.editor}${code.renderer}codeeditor.php
mv ciplogiccodeeditor.xml ciplogic${visual.editor}${code.renderer}codeeditor.xml
popd

# while the names in the code can't use underscore, in the zip, are ok.
pushd target/joomla_2_5
find . -type f | zip ../../build/ciplogic_${visual.editor}_${code.renderer}_codeeditor_joomla_25_${project.version}.zip -@
popd
