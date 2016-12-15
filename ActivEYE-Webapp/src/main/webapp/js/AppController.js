angular.module('patternfly.validation').controller('AppController',AppController);

AppController.$inject = ['$scope','$rootScope','$state'];

function AppController($scope,$rootScope,$state) {

        var statename;
        this.showHeader = function ($state) {
            return true;


            $rootScope.$on('$stateChangeSuccess',
                function(event, toState, toParams, fromState, fromParams) {
                    statename = toState.current;
                    console.log(statename);  });

            console.log("xx",$state.current.name);
            return $state.current.name != 'login'; // && $rootScope.isAuthenticated() todo filip
        };
}