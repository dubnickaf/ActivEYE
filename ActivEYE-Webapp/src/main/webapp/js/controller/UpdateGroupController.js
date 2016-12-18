angular.module('mainApp').controller('UpdateGroupController',UpdateGroupController);

UpdateGroupController.$inject = ['$rootScope','$scope','SelectedUsersService','GroupService','Session'];

function UpdateGroupController($rootScope,$scope,SelectedUsersService,GroupService,Session){
    $scope.name = undefined;
    $scope.users = undefined;
    $scope.dismiss = function(){
        $scope.$dismiss();
    };
    $scope.loadGroupDetail = function(){
        $scope.name = undefined;
        $scope.users = undefined;
    };
    $scope.selectUsers = function(){
        $scope.users = SelectedUsersService.getSelected();
    };
    $scope.updateGroup = function(){
        $scope.selectUsers();
        var toCreate = {};
        toCreate.creatorsUserId = Session.getUser().id;
        toCreate.users = $scope.users;
        toCreate.name = $scope.name;
        GroupService.create(toCreate).then(function(){
            SelectedUsersService.flush();
            $rootScope.$broadcast('group:created');
            $scope.dismiss();
        });
    };
    $scope.loadGroupDetail();
}