------------
Requirements
------------
- java 17.0.10 2024-01-16 LTS
- Windows, iOS, Linux

--------
Commands
--------
# starts puzzle app, entry point ~/src/main/java/puzzle/PuzzleApp.java:
./gradlew run

# run all test cases in ~/src/test/java/*:
./gradlew test

# build project (compiles code / classes, runs tests etc):
./gradlew build

-----
Notes
-----
# use the following command to ignore gradle console logs such as "<=========----> 75% EXECUTING [5s]":
./gradlew run --console=plain

# View generated test case coverage in your local browser at:
~/puzzle/build/reports/tests/test/index.html
