angular.module('mainApp').controller('UpdateGroupController',UpdateGroupController);

UpdateGroupController.$inject = ['$stateParams','$rootScope','$scope','GroupService','Session'];

function UpdateGroupController($stateParams,$rootScope,$scope,GroupService,Session){

    $scope.createVsUpdate =  'UPDATE';
    $scope.new = undefined;
    $scope.dismiss = function(){
        $scope.$close(true);
    };
    $scope.loadGroupUpdate = function(){
        GroupService.findById($stateParams.groupId).then(function(data) {
            $scope.new = data.data;
        });
    };
    $scope.updateGroup = function(){
        var toUpdate = $scope.new;
        Session.globalData.multipleSelect.push(Session.getUser());
        toUpdate.users = Session.globalData.multipleSelect;
        //console.log("updateController.ToUpdate: ", toUpdate);
        GroupService.update(toUpdate).then(function(){
            $rootScope.$broadcast('group:created');
            $scope.$close(true);
        });
    };
    $scope.loadGroupUpdate();
}