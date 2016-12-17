
angular.module('mainApp').controller('CreateRecordController',CreateRecordController);


CreateRecordController.$inject = ['$scope','$rootScope','RecordService','CreateRecordService','Session'];

function CreateRecordController($scope,$rootScope,RecordService,CreateRecordService,Session){
    $scope.back = function(){
        $scope.$close(true);
    };
    $scope.createRecord = function(){
        var record = {};
        record.user = Session.getUser();
        record.activity = CreateRecordService.getActivity();
        record.startDate = CreateRecordService.getStartTime();
        record.endDate = CreateRecordService.getEndTime();
        record.burnedCalories = CreateRecordService.getCalories();
        RecordService.create(record).then(function(){
            console.log($scope);
            $rootScope.$broadcast('record:created');
            console.log('emmmited');
            $scope.$close(true);
        });
    }
}
