# Binding plugin goals

SWBAT add `souce` and `javadoc` plugins and execute them during the build.

The folder next to this file contains a very simple project to calculate the greatest common divisor. JavaDoc was used 
to document the main class. Currently, when running `mvn package` the generated output doesn't contain
the sources nor the documentation, as you can see in the folder structure.

```
.
├── classes
├── generated-sources
├── generated-test-sources
├── greatest-common-divisor-1.0-SNAPSHOT.jar
├── maven-archiver
├── maven-status
├── surefire-reports
└── test-classes
```

Having the source and documentation within the output files can be useful for other developers that are using the library.

1. Add the [maven-source-plugin](https://maven.apache.org/plugins/maven-source-plugin/usage.html) to your build and set
 it to be executed on the `package` phase by adding the next code to the `pom.xml` file. 
 Note: this should be added within `build/plugins` there's a similar group `build/pluginManagement/plugins` if
 the code is added to the latter it won't work as expected. 

```
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-source-plugin</artifactId>
  <executions>
    <execution>
      <id>attach-sources</id>
      <phase>package</phase>
      <goals>
        <goal>jar</goal>
      </goals>
    </execution>
  </executions>
</plugin>
```

2. Similarly add the [maven-javadoc-plugin](https://maven.apache.org/plugins/maven-javadoc-plugin/) plugin using the next code.

```
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-javadoc-plugin</artifactId>
  <executions>
    <execution>
      <id>attach-javadocs</id>
      <phase>package</phase>
      <goals>
        <goal>jar</goal>
      </goals>
    </execution>
  </executions>
</plugin>
``` 

3. Run `mvn package` and check the generated output.