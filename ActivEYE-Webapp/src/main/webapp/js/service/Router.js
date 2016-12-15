/**
 * Created by spriadka on 12/15/16.
 */
angular.module('activeye.services').factory('Router',Router);

Router.$inject = ['$location','mediator'];

function Router($location,mediator){
    console.log('registered');
    mediator.listen('router:dashboard').act(function(event,data){
        $location.path('/dashboard');
    })
    mediator.listen('router:groups').act(function(event,data){
        $location.path('/groups');
    })
    mediator.listen('router:records').act(function(event,data){
        $location.path('/records');
    })
    mediator.listen('router:activities').act(function(event,data){
        $location.path('/activities');
    })
}