
angular.module('mainApp').controller('CreateGroupController',CreateGroupController);


CreateGroupController.$inject = ['$rootScope','$scope','SelectedUsersService','GroupService','Session'];

function CreateGroupController($rootScope,$scope,SelectedUsersService,GroupService,Session){
    $scope.name = undefined;
    $scope.users = undefined;
    $scope.dismiss = function(){
        $scope.$dismiss();
    };
    $scope.selectUsers = function(){
        $scope.users = SelectedUsersService.getSelected();
    };
    $scope.createGroup = function(){
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
    }
}
