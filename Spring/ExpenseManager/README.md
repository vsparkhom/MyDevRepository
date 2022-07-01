# Expense Manager Spring project

**How to run project on local machine**

1. Run MySQL database server
    * Enable MySQL80 service (in _Services_)
    * Open MySQL Workbench 8.0 CE
    * Connect to the required DB using command:
    ```sql
    USE exp_man_spring_db; 
    ```

2. Checkout *spring_projects_expense_manager* branch in Git

3. Navigate to project folder: /d/Vladimir/Repositories/github/Spring/ExpenseManager/

4. Build latest version of project using Maven:
```
mvn clean install
```

5. Deploy most recent project version to the local _Apache Tomcat_ server using  following commands (or execute _deploy.sh_ script)
```bash
#!/bin/sh
rm -rf /c/apache-tomcat-9.0.62/webapps/ExpenseManager
rm /c/apache-tomcat-9.0.62/webapps/ExpenseManager.war
cp /d/Vladimir/Repositories/github/Spring/ExpenseManager/target/ExpenseManager.war /c/apache-tomcat-9.0.62/webapps
```

6. Start local _Apache Tomcat_ server
```
cd /c/apache-tomcat-9.0.62/bin/
./startup.sh
```

7. Open [project page](http://localhost:8080/ExpenseManager/summary) in browser
