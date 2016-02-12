#!/bin/bash

VERSION=$(xmllint --xpath "//*[local-name()='project']/*[local-name()='version']/text()" pom.xml)

if [[ $VERSION == *"SNAPSHOT"* ]]; then
	echo "Building SNAPSHOT"
	mvn compile test
else
	echo "Building RELEASE"
	mvn deploy --settings .travis/maven-settings.xml
fi
