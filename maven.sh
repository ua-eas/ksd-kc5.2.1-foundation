#!/bin/bash


function main {
    mvn -Dmaven.test.skip=true install  &&
    mvn jar:jar  &&
    mvn install:install-file -Dpackaging=jar -DgroupId=org.kuali.kra -DartifactId=kc_project -Dversion=5.2.1 -DgeneratePom=true -Dfile=target/kc_project-5.2.1.jar
}

main $@

