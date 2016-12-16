angular.module('mainApp').controller('HomeController',HomeController);

function HomeController($rootScope,UserService,$scope,mediator,Session,Router){
    $scope.userEmailAddress = Session.getUser().emailAddress;
    $scope.userName = Session.getUser().name;

}