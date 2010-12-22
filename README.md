Internal demo of Mongo with Java
================================

The purpose of this project is to demonstrate usage of the "official" MongoDB driver for Java.
I am a new Java developer, so it's likely that I am not following several best practices and 
cultural conventions.

Dependencies
------------

Put mongo-2.3.jar and junit-4.8.2.jar in the base directory

Compilation instructions
------------------------

    javac src/Order.java -d . -classpath ./mongo-2.3.jar:.
    javac src/Customer.java -d . -classpath ./mongo-2.3.jar:.
    javac src/MongoRealTest.java -d . -classpath ./mongo-2.3.jar:.
    javac src/MongoRealTestTest.java -d . -classpath ./mongo-2.3.jar:./junit-4.8.2.jar:.

Execution
---------

**Execute unit test**

    java -classpath mongo-2.3.jar:junit-4.8.2.jar:. org.junit.runner.JUnitCore MongoRealTestTest
  
**Execute main class; insert and query documents**
  
    java -classpath mongo-2.3.jar:. MongoRealTest

TODO
----

Use Ant or Ivy for automated build and unit tests (?)

