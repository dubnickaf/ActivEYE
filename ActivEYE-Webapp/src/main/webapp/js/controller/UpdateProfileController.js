
angular.module('mainApp').controller('UpdateProfileController',UpdateProfileController);


    UpdateProfileController.$inject = ['$scope','UserService','Session'];

    function UpdateProfileController($scope,UserService,Session){

        $scope.userToUpdate = Session.getUser();
        $scope.dismiss = function(){
            $scope.$dismiss();
        };
        $scope.updateUser = function(){
            UserService.update($scope.userToUpdate).then(function(){
                Session.saveUser($scope.userToUpdate);
                $scope.dismiss();
            });
        }
    }