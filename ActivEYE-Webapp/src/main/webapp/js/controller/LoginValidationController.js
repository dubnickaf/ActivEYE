angular.module('patternfly.validation').controller('LoginValidationController',LoginValidationController);

LoginValidationController.$inject = ['$scope','$rootScope','mediator'];

function LoginValidationController($scope,$rootScope,mediator){
    console.log('registered');
    var vm = this;
    $scope.nauDto = {email: '', password: ''};
    $scope.myValue = "Please enter a valid email address";
    $scope.validEmail = function(input){
        if ($scope.helpMessage == $scope.loginFailed){
            $scope.helpMessage = $scope.helpEmail;
        }
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(input);
    };
    $scope.validPassword = function(input){
        console.log(input);
        return input.length > 0;
    };
    $scope.authenticate = function(){
        if ($scope.validEmail($scope.nauDto.email)) {
            $rootScope.$broadcast('user:login:validate', $scope.nauDto);
        }
    };
    mediator.listen('user:login:invalid').act(function(event){
        console.log('called');
        $('#login-help-block').removeClass('ng-hide');
        $scope.helpMessage = $scope.loginFailed;
    });
    $scope.helpEmail = "Please provide valid email address";
    $scope.loginFailed = "Login failed, invalid username or password";
    $scope.helpMessage = $scope.helpEmail;
};