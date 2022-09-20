# Starter code

This folder contains several Java files, the main goal is that you can generate the `*.class` and `*.jar` files.

The code might look familiar, it's a simplification of a user validator using the strategy pattern. There are two 
strategies, one of them checks the user's email, and the other one checks the user's phone.

At the beginning these are the files that are on the directory:

```
EmailCheck.java
PhoneCheck.java
README.md
User.java
UserValidator.java
UserValidatorExample.java
ValidationStrategy.java
```

From a terminal, compile the files with the next command: 

```
javac UserValidatorExample.java
```

Since `UserValidatorExample.java` has dependencies on the other files they will be compiled as well.

So we'll end up with the next list of files:

```
EmailCheck.class
EmailCheck.java
PhoneCheck.class
PhoneCheck.java
README.md
User.class
User.java
UserValidator.class
UserValidator.java
UserValidatorExample.class
UserValidatorExample.java
ValidationStrategy.class
ValidationStrategy.java
```

As you can see each `.java` file has its correspondent `.class` file.

We can execute them with this command:

```
java UserValidatorExample
```

Having the compiled files scattered might result in brittle executions, so it's better to gather all of them
in a packaged `jar` file. We can do it with the command:

```
jar -cfe validator.jar UserValidatorExample *.class
```

And we can run the `jar` file with:

```
java -jar validator.jar
```

`jar` files are built on ZIP fomat, you can uncompress it by using:

```
jar -xf validator.jar
```

You'll see there's a `META-INF` folder that contains a `MANIFEST.MF` file, pointing out what's the main class':

```
Manifest-Version: 1.0
Created-By: 11.0.2 (Oracle Corporation)
Main-Class: UserValidatorExample
```

