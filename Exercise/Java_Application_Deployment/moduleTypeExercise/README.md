 The `modules` folder contains a project that has been separated in modules, `com.udacity.flight` is a small module 
 stubbing a search engine for getting flights availability, similarly `com.udacity.hotel` simulates a search 
 engine for hotel availability.
 
 `com.udacity.packagesearch` uses the previous two modules to get a list of available hotels and flights
 and show it to the user to create a package. 
 
 The project can be compiled when by running this command from the `modules` directory:
 
```
javac -d out --module-source-path . --module com.udacity.flight,com.udacity.hotel,com.udacity.packagesearch
```
 
 The information on all `module-info.java` is missing. So trying to compile it will fail.    
 
 1. Running the compile command shows visibility errors, add the missing `requires` and `exports` to each 
 `module-info.java` 
 2. After adding them try to compile again, if everything works run the program with the next command:
```
java --module-path out --module com.udacity.packagesearch/com.udacity.packagesearch.search.Main
```
 3. Uncomment the code related to manager information, and compile again, the code compiles, but what happens when you 
 run it?
 
 4. Fix the previous error by adding an `opens` `to` sentence to the `com.udacity.hotel` module definition. 