/**
 * Created by spriadka on 12/17/16.
 */


angular.module('patternfly.validation').controller('CreateRecordCaloriesValidationController',CreateRecordCaloriesValidationController);

CreateRecordCaloriesValidationController.$inject = ['$scope','CreateRecordService'];

function CreateRecordCaloriesValidationController($scope,CreateRecordService){
    $scope.calories = 0;
    $scope.validateCalories = function(input){
        return input > 0;
    };
    $scope.changeCalories = function(){
      CreateRecordService.setCalories($scope.calories);
    }
}