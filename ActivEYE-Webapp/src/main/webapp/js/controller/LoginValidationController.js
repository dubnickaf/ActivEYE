angular.module('patternfly.validation').controller('LoginValidationController',['activeye.services',function($scope,UserService){
    var vm = this;
    $scope.nauDto = {name: undefined, password: undefined};
    $scope.myValue = "Please enter a valid email address";
    $scope.validEmail = function(input){
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(input);
    };
    $scope.authenticate = function(){
        UserService.query(function(res){
            console.log('called');
            console.log(res);
        });
    }
}]);