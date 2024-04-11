------------
Requirements
------------
Install Java and Gradle. The following is what I used to compile and run the code:

- java 17.0.10 2024-01-16 LTS
- Gradle 8.6
- Windows, Mac, Linux (My OS: Mac OS X 13.1 x86_64)

--------------------
Build Gradle scripts
--------------------
Enter the following command line script in root project folder (same level as build.gradle):
```
gradle wrapper
```

--------------
Gradle scripts
--------------
Starts puzzle app, entry point ~/src/main/java/puzzle/PuzzleApp.java:
```
./gradlew run
```

Run all test cases in ~/src/test/java/*:
```
./gradlew test
```

Build project (compiles code / classes, runs tests etc):
```
./gradlew build
```

-----
Notes
-----
Use the following command to ignore gradle console logs such as "<=========----> 75% EXECUTING [5s]":
```
./gradlew run --console=plain
```

View generated test case coverage in your local browser at:
```
~/puzzle/build/reports/tests/test/index.html
```
