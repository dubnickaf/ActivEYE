
angular.module('mainApp').controller('CreateRecordController',CreateRecordController);


CreateRecordController.$inject = ['$scope','RecordService','CreateRecordService','Session','$state'];

function CreateRecordController($scope,RecordService,CreateRecordService,Session,$state){
    $scope.dismiss = function(){
        $scope.$dismiss();
    };
    $scope.createRecord = function(){
        var record = {};
        record.user = Session.getUser();
        record.activity = CreateRecordService.getActivity();
        record.startDate = CreateRecordService.getStartTime();
        record.endDate = CreateRecordService.getEndTime();
        record.burnedCalories = CreateRecordService.getCalories();
        RecordService.create(record).then(function(){
            $scope.dismiss();
        });
    }
}
