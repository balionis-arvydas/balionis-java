# Setup MySQL database

See scripts at 
```
.\myproject\myproject-service\src\main\sql\database_create.sql
.\myproject\myproject-service\src\main\sql\table_create.sql
.\myproject\myproject-service\src\main\sql\static_insert.sql
```

# Setup Tomcat 

See data source at
```
.\myproject\myproject-web\src\test\resources\context.xml 
```

# Compile 

1. mvn clean install 

_Note: If you don’t have MySQL running, then tests will fail._

# Deploy to $TOMCAT_HOME/webapps

```
.\myproject\myproject-web\target\myproject-web-1.0-SNAPSHOT.war 
```

# Run

```
http://localhost:8080/myproject-web-1.0-SNAPSHOT/welcome
```