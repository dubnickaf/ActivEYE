# ActivEYE - Web Application

## Installation

1. Navigate to the **ActivEYE-Webapp** directory
2. Execute command `mvn clean install` or `mvn clean package` to build application
3. Within the same directory execute `mvn tomcat7:run` to deploy the application to the web-server
3. Visit [http://localhost:8080/pa165](http://localhost:8080/pa165)

## Usage
Login to the application using these credentials:

Admin User:
email: "admin@mail.com"      password: "admin"

Basic Users:
email: "user0@mail.com"      password: "user0"

email: "user1@mail.com"      password: "user1"

email: "user2@mail.com"      password: "user2"

email: "user3@mail.com"      password: "user3"

email: "user4@mail.com"      password: "user4"

email: "user5@mail.com"      password: "user5"

email: "user6@mail.com"      password: "user6"

email: "user7@mail.com"      password: "user7"

email: "user8@mail.com"      password: "user8"

email: "user9@mail.com"      password: "user9"

##REST Usage

###Users

`curl -X GET http://localhost:8080/pa165/rest/users/all` To get all users

`curl -X GET http://localhost:8080/pa165/rest/users/get/{userId}` To get user by specific ID

`curl -X GET http://localhost:8080/pa165/rest/users/get?email={userEmail}` To get user by specific email

`curl -X POST -H "Content-type: application/json" -d '{"email":"<userEmail>","password":"<userPassword>"}' http://localhost:8080/pa165/rest/users/login` To login as user with userEmail and userPassword

`curl -X POST -H "Content-type: application/json" -d '{"name":"<userName>","emailAddress":"<userEmailAddress>","bornDate":"<userBirthDate>","gender","<userGender>","role":"<userRole>"}' http://localhost:8080/pa165/rest/users/register?password="<userPassword>"` to register with user info and credentials

`curl -X PUT -H "Content-type: application/json" -d '{"id":"<userId>","name":"<userName>","emailAddress":"<userEmailAddress>","bornDate":"<userBirthDate>","gender","<userGender>","role":"<userRole>"}' http://localhost:8080/pa165/rest/users/update` to update user

`curl -X GET http://localhost:8080/pa165/rest/users/getStats?id=<userId>` to get statistics for user with given ID

`curl -X GET http://localhost:8080/pa165/rest/users/getWRecords?email=<userEmail>` to get User with his activity records

`curl -X GET http://localhost:8080/pa165/rest/users/getWRecords/{userId}` to get user with records with specific userId

`curl -X GET http://localhost:8080/pa165/rest/users/getWGroups/{userId}` to get user and his groups with his userId

`curl -X GET http://localhost:8080/pa165/rest/users/getWGroups?email=<userEmail>` to get user and his groups with specific email

`curl -X DELETE -H "Content-type: application/json" '{"id":"<userId>","name":"<userName>","emailAddress":"<userEmailAddress>","bornDate":"<userBirthDate>","gender","<userGender>","role":"<userRole>"}' http://localhost:8080/pa165/rest/users/delete` to delete user

###Records

`curl -X POST -H "Content-type: application/json" -d '{"activity":"<userActivity>","user":"<user>","burnedCalories":"<burnedCalories>","startDate":"<startDate>","endDate":"<endDate>"}' http://localhost:8080/pa165/rest/records/create`to create activity record 

`curl -X GET http://localhost:8080/pa165/rest/records/get/{recordId}` to get record with given id

`curl -X GET http://localhost:8080/pa165/rest/records/get/all` to get all records

`curl -X DELETE http://localhost:8080/pa165/rest/records/delete/{recordId}` to delete record with given id

`curl -X PUT -H "Content-type: application/json" -d '{"id":"<recordId>","activity":"<userActivity>","user":"<user>","burnedCalories":"<burnedCalories>","startDate":"<startDate>","endDate":"<endDate>"}' http://localhost:8080/pa165/rest/users/update` to update record with new info
