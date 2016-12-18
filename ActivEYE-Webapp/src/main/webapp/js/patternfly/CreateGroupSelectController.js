/**
 * Created by spriadka on 12/16/16.
 */

angular.module('patternfly.select').controller('CreateGroupSelectController',CreateGroupSelectController);

CreateGroupSelectController.$inject = ['$scope','UserService'];

function CreateGroupSelectController($scope,UserService){
    $scope.allUsers = undefined;
    $scope.multipleSelect = [];
    $scope.init = function() {
        UserService.getAll().then(function(res){
            $scope.allUsers = res.data;
            console.log("$scope.multipleSelect:");
            console.log($scope.multipleSelect);
        })
    };
    $scope.init();
}