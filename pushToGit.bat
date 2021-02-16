call git add .
if "%ERRORLEVEL%" == "0" goto commit
echo.
echo Cannot make -add operation
goto fail

:commit
call git commit
if "%ERRORLEVEL%" == "0" goto push
echo.
echo Cannot make -commit operation
goto fail

:push
call git push -u origin master
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Cannot make -push operation
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.