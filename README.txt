1) Install maven: 
   a) http://maven.apache.org/download.cgi
   b) Add maven bin to your PATH 
2) Set JAVA_HOME to your jdk path
3) Setup eclipse workspace and project
   a) mvn -Declipse.workspace=<path-to-eclipse-workspace> eclipse:add-maven-repo
   b) mvn eclipse:eclipse
   c) Copy test.properties.tmpl to test.properties and update for your test database. 
      NOTE: tables are dropped in the test databases as part of the tests!
4) mvn package (should build correctly)
   a) mvn -DskipTests=true package (to skip tests when packaging)

... rejoice -cbarber
