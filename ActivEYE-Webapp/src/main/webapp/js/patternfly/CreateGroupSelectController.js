/**
 * Created by spriadka on 12/16/16.
 */

angular.module('patternfly.select').controller('CreateGroupSelectController',CreateGroupSelectController);

CreateGroupSelectController.$inject = ['$rootScope','$scope','UserService','Session'];

function CreateGroupSelectController($scope,UserService,Session){
    $scope.allUsers = undefined;
    $scope.data = {multipleSelect : []};
    Session.globalData = $scope.data;
    $scope.init = function() {
        UserService.getAll().then(function(res){
            $scope.allUsers = res.data;
        })
    };
    $scope.init();
}