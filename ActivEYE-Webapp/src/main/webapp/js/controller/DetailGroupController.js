
angular.module('mainApp').controller('DetailGroupController',DetailGroupController);


DetailGroupController.$inject = ['$rootScope','$scope','GroupService','Session'];

function DetailGroupController($rootScope,$scope,GroupService,Session){
    console.log("detail open");
}