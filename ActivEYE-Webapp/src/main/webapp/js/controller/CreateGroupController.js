angular.module('mainApp').controller('CreateGroupController',CreateGroupController);

CreateGroupController.$inject = ['$rootScope','$scope','GroupService','Session'];

function CreateGroupController($rootScope,$scope,GroupService,Session){

    $scope.createVsUpdate =  'CREATE';
    $scope.users = [];
    $scope.dismiss = function(){
        $scope.$dismiss();
    };
    $scope.createGroup = function(){
        var toCreate = {};
        toCreate.creatorsUserId = Session.getUser().id;
        Session.globalData.multipleSelect.push(Session.getUser());
        toCreate.users = Session.globalData.multipleSelect;
        toCreate.name = "CreateGroupController.js::17"; //$scope.name;
        //console.log("createController.ToCreate: ", toCreate);
        GroupService.create(toCreate).then(function(){
            $rootScope.$broadcast('group:created');
            $scope.dismiss();
        });
    }
}
