#!/usr/bin/env bash

rm -fr target/wordpress_3_5
mkdir target/wordpress_3_5

# FIXME: probably can live without this.
cp -R target/php/wordpress_35/* target/wordpress_3_5
cp -R target/${project.artifactId}-${project.version}/codeeditor${visual.editor}${code.renderer} target/wordpress_3_5

pushd target/wordpress_3_5
mv wpcodeeditor.php ciplogic_codeeditor_${visual.editor}_${code.renderer}_wordpress_35.php
popd

# while the names in the code can't use underscore, in the zip, are ok.
pushd target/wordpress_3_5
find . -type f | zip ../../build/ciplogic_codeeditor_${visual.editor}_${code.renderer}_wordpress_35.zip -@
popd
