
angular.module('mainApp').controller('UpdateRecordController',UpdateRecordController);


UpdateRecordController.$inject = ['$scope','$rootScope','RecordService','CreateRecordService','Session'];

function UpdateRecordController($scope,$rootScope,RecordService,CreateRecordService,Session){}