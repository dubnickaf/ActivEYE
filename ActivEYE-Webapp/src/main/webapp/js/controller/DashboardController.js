
angular.module('mainApp').controller('DashboardController',DashboardController);

DashboardController.$inject = ['$rootScope','UserService','$scope','mediator','Session','Router'];

function DashboardController($rootScope,Session, UserService){
    var vm=this;
    vm.statsDto = {
        userDto 			                : undefined,
        totalCaloriesBurned			        : undefined,
        caloriesBurnedToday                 : undefined,
        averageBurnedCaloriesPerRecord      : undefined,
        totalRecords                        : undefined,
        recordsToday                        : undefined,
        mostUsedActivity                    : undefined
    };
    vm.loadData = loadData();
    function loadData() {
        console.log(Session);
        vm.statsDto = UserService.getStatistics(UserService.findByEmail(Session.user.email));
        console.log(vm.statsDto);
    }

    loadData();
    return vm;
}