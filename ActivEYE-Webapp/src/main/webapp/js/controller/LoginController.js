/**
 * Created by spriadka on 12/11/16.
 */
angular.module('mainApp').controller('LoginController',LoginController);

LoginController.$inject = ['$rootScope','UserService','$scope','mediator','Session','Router'];

function LoginController($rootScope,UserService,$scope,mediator,Session,Router){
    console.log('registered');
    $scope.valid = false;
    mediator.listen('user:login:validate').act(function(event,data){
        ///call then when the data is retreived with server response as parameter res
        UserService.login(data).then(function(res){
            $scope.valid = res.data;
            console.log($scope.valid);
            if (!$scope.valid){
                $rootScope.$broadcast('user:login:invalid');
            }
            else {
                UserService.findByEmail(data.email).then(function(user){
                    console.log(user.data);
                    Session.saveUser(user.data);
                    Router.redirect('/home/dashboard');
                },function(err){
                    console.log(err);
                });
            }
        })
    });
}