/**
 * Created by spriadka on 12/15/16.
 */
angular.module('activeye.services').factory('Session',Session);

Session.$inject = ['$scope','mediator','UserService'];

function Session($scope,mediator,UserService) {
    console.log('registered');
    $scope.user = undefined;
    mediator.listen('user:login:successful').act(function(event,data){
        UserService.findByEmail({email: data.email},function(result){
            $scope.user = result;
        })
    })
}