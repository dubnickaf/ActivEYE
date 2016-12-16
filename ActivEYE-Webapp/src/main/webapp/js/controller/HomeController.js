angular.module('mainApp').controller('HomeController',HomeController);

function HomeController($rootScope,UserService,$scope,mediator,Session,Router){
    $scope.redirectIfLoggedOut = function() {
        if(Session.isAnyoneLoggedIn() === false) {
            Router.redirect('/login');
        }else{
            $scope.userEmailAddress = Session.getUser().emailAddress;
            $scope.userName = Session.getUser().name;
        }
    };
    $scope.redirectIfLoggedOut();
    $scope.logOutConsequences = function() {
        Session.saveUser(undefined);
    }
}