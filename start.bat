@echo off
cd %~dp0\frontend
start npm start

cd %~dp0\backend
mvn spring-boot:run
