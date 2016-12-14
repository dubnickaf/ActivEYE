angular.module('patternfly.validation').controller('LoginValidationController',function($scope,$location,UserService){
    var vm = this;
    vm.nauDto = {name: undefined, password: undefined};
    $scope.myValue = "Please enter a valid email address";
    $scope.validEmail = function(input){
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(input);
    }
    $scope.authenticate = function ()
    {
        $location.path('/dashboard');
        /*
        TODO :)

        UserService.authenticateUser(angular.toJson(vm.nauDto)).then(function (response){

            console.log(response);
        });*/



    };
})