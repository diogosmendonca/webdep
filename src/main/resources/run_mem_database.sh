#!/bin/bash
java -cp $HOME/.m2/repository/org/hsqldb/hsqldb/2.3.1/hsqldb-2.3.1.jar org.hsqldb.server.Server --database.0 mem:. --dbname.0 webdep

