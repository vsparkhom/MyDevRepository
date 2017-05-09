@echo off
set /p i=1
for /f "tokens=*" %%a in (practice.my) do (
  echo %i%
  echo line=%%a
  set /a i=%i%+1
)
pause


#for /f "delims== tokens=1,2" %%G in (practice.my) do set %%G=%%H
#pause