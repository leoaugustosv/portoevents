@echo off
setlocal enabledelayedexpansion
set CP=
for /r %%f in (*.java) do (
    set CP=!CP! %%f
)
javac !CP!
endlocal