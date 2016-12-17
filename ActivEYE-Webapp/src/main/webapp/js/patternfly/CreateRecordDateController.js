/**
 * Created by spriadka on 12/16/16.
 */
angular.module('patternfly.form').controller('CreateRecordDateController',CreateRecordDateController);

CreateRecordDateController.$inject = ['$scope','$locale','CreateRecordService'];

function CreateRecordDateController($scope,$locale,CreateRecordService){
    $scope.startTime = moment();
    $scope.endTime = moment();
    $scope.options = {
        format: $locale.DATETIME_FORMATS.medium
    };
    $scope.changeStartTime = function(){
        CreateRecordService.setStartTime($scope.startTime);
    };
    $scope.changeEndTime = function(){
        CreateRecordService.setEndTime($scope.endTime);
    };
    $scope.init = function(){
        CreateRecordService.setStartTime($scope.startTime);
        CreateRecordService.setEndTime($scope.endTime);
    };
    $scope.init();
}