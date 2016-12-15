angular.module('patternfly.validation').controller('AppController',AppController);

AppController.$inject = ['$scope','$rootScope','$state','$location'];

function AppController($scope,$rootScope,$state,$location) {
        console.debug($location.path() != "/login");
        this.showHeader = true;//$location.path() != "/login";
}