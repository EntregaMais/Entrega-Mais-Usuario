@echo off 
Rem  Roda a aplicacao localmente
mvn clean && docker compose up -d postgres && mvn spring-boot:run