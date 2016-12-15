/**
 * authenticates user
 * @author dubnickaf@gmail.com
 */

angular.module('activeye.services').factory('UserService', UserService);

UserService.$inject = ['$http'];

function UserService($http) {
    var vm = this;
    vm.getAll = function () {
        return $http.get('/pa165/rest/users/all')
    };
    vm.login = function (user){
        return $http.post('/pa165/rest/users/login',user);
    };
    vm.findByEmail = function (email){
        return $http.get('/pa165/rest/users/get',{
            params: {email: email}
        });
    };
    vm.initialize = function(){
        console.log('registered');
    };
    return vm;
}
