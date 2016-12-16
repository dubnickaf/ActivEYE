# ActivEYE REST API instructions

## How to start the application
* Run ``mvn clean install`` in top level directory
* Run ``mvn tomcat7:run`` in **ActivEYE-Webapp** directory

## How to test the REST API
* Execute the following commands from top to bottom

### ACTIVITY
**create**
```
curl -i -X POST -H "Content-type: application/json" -d '{"name": "utekanie", "caloriesRatio": "666"}' http://localhost:8080/pa165/rest/activities/create
```

**update**
```
curl -i -X PUT -H "Content-type: application/json" -d '{"id": "5", "name": "utekanie", "caloriesRatio": "667"}' http://localhost:8080/pa165/rest/activities/update
```

**delete**
```
curl -i -X DELETE http://localhost:8080/pa165/rest/activities/delete/5
```

**findById**
```
curl -i -X GET http://localhost:8080/pa165/rest/activities/get/4
```

**findByName**
```
curl -i -X GET http://localhost:8080/pa165/rest/activities/get?name=Cycling
```
(if using fish escape the "?" with "\")

**findAll**
```
curl -i -X GET curl -i -X GET http://localhost:8080/pa165/rest/activities/get/all
```

___

### GROUP
**create**
```
curl -i -X POST -H "Content-type: application/json" -d '{"creatorsUserId":1,"users":[{"id":9,"name":"user8","emailAddress":"user8@mail.com","bornDate":"2008-02-01","gender":"MALE","role":"USER"}],"name":"CEPECKARI"}' http://localhost:8080/pa165/rest/groups/create 
```
**update**
```
curl -i -X PUT -H "Content-type: application/json" -d '{"id":6,"creatorsUserId":1,"users":[],"name":"CEPECKARIxxxxxx"}' http://localhost:8080/pa165/rest/groups/update
```
**delete**
```
curl -i -X DELETE http://localhost:8080/pa165/rest/groups/delete/6
```
**findById**
```
curl -i -X GET http://localhost:8080/pa165/rest/groups/get/1
```
**findAll**
```
curl -i -X GET http://localhost:8080/pa165/rest/groups/get/all
```
**findAllUsers**
```
curl -i -X GET http://localhost:8080/pa165/rest/groups/get/all/1
```
**addUser** (test addUser to group scenario)
```
curl -i -X GET http://localhost:8080/pa165/rest/groups/get/1

curl -i -X POST -H "Content-type: application/json" -d '{"id":4,"name":"user3","emailAddress":"user3@mail.com","bornDate":"2003-02-01","gender":"FEMALE","role":"USER"}' http://localhost:8080/pa165/rest/groups/update/1

curl -i -X GET http://localhost:8080/pa165/rest/groups/get/1
```
