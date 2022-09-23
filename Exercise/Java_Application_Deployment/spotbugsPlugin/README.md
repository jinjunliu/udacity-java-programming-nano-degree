SWBAT add SpotBugs plugin and find possible errors within the code.

[Spotbugs](https://spotbugs.github.io/) can help you find potential bugs on your Java programs by using static analysis
to detect code instances that are likely to be errors.

One option to use it is through its maven plugin. We'll use it in that way for this exercise.

The attached code is the same as previous exercise, but it includes an extra class containing errors that are 
commonly picked up by spotbugs.

1. Add a reporting section to the `pom.xml`, within this section add the spotbugs plugin. Similar to the code below.

```
<reporting>
    <plugins>
        <plugin>
            <groupId>com.github.spotbugs</groupId>
            <artifactId>spotbugs-maven-plugin</artifactId>
            <version>4.1.4</version>
        </plugin>
    </plugins>
</reporting>
```

2. Run `mvn compile site`.

3. Check the generated report on `target/site/spotbugs.html`. What error patterns does it show?

4. Optional check what other errors can be detected by spotbugs on [their site](https://spotbugs.readthedocs.io/en/latest/bugDescriptions.html).