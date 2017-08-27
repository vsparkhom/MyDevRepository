@echo off

set WHAT_TO_COMPILE= %WHAT_TO_COMPILE% src\*.java
set WHAT_TO_COMPILE= %WHAT_TO_COMPILE% src\ua\storchaka\pract5\*.java
set WHAT_TO_COMPILE= %WHAT_TO_COMPILE% src\ua\storchaka\pract5\exceptions\*.java

cls

echo Compiling...
echo -----------------

javac  -source 1.4 -target 1.4  -d class %WHAT_TO_COMPILE%
IF ERRORLEVEL 1 goto error

echo Compiling done
 
pause

goto end

:error

pause

:end