call runcrud

if "%ERRORLEVEL%" == "0" goto openweb
echo.
echo runcrud has errors - breaking work
goto fail

:openweb
start https://kodilla.com
start http://localhost:8080/crud/v1/task/getTasks

:fail
echo.
echo There were errors
:end
echo.
echo Work is finished.
