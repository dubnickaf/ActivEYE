/**
 * Created by spriadka on 12/15/16.
 */

angular.module('mainApp').config(config);

config.$inject = ['$stateProvider'];

function config($stateProvider, $urlRouterProvider,mediator) {
    console.log('configuring');
    $stateProvider.state({
        name: 'root',
        url: '',
        redirectTo: 'login'
    })
        .state({
            name: 'login',
            url: '/login',
            controller: 'LoginController',
            templateUrl: 'pages/login.html'
        })
        .state({
            name: 'dashboard',
            url: '/dashboard',
            controller: 'DashboardController',
            templateUrl: 'pages/dashboard.html'
        });}