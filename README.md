## Build jar

mvn clean package


## Install jar into local repository

mvn install

## Manually Install into local repository

Install the jar into local repository

mvn install:install-file -Dfile=monoinvoices-client-1-0.0.jar -DgroupId=net.sinticbolivia.facturacion.client -DartifactId=monoinvoices-client -Dversion=1.0.0