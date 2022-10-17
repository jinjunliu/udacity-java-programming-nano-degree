# JLink

The `gcd` folder contains an executable class that calculates the greatest common divisor of two numbers.

1. Create a jar from it executing 

    ```
    mvn clean package
    ```

2. Check the dependencies of the generated jar with jdeps: 
    ```
    jdeps target/gcd-1.0-SNAPSHOT.jar
    ```

3. Create a custom JRE to run this jar:
    ```
    jlink --module-path "$JAVA_HOME/jmods" --module-path target/classes --add-modules com.udacity.gcd --output tinyJRE
    ```

4. Check the size of this image
    ```
    du -sh tinyJRE
    ```

5. Try running the jar with the custom image. 
    ```
    tinyJRE/bin/java -jar target/gcd-1.0-SNAPSHOT.jar 
    ```