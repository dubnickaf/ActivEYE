
angular.module('mainApp').controller('GroupsController',GroupsController);

GroupsController.$inject = ['$state','$rootScope','GroupService','UserService','$scope','mediator','Session','Router'];

function GroupsController($state, $rootScope, GroupService, UserService, $scope, mediator, Session, Router) {
    $scope.allGroups = undefined;
    $scope.usersGroups = undefined;
    $scope.userRole = Session.getUser().role;
    $scope.editGroup = function (record){

    };
    $scope.showDetail = function (group){
        console.log("showdetail",group);
        $state.go('home.groups.detail_group');
    };
    $scope.deleteGroup = function (group){
        console.log(group);
        GroupService.delete(group.id).then(function(){
            $scope.loadUsersGroups();
            $scope.loadAllGroups();
            Router.redirect('home/dashboard/groups');
        });
    };
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