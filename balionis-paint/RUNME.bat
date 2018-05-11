@rem 
@echo off 

set CURDIR=%CD%
set CURVER="1.0-SNAPSHOT"

if "%JAVA_HOME%" == "" (
   echo "ERROR: JAVA_HOME not found"
   goto :EOF
)

IF NOT EXIST "%JAVA_HOME%\bin\java.exe" (
   echo "ERROR: JAVA_HOME not found"
   goto :EOF
)

set EXEJAR="%CURDIR%\build\libs\balionis-paint-%CURVER%.jar"

IF NOT EXIST "%EXEJAR%" (
   echo "ERROR: %EXEJAR% not found. Have you run gradle bootJar?"
   goto :EOF
)

"%JAVA_HOME%/bin/java.exe" -jar "%EXEJAR%"

set STATUS=%ERRORLEVEL%

IF /I "%STATUS%" NEQ "0" (
    ECHO "ERROR: java failed with %STATUS%"
)