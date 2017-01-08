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
            controller: 'HomeController',
            templateUrl: 'pages/home.html'
        })
        .state('home.records', {
            url: '/records',
            controller: 'RecordsController',
            templateUrl: 'pages/records.html'
        })
        .state('home.groups', {
            url: '/groups',
            controller: 'GroupsController',
            templateUrl: 'pages/groups.html'
        })
        .state('home.dashboard', {
            url: '/dashboard',
            controller: 'DashboardController',
            templateUrl: 'pages/dashboard.html'
        })
        .state({///not used in app
            name: 'activities',
            url: '/activities',
            controller: 'ActivitiesController',
            templateUrl: 'pages/activities.html'

        })
        .state('home.dashboard.update_profile', {
            onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                $uibModal.open({
                    controller: 'UpdateProfileController',
                    templateUrl: 'pages/update_profile.html'
                }).result.finally(function () {
                    $state.go('^');
                })
            }]
        })
        .state('home.records.create_record', {
            onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'pages/form_record.html',
                    controller: 'CreateRecordController'
                }).result.finally(function () {
                    $state.go('^');
                });
            }]
        })
        .state('home.records.update_record', {
            url: '/:touchedRecordId',
            params: ['touchedRecordId'],
            onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'pages/form_record.html',
                    controller: 'UpdateRecordController'
                }).result.finally(function () {
                    $state.go('^');
                });
            }]
        })
        .state('home.groups.create_group', {
            onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'pages/form_group.html',
                    controller: 'CreateGroupController'
                }).result.finally(function () {
                    $state.go('^');
                });
            }]
        })
        .state('home.groups.update_group', {
            url: '/update/:groupId',
            params: ['groupId'],
            onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'pages/form_group.html',
                    controller: 'UpdateGroupController'
                }).result.finally(function () {
                    $state.go('^');
                });
            }]
        })
        .state('home.groups.detail_group', {
            url: '/show/:groupId',
            params: ['groupId'],
            onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'pages/detail_group.html',
                    controller: 'DetailGroupController'
                }).result.finally(function () {
                    $state.go('^');
                });
            }]
        });
}
