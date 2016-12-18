
angular.module('mainApp').controller('DetailGroupController',DetailGroupController);


DetailGroupController.$inject = ['$stateParams','$rootScope','$scope','SelectedUsersService','GroupService','Session'];

function DetailGroupController($stateParams,$rootScope,$scope,SelectedUsersService, GroupService,Session){
    console.log("detail open");
    $scope.name = undefined;
    $scope.users = undefined;
    $scope.dismiss = function(){
        $scope.$dismiss();
        //SelectedUsersService.flush();
    };
    $scope.loadGroupDetail = function(){
        console.log("id groupy ktoru idem editovat",$stateParams.groupId);
        GroupService.findById($stateParams.groupId).then(function(data) {
            console.log("This rec",data);
            $scope.name = data.data.name;
            $scope.users = data.data.users;
            //SelectedUsersService.setSelected($scope.users);
        })
    };
    $scope.loadGroupDetail();
}