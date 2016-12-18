
angular.module('mainApp').controller('UpdateProfileController',UpdateProfileController);


    UpdateProfileController.$inject = ['$rootScope','$scope','UserService','Session'];

    function UpdateProfileController($rootScope,$scope,UserService,Session){
        $scope.id = undefined;
        $scope.name = undefined;
        $scope.email = undefined;
        $scope.bornDate = undefined;
        $scope.gender = undefined;
        $scope.role = undefined;
        $scope.loadCurrent = function(){
            $scope.id = Session.getUser().id;
            $scope.email = Session.getUser().emailAddress;
            $scope.name = Session.getUser().name;
            $scope.bornDate = Session.getUser().bornDate;
            $scope.gender = Session.getUser().gender;
            $scope.role = Session.getUser().role;
        };
        $scope.dismiss = function(){
            $scope.$dismiss();
        };
        $scope.updateUser = function(){
            var user = {};
            user.id = $scope.id;
            user.name = $scope.name;
            user.emailAddress = $scope.email;
            user.bornDate = $scope.bornDate;
            user.role = $scope.role;
            UserService.update(user).then(function(){
                Session.saveUser(user);
                $rootScope.$broadcast('user:updated');
                $scope.dismiss();
            });
        };
        $scope.loadCurrent();
    }