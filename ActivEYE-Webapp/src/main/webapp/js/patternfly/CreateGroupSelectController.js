/**
 * Created by spriadka on 12/16/16.
 */

angular.module('patternfly.select').controller('CreateGroupSelectController',CreateGroupSelectController);

CreateGroupSelectController.$inject = ['$scope','UserService'];

function CreateGroupSelectController($scope,UserService){
    $scope.allUsers = undefined;
    $scope.init = function() {
        UserService.getAll().then(function(res){
            $scope.allUsers = res.data;
        })
    };
    $scope.selected = undefined;
    $scope.init();
}