1) Install maven: 
   a) http://maven.apache.org/download.cgi
   b) Add maven bin to your PATH 
2) Set JAVA_HOME to your jdk path
3) Setup eclipse workspace and project
   a) mvn -Declipse.workspace=<path-to-eclipse-workspace> eclipse:add-maven-repo
   b) mvn eclipse:eclipse
4) mvn package (should build correctly)

... rejoice -cbarber
