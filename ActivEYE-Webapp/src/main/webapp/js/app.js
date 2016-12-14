/**
 * Created by spriadka on 12/11/16.
 */
   var app = angular.module('mainApp',['ui.router','ui.bootstrap','patternfly.validation']);
    app.config(function($stateProvider,$urlRouterProvider){
        $stateProvider.state({
            name: 'login',
            url: '/login',
            controller: 'LoginValidationController',
            templateUrl: 'pages/login.html'
        });
        $urlRouterProvider.when('','/login');
    });
    app.config(function($stateProvider,$urlRouterProvider){
        $stateProvider.state({
            name: 'dashboard',
            url: '/dashboard',
            controller: 'DashboardController as dashboardController',
            templateUrl: 'pages/dashboard.html'
        });
        $urlRouterProvider.when('','/dashboard');
    });
    app.config(function($stateProvider,$urlRouterProvider){
        $stateProvider.state({
            name: 'updateUser',
            url: '/updateUser',
            controller: 'UpdateUserController as updateUserController',
            templateUrl: 'pages/update_profile.html'
        });
        $urlRouterProvider.when('','/updateUser');
    });
