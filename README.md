# Tutorial de Hibernate 4.3.8.Final

Este es un tutorial sencillo para poder probar de manera sencilla el funcionamiento de Hibernate.

## BBDD

Al ser Hibernate un ORM necesitamos una base de datos relacional sobre la que poder guardar los objetos existentes. Vamos a suponer que tenemos una BBDD MySQL en local y que usa el puerto por defecto para realizar las pruebas.

### Iniciando la BBDD
Lo primero de todo es poder tener una BBDD a la que conectarnos. Para ello vamos a crear una BBDD llamada *pojo* para ello ejecutamos

	mysqladmin -u root create pojo

Lo siguiente es poder permitir el acceso, para ello usaremos el usuario *pojo* con la contraseña *pojo*

	mysql -u root

	mysql> GRANT ALL PRIVILEGES ON pojo.* to pojo@localhost IDENTIFIED BY 'pojo';
	
Además vamos a tener que disponer de algunas tablas sobre las que hacer consultas. Para ello se dispone del script en SQL *01-CreateTables.sql* que se puede ejecutar de la siguiente manera

	 mysql pojo -u pojo -p < src/sql/01-CreateTables.sql
	 
## Ejecución
En este tutorial se emplea maven así que podemos usarlo para ejecutar el código. Para ello basta con introducir:

	mvn clean compile exec:java -Dexec.mainClass=org.example.tutorials.hibernate.hibernateTutorial.App