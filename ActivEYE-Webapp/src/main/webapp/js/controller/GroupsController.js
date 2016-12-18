
angular.module('mainApp').controller('GroupsController',GroupsController);

GroupsController.$inject = ['$rootScope','GroupService','UserService','$scope','mediator','Session','Router'];

function GroupsController($rootScope, GroupService, UserService, $scope, mediator, Session, Router) {
    $scope.allGroups = undefined;
    $scope.usersGroups = undefined;
    $scope.loadUsersGroups = function() {
        UserService.findWithGroupsByEmail(Session.getUser().emailAddress).then(function(data) {
            console.log(data);
            $scope.usersGroups = data.data.groups;
        });
    };
    $scope.loadAllGroups = function() {
        GroupService.findAll().then(function(data) {
            console.log(data);
            $scope.allGroups = data.data;
        });
    };
    mediator.listen('group:created').act(function(){
        $scope.loadUsersGroups();
        $scope.loadAllGroups();
    });
    $scope.loadUsersGroups();
    $scope.loadAllGroups();
}