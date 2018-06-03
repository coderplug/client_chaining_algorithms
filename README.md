# README

This repository is used for demo Forward and backward chaining web service client application. 

This is only server part of the web service, server source code can be reached[here](https://github.com/coderplug/rest_chaining_algorithms).
## Chaining Application system requirements

* JDK 1.7
* Maven 3
* Internet connection

## Setup (using IntelliJ IDEA)

* Clone git repo
* Project is based on *Maven*, thus import project to IntelliJ IDEA by:
  * File -> Open... -> pick `pom.xml` file.
* Setup application server (Apache TomEE):
    1. Download WebProfile, ZIP from: [http://tomee.apache.org/downloads.html](http://tomee.apache.org/downloads.html)
    2. Unzip
    4. In IntelliJ IDEA: register "TomEE Server" -> local:
        * Press "Fix", choose "exploded war" as artifact
        * Set page: `http://localhost:8081/`
        * Set port: `8081`
    5. Run the server, project should start successfully.  
* Note: To change service resource change location for `WebTarget` object in `src/main/java/client/RESTClient`
* Note: To change DB checkboxes change `web/index.xhtml`:
    1. Change `<p:selectManyCheckbox id="databases" value="#{data.databases}">`
        * Each line inside tag has `<f:selectItem itemLabel="X" itemValue="Y" />` where:
            * X - visible name
            * Y - DB name (`server` column in server Multi DB). Current index page shows all test cases, format `[F,B][1-9]`
