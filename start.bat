@echo off
cd %~dp0\frontend
npm install && start npm start

cd %~dp0\backend
start mvn spring-boot:run
