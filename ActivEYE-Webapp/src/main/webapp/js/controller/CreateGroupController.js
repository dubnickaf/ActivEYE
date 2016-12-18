
angular.module('mainApp').controller('CreateGroupController',CreateGroupController);


CreateGroupController.$inject = ['$rootScope','$scope','SelectedUsersService','GroupService','Session'];

function CreateGroupController($rootScope,$scope,SelectedUsersService,GroupService,Session){

    $scope.createVsUpdate =  'CREATE';
    $scope.name = undefined;
    $scope.users = [];
    $scope.dismiss = function(){
        $scope.$dismiss();
    };
    $scope.selectUsers = function(){
        $scope.users = SelectedUsersService.getSelected();
        console.log("users:");
        console.log($scope.users);
    };
    $scope.createGroup = function(){
        var toCreate = {};
        toCreate.creatorsUserId = Session.getUser().id;
        Session.globalData.multipleSelect.push(Session.getUser());
        console.log("Session.globalData.multipleSelect:", Session.globalData.multipleSelect);
        toCreate.users = Session.globalData.multipleSelect;
        toCreate.name = $scope.name;
        GroupService.create(toCreate).then(function(){
            SelectedUsersService.flush();
            $rootScope.$broadcast('group:created');
            $scope.dismiss();
        });
    }
}
