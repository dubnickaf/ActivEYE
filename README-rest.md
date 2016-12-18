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
___

### RECORD
**create**
```
curl -i -X POST -H "Content-type: application/json" -d '{"user":{"id":1,"name":"user0","emailAddress":"user0@mail.com","bornDate":"2000-02-01","gender":"MALE","role":"USER"},"activity":{"id":1,"name":"Running","caloriesRatio":654.00}, "startDate":"2016-01-01T12:10:32.864","endDate":"2016-01-01T12:25:32.864"}' http://localhost:8080/pa165/rest/records/create
```
**update**
```
curl -i -X POST -H "Content-type: application/json" -d '{"user":{"id":1,"name":"user0","emailAddress":"user0@mail.com","bornDate":"2000-02-01","gender":"MALE","role":"USER"},"activity":{"id":1,"name":"Running","caloriesRatio":654.00},"burnedCalories":111.18,"startDate":"2015-01-01T12:10:32.864","endDate":"2016-01-01T12:20:32.864"}' http://localhost:8080/pa165/rest/records/create
```
**delete**
```
curl -i -X DELETE http://localhost:8080/pa165/rest/records/delete/11
```
**findById**
```
curl -i -X GET http://localhost:8080/pa165/rest/records/get/10
```
**findAll**
```
curl -i -X GET http://localhost:8080/pa165/rest/records/get/all
```
___

### USER
**login**
```
curl -i -X POST -H "Content-type: application/json" -d '{"email":"user0@mail.com", "password":"user0"}' http://localhost:8080/pa165/rest/users/login
```
**register**
```
curl -i -X POST -H "Content-type: application/json" -d '{"emailAddress":"thenewcustomer@mail.com"}' http://localhost:8080/pa165/rest/users/register?password=stronkpass
```
(if using fish escape the "?" with "\")

**update**
```
curl -i -X PUT -H "Content-type: application/json" -d '{"id":1,"name":"user0","emailAddress":"carl.pilkington@mail.com","bornDate":"2015-02-01","gender":"MALE","role":"USER"}' http://localhost:8080/pa165/rest/users/update
```
**delete**
```
curl -i -X DELETE -H "Content-type: application/json" -d '{"id":10,"emailAddress":"user9@mail.com","name":"User9","bornDate":"2009-01-01","gender":"FEMALE","role":"USER"}' http://localhost:8080/pa165/rest/users/delete
```
**findAll**
```
curl -i -X GET http://localhost:8080/pa165/rest/users/all
```
**findById**
```
curl -i -X GET http://localhost:8080/pa165/rest/users/get/1

```
**findByEmail**
```
curl -i -X GET http://localhost:8080/pa165/rest/users/get?email=user0@mail.com
```
(if using fish escape the "?" with "\")

**findUserWithRecordsById**
```
curl -i -X GET http://localhost:8080/pa165/rest/users/getWRecords/1
```
**findUserWithRecordsByEmail**
```
curl -i -X GET http://localhost:8080/pa165/rest/users/getWRecords?email=user1@mail.com
```
(if using fish escape the "?" with "\")

**findUserWithGroupsById**
```
curl -i -X GET http://localhost:8080/pa165/rest/users/getWGroups/4
```
**findUserWithGroupsByEmail**
```
curl -i -X GET http://localhost:8080/pa165/rest/users/getWGroups?email=user3@mail.com
```
(if using fish escape the "?" with "\")

**getStatistics**
```
curl -i -X GET http://localhost:8080/pa165/rest/users/getStats?id=1
```
(if using fish escape the "?" with "\")
