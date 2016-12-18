angular.module('mainApp').controller('HomeController',HomeController);

HomeController.$inject = ['$rootScope','UserService','$scope','mediator','Session','Router'];

function HomeController($rootScope,UserService,$scope,mediator,Session,Router){
    $scope.user = undefined;
    $scope.redirectIfLoggedOut = function() {
        if(Session.isAnyoneLoggedIn() === false) {
            Router.redirect('/login');
        }
    };
    mediator.listen('user:updated').act(function(){
        $scope.loadData();
    });
    $scope.redirectIfLoggedOut();
    $scope.logOutConsequences = function() {
        Session.saveUser(undefined);
    };
    $scope.loadData = function(){
        $scope.user = Session.getUser();
    };
    $scope.loadData();
}