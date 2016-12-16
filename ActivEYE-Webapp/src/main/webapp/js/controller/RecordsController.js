angular.module('mainApp').controller('RecordsController',RecordsController);

RecordsController.$inject = ['RecordService','Session','UserService','$scope'];

function RecordsController(RecordService,Session, UserService, $scope){

    $scope.userWithRecords = undefined;
    $scope.deleteRecord = function(record){
            console.log("Deleting record ", record);
            RecordService.delete(record).then(function(){
                console.log("Deleting successful");
                Router.redirect('home/dashboard/records');
            });
    };
    $scope.loadData = loadData();
    function loadData() {
        console.log("usertouse",Session.getUser());
        UserService.findWithRecordsByEmail(Session.getUser().emailAddress).then(function(data){

            $scope.userWithRecords = data.data;
            console.log($scope.userWithRecords);
        });
        console.log($scope.userWithRecords);
    }
    loadData();

}