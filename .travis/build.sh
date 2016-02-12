#!/bin/bash

VERSION=$(xmllint --xpath "//*[local-name()='project']/*[local-name()='version']/text()" pom.xml)

if [[ $VERSION == *"SNAPSHOT"* ]]; then
	mvn compile test
else
	mvn deploy --settings .travis/maven-settings.xml
fi
