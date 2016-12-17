/**
 * Created by spriadka on 12/16/16.
 */


angular.module('patternfly.select').controller('CreateRecordActivityController',CreateRecordActivityController);

CreateRecordActivityController.$inject = ['$scope','ActivityService','CreateRecordService'];

function CreateRecordActivityController($scope,ActivityService,CreateRecordService){
    $scope.activities = undefined;
    $scope.activity = undefined;
    $scope.init = function(){
        ActivityService.getAll().then(function(result){
            $scope.activities = result.data;
        });
    };
    $scope.changeActivity = function(){
        CreateRecordService.setActivity($scope.activity);
    };
    $scope.init();
}