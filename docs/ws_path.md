# WEB SERVICE PATH LIST

All path is preceded by context, context corresponding to :
- 1 : device path
- 2 : path you give in tomcat-deployment 

My local example : localhost:8080/tma

## Command

ws src path :

	/command

simulate function path :
	/command/simulate?idMagasin=1

Path parameter take idMagasin id to simulate it's new command

update function path : 

	/command/update?idCommande=1,idEtat=1	

Development in progress,
Parameter idCommande take id of command updated
Parameter idEtat take id of target state update

clear all command : 

	/command/clear

Development in progress

clear all 4 status command : 

	/command/cleartoday

Development in progres

## CommandLog

ws src path : 
	
	/commandlog

log reader function : 

	/commandlog/read

## Monitor

ws src path : 

	/monitor

Information reading : 

	/read

in progress, actually working but don't return target db version

update db version :

	/update


