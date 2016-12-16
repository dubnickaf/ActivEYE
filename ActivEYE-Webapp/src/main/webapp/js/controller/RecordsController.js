angular.module('mainApp').controller('RecordsController',RecordsController);

RecordsController.$inject = ['RecordService'];

function RecordsController($rootScope, Session, UserService, $scope){

    $scope.userWithRecords = undefined;
    $scope.deleteRecord = deleteRecord();
    function deleteRecord(record){
            RecordService.delete(record).then(function(){
                console.log($scope.userWithRecords);
                Router.redirect('/dashboard/records');
            });
    }
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