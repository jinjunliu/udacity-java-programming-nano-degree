# Migrating to Java 9 modules

The folder `reservation-system` contains a Maven project consisting of just one module.
(it's similar to the one on first exercise). You'll separate this app in three different modules.

Maven modules (managed by `pom.xml` files) and modules descriptor (module-info.java) complement each other. But, they 
play different roles. Maven brings the functionality of automatic, centralized dependency management. And the 
module descriptors handle the modularity at Java platform level. Let's see how they work together.

Running `mvn clean package` generates just one jar that can be run with 
`java -jar target/reservation-system-1.0-SNAPSHOT.jar`

1. On the `pom.xml` change the Java version from 1.8 to 11.
2. Change the packaging from `jar` to `pom` to allow maven submodules.
3. Under the `reservation-system` folder we need to add three different folders, each of them will contain a moduel.
    1. flightmodule
    2. hotelmodule
    3. packagemodule 
4. For each of the modules we need to declare a `pom.xml` referencing the parent module. Here's what's needed for flightmodule.
    ```
    <parent>
        <groupId>com.udacity.jpnd</groupId>
        <artifactId>reservation-system</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
     
    <artifactId>flightmodule</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>
    <name>flightmodule</name>
    ```
    Add similar `pom.xml`s for the other modules
5. Add a `modules` section to the parent `pom.xml`
    ```
    <modules>
        <module>flightmodule</module>
        <module>hotelmodule</module>
        <module>packagemodule</module>
    </modules>
    ```
6. Move the classes from parent module to each submodule (from package `com.udacity.fligth` to `flightmodule`, 
`com.udacity.hotel` to `hotelmodule`, and `com.udacity.packagesearch` to `packagemodule`). Each submodule should have
the structure of a Maven project (with the directory `src/main/java`).

7. After doing this, the classes on `packagemodule` will lose some visibility on classes, so let's add the required 
dependencies to its `pom.xml` to recover that visibility.
    ```
    <dependencies>
        <dependency>
            <groupId>com.udacity.jpnd</groupId>
            <artifactId>hotelmodule</artifactId>
            <version>1.0-SNAPSHOT</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>com.udacity.jpnd</groupId>
            <artifactId>flightmodule</artifactId>
            <version>1.0-SNAPSHOT</version>
            <scope>compile</scope>
        </dependency>
    </dependencies>
    ```
8. Move the declaration of `maven-jar-plugin` to `packagemodule` since it's the one that contains the `mainClass`.

9. Now the project is modularized at Maven level, to convert it in a Java modularized project we need to add the 
`module-info.java`, they will be in the `src/main/java` directory of each of them. These files are similar to what we
did on exercise one.

10. The project should build with `mvn clean package` but now it will create three different jars, to run them you need 
specify the dependencies like this: 
`java -cp flightmodule/target/flightmodule-1.0-SNAPSHOT.jar:hotelmodule/target/hotelmodule-1.0-SNAPSHOT.jar:packagemodule/target/packagemodule-1.0-SNAPSHOT.jar com.udacity.packagesearch.search.Main
`





