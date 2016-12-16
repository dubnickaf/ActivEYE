/**
 * Created by spriadka on 12/15/16.
 */

angular.module('mainApp').config(config);

config.$inject = ['$stateProvider'];

function config($stateProvider) {
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
            name: 'home',
            url: '/home',
            templateUrl: 'pages/home.html'
        })
        .state('home.records',{
            url: '/records',
            controller: 'RecordsController',
            templateUrl: 'pages/records.html'
        })
        .state('home.groups',{
            url: '/groups',
            controller: 'GroupsController',
            templateUrl: 'pages/groups.html'
        })
        .state('home.dashboard',{
            url: '/dashboard',
            controller: 'DashboardController',
            templateUrl: 'pages/dashboard.html'
        })
        .state({
            name: 'activities',
            url: '/activities',
            controller: 'ActivitiesController',
            templateUrl: 'pages/activities.html'

        })
        .state({
            name: 'update_profile',
            url: '/update_profile',
            controller: 'UpdateProfileController',
            templateUrl: 'pages/update_profile.html'
        })
        .state('home.records.create_record',{
            url: '/create_record',
            controller: 'CreateRecordController',
            templateUrl: 'pages/create_record.html'
        })
        .state('home.groups.create_group',{
            onEnter: function($modal) {
                $modal.open({
                    templateUrl: 'pages/create_group.html',
                    controller: 'CreateGroupController'
                })
            }
        });
}
