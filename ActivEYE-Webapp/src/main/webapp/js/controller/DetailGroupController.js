
angular.module('mainApp').controller('DetailGroupController',DetailGroupController);


DetailGroupController.$inject = ['$stateParams','$rootScope','$scope','GroupService','Session'];

function DetailGroupController($stateParams,$rootScope,$scope,GroupService,Session){
    //console.log("detail open");
    $scope.name = undefined;
    $scope.users = undefined;
    $scope.dismiss = function(){
        $scope.$dismiss();
    };
    $scope.loadGroupDetail = function(){
        //console.log("id groupy ktoru idem editovat",$stateParams.groupId);
        GroupService.findById($stateParams.groupId).then(function(data) {
            //console.log("This rec",data);
            $scope.name = data.data.name;
            $scope.users = data.data.users;
        })
    };
    $scope.loadGroupDetail();
}