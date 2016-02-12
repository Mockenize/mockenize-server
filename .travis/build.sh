#!/bin/bash

VERSION=$(xmllint --xpath "//*[local-name()='project']/*[local-name()='version']/text()" pom.xml)
echo "Detected version: $VERSION"

if [ $VERSION=~*SNAPSHOT* ]; then
	echo "Building SNAPSHOT"
	mvn clean test --settings .travis/maven-settings.xml
else
	echo "Building RELEASE"
	mvn clean deploy --settings .travis/maven-settings.xml
fi
