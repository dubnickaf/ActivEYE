# ActivEYE REST API instructions

## How to start the application
* Run ``mvn clean install`` in top level directory
* Run ``mvn tomcat7:run`` in **ActivEYE-Webapp** directory

## How to test the REST API
* Execute the following commands from top to bottom

## ACTIVITY
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
