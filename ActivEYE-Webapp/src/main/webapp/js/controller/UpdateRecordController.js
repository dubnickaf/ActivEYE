
angular.module('mainApp').controller('UpdateRecordController',UpdateRecordController);


UpdateRecordController.$inject = ['$stateParams','$scope','$rootScope','RecordService','CreateRecordService','Session'];

function UpdateRecordController($stateParams,$scope,$rootScope,RecordService,CreateRecordService,Session){

    $scope.createVsUpdate =  'UPDATE';
    console.log("This id",$stateParams.touchedRecordId);
    //$scope.user = Session.getUser();
    $scope.activity = undefined;
    $scope.startDate = undefined;
    $scope.endDate = undefined;
    $scope.dismiss = function(){
        $scope.$dismiss();
    };
    $scope.updateRecord = function() {
        record = {};
        record.user = Session.getUser();
        record.activity = undefined;
        record.startDate = undefined;
        record.endDate = undefined;
        RecordService.update(record).then(function() {
            console.log("UPDATED");
            $scope.dismiss();
        })
    };
    $scope.loadRecordToUpdate = function() {
        RecordService.findById($stateParams.touchedRecordId).then(function(data) {
            console.log("This rec",data);
            $scope.activity = data.data.activity;
            $scope.startDate = data.data.startDate;
            $scope.endDate = data.data.endDate;
        })
    };
    $scope.loadRecordToUpdate();

}