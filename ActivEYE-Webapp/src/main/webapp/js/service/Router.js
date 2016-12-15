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
}