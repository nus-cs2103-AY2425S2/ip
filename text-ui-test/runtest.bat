@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT
if exist data\laffy.txt del data\laffy.txt

REM compile the code into the bin folder
javac  -cp ..\src\main\java\laffy -Xlint:none -d ..\bin^
 ..\src\main\java\laffy\*.java^
 ..\src\main\java\laffy\command\*.java^
 ..\src\main\java\laffy\command\exceptions\*.java^
 ..\src\main\java\laffy\tasklist\*.java^
 ..\src\main\java\laffy\tasklist\tasks\*.java^
 ..\src\main\java\laffy\tasklist\exceptions\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin laffy.Laffy < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
