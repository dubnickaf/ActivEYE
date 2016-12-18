
angular.module('mainApp').controller('DashboardController',DashboardController);

DashboardController.$inject = ['UserService','$scope','Session','mediator'];

function DashboardController(UserService,$scope,Session,mediator){
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
    $scope.loadData = function () {
        UserService.getStatistics(Session.getUser().id).then(function(data){
            $scope.statsDto = data.data;
        });
    };
    $scope.loadData();
}