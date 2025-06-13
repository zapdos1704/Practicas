@echo off
echo Cliente pg_dump:
pg_dump --version
echo.
echo Ubicación:
where pg_dump
echo.
echo Versiones instaladas:
dir "C:\Program Files\PostgreSQL\" /AD
