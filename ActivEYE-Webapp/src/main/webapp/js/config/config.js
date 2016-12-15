/**
 * Created by spriadka on 12/15/16.
 */

angular.module('mainApp').config(config);

config.$inject = ['$stateProvider', '$urlRouterProvider'];

function config($stateProvider, $urlRouterProvider) {
    console.log('registered');
    $stateProvider.state({
        name: 'root',
        url: '',
        redirectTo: 'login'
    })
        .state('list',{
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
        })
        .state({
            name: 'records',
            url: '/records',
            controller: 'RecordsController',
            templateUrl: 'pages/records.html'
        })
        .state({
            name: 'groups',
            url: '/groups',
            controller: 'GroupsController',
            templateUrl: 'pages/groups.html'
        })
        .state({
            name: 'activities',
            url: '/activities',
            controller: 'ActivitiesController',
            templateUrl: 'pages/activities.html'
        });}