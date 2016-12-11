/**
 * Created by spriadka on 12/11/16.
 */
angular.module('patternfly.validation').controller('LoginValidationController',function($scope){
    $scope.myValue = "Please enter a valid email address";
    $scope.validEmail = function(input){
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(input);
    }
});