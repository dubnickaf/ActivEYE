
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
        console.log("users:");
        console.log($scope.users);
    };
    $scope.createGroup = function(){
        $scope.selectUsers();
        var toCreate = {};
        toCreate.creatorsUserId = Session.getUser().id;
        $scope.users.push(Session.getUser());
        toCreate.users = $scope.users;
        toCreate.name = $scope.name;
        GroupService.create(toCreate).then(function(){
            SelectedUsersService.flush();
            $rootScope.$broadcast('group:created');
            $scope.dismiss();
        });
    }
}
