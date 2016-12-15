
/**
 * Created by spriadka on 12/14/16.
 */

angular.module('activeye.services',[]);

angular.module('activeye.services').config(config);

function config($locationProvider){
    console.log('configuring services');
    $locationProvider.hashPrefix('!');
}

angular.module('activeye.services').run(initServices);

initServices.$inject = ['Router','Session','UserService'];

function initServices(Router,Session,UserService){
    console.log('initializing services');
    Router.initialize();
    Session.initialize();
    UserService.initialize();
}