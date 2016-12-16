
angular.module('mainApp').controller('DashboardController',DashboardController);

DashboardController.$inject = ['$rootScope','UserService','$scope','mediator','Session','Router'];

function DashboardController($rootScope,UserService,$scope,mediator,Session,Router){
    console.log(Session.getUser());
    $scope.statsDto = {
        userDto 			                : undefined,
        totalCaloriesBurned			        : undefined,
        caloriesBurnedToday                 : undefined,
        averageBurnedCaloriesPerRecord      : undefined,
        totalRecords                        : undefined,
        recordsToday                        : undefined,
        mostUsedActivity                    : undefined,
        numberOfGroups                      : undefined
    };
    $scope.loadData = loadData();
    function loadData() {
        console.log(Session);
        UserService.getStatistics(Session.getUser().id).then(function(data){
            console.log(data);
            $scope.statsDto = data.data;
        });
        console.log($scope.statsDto);
    }
    loadData();
}