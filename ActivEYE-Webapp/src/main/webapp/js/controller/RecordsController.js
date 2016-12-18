angular.module('mainApp').controller('RecordsController',RecordsController);

RecordsController.$inject = ['RecordService','Session','UserService','$scope','mediator'];

function RecordsController(RecordService,Session, UserService, $scope,mediator){

    $scope.userWithRecords = undefined;
    $scope.editRecord = function (record){

    };
    $scope.deleteRecord = function (record){
            console.log(record);
            RecordService.delete(record.id).then(function(){
                console.log($scope.userWithRecords);
                $scope.loadData();
                Router.redirect('home/dashboard/records');
            });
    };
    $scope.loadData = function(){
        console.log($scope);
        console.log("usertouse",Session.getUser());
        UserService.findWithRecordsByEmail(Session.getUser().emailAddress).then(function(data){
            $scope.userWithRecords = data.data;
            console.log($scope.userWithRecords);
        });
        console.log($scope.userWithRecords);
    };
    mediator.listen('record:created').act(function(){
        $scope.loadData();
    });
    $scope.loadData();

}