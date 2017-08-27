::@echo off

::1. Перейти на директорию выше относительно директории, в который находится bat-файл и создать там директорию c именем ‘OSOPIS’.
cd ..
mkdir OSOPIS

::2. Создать в директории ‘OSOPIS’ поддиректорию с именем ‘TempFolder’.
mkdir OSOPIS\TempFolder

::3. В ‘TempFolder’ создать файл ‘practice.my’, куда в первую строку записать свои имя и фамилию, а во вторую – номер группы, в которой Вы учитесь.
(
echo Anhelina Parkhomchuk
echo Indn-31s
) > OSOPIS\TempFolder\practice.my

::4. Переместить файл ‘practice.my’ в директорию ‘OSOPIS’ и присвоить ему атрибут ‘read only’.
move OSOPIS\TempFolder\practice.my OSOPIS
attrib +R OSOPIS\practice.my

::5. *Вывести информацию из файла ‘practice.my’ в формате: Я, [Ваше имя и фамилия], студент/студентка [номер группы].
set FILE_NAME="OSOPIS\practice.my"
set /a c=0
setlocal ENABLEDELAYEDEXPANSION
FOR /F "delims== tokens=1 usebackq" %%i in (%FILE_NAME%) do (
  set /a c=c+1
  set "var!c!=%%i"
)
echo I'm, %var1%, a student of group %var2%.
endlocal

::6. Скопировать файл ‘practice.my’ в поддиректорию ‘TempFolder’ и переименовать его в ‘practice_.my’.
copy OSOPIS\practice.my OSOPIS\TempFolder
rename OSOPIS\TempFolder\practice.my practice_.my

::7. Дописать в конец файла ‘practice.my’ текущую дату.
echo %DATE% >> OSOPIS\practice.my

::8. Убрать атрибут ‘read only’ файла ‘practice.my’ и повторить п. 7.
attrib -R OSOPIS\practice.my
echo %DATE% >> OSOPIS\practice.my

::9. Сравнить контент файлов ‘practice.my’ и ‘practice_.my’. Результат сравнения записать в текстовый файл и открыть его.
fc /L OSOPIS\practice.my OSOPIS\TempFolder\practice_.my > OSOPIS\comparison_result.txt
OSOPIS\comparison_result.txt
pause