angular.module('mainApp').controller('RecordsController',RecordsController);


function RecordsController($rootScope, Session, UserService, $scope){
    $scope.userWithRecords = undefined;
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