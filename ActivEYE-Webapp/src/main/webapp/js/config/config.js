/**
 * Created by spriadka on 12/15/16.
 */

angular.module('mainApp').config(config);

config.$inject = ['$stateProvider', '$urlRouterProvider'];

function config($stateProvider, $urlRouterProvider) {
    $stateProvider.state({
        name: 'root',
        url: '/',
        redirectTo: 'login'
    })
        .state({
            name: 'login',
            url: '/login',
            controller: 'DemoController',
            templateUrl: 'pages/login.html'
        });}