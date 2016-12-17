
angular.module('mainApp').controller('GroupsController',GroupsController);

GroupsController.$inject = ['$rootScope','GroupService','$scope','mediator','Session','Router'];

function GroupsController($rootScope, GroupService, $scope, mediator, Session, Router) {
    console.log("aaaaa");
    $scope.loadData2 = function() {
        console.log(Session);
        GroupService.findAll().then(function(data) {
            console.log(data);
            $scope.groupsDto = data.data;
        });
        console.log($scope.groupsDto);
    };
    $scope.loadData2();
}