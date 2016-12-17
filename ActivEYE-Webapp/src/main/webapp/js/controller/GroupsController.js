
angular.module('mainApp').controller('GroupsController',GroupsController);

GroupsController.$inject = ['$rootScope','GroupService','UserService','$scope','mediator','Session','Router'];

function GroupsController($rootScope, GroupService, UserService, $scope, mediator, Session, Router) {
    $scope.allGroups;
    $scope.usersGroups;
    $scope.loadUsersGroups = function() {
        UserService.findWithGroupsByEmail(Session.getUser().emailAddress).then(function(data) {
            $scope.usersGroups = data.data.groups;
        });
    };
    $scope.loadAllGroups = function() {
        GroupService.findAll().then(function(data) {
            $scope.allGroups = data.data;
        });
    };
    $scope.loadUsersGroups();
    $scope.loadAllGroups();
}