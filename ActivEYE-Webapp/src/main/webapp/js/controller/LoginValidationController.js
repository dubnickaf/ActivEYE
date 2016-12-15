angular.module('patternfly.validation').controller('LoginValidationController',LoginValidationController);

LoginValidationController.$inject = ['$scope','$rootScope'];

function LoginValidationController($scope,$rootScope){
    var vm = this;
    $scope.nauDto = {email: undefined, password: undefined};
    $scope.myValue = "Please enter a valid email address";
    $scope.validEmail = function(input){
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(input);
    };
    $scope.authenticate = function(){
        $rootScope.$broadcast('user:login:validation',$scope.nauDto);
    };
};