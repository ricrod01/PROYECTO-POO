# PROYECTO-POO
Proyecto de Programación Orientada a Objetos
Ricardo Rodríguez 18907

Descripción:
Proyecto de manejo de inventarios pensado para microempresas.

Necesario:
Debe tener instalado MySQL y MySQL Workbench, además descargar el conector mysql-connector-j-9.0.0.jar que se encuentra en este repositorio. También debe de correr la base de datos llamada DB.sql

Código para la terminal:
Windows

'''bash
javac -cp ".;mysql-connector-j-9.0.0.jar" src\*.java
'''

'''bash
java -cp ".;mysql-connector-j-9.0.0.jar;src" Main
'''

Linux y Mac
'''bash
javac -cp ".:mysql-connector-j-9.0.0.jar" src\*.java
'''

'''bash
java -cp ".:mysql-connector-j-9.0.0.jar:src" Main
'''


