angular.module('patternfly.validation').controller('LoginValidationController',function($scope,$location,UserService){
    var vm = this;
    $scope.nauDto = {name: undefined, password: undefined};
    $scope.myValue = "Please enter a valid email address";
    $scope.validEmail = function(input){
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(input);
    }
    $scope.authenticate = function ()
    {
        console.log($scope.nauDto);
        $location.path("/dashboard");
        /*
        To be resolved:
        UserService.authenticateUser(angular.toJson($scope.nauDto)).then(function (response){

            console.log(response);
        });
        */


    };
})