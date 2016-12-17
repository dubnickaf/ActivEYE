
angular.module('mainApp').controller('DashboardController',DashboardController);

DashboardController.$inject = ['UserService','$scope','Session'];

function DashboardController(UserService,$scope,Session){
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
        UserService.getStatistics(Session.getUser().id).then(function(data){
            $scope.statsDto = data.data;
        });
    }
    loadData();
}