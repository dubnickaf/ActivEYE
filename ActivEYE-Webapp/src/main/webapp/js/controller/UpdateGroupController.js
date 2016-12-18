angular.module('mainApp').controller('UpdateGroupController',UpdateGroupController);

UpdateGroupController.$inject = ['$stateParams','$rootScope','$scope','SelectedUsersService','GroupService','Session'];

function UpdateGroupController($stateParams,$rootScope,$scope,SelectedUsersService,GroupService,Session){

    $scope.createVsUpdate =  'UPDATE';
    $scope.name = undefined;
    $scope.users = undefined;
    $scope.dismiss = function(){
        $scope.$close(true);
    };
    $scope.loadGroupDetail = function(){
        console.log("id groupy ktoru idem editovat",$stateParams.groupId);
        GroupService.findById($stateParams.groupId).then(function(data) {
            console.log("This rec",data);
            $scope.name = data.data.name;
            $scope.users = data.data.users;
            SelectedUsersService.setSelected($scope.users);
        })

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
            $scope.$close(true);
        });
    };
    $scope.loadGroupDetail();
}