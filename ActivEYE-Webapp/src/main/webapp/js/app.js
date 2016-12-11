/**
 * Created by spriadka on 12/11/16.
 */
   var app = angular.module('mainApp',['ui.router','ui.bootstrap','patternfly.validation']);
    app.config(function($stateProvider,$urlRouterProvider){
        $stateProvider.state({
            name: 'login',
            url: '/login',
            controller: 'DemoController',
            templateUrl: 'pages/login.html'
        });
        $urlRouterProvider.when('','/login');
    });
