/**
 * @author Filip Dubnička <445647>
 */
angular.module('activeye.services').factory('UserService', UserService);

UserService.$inject = ['$http'];

function UserService($http) {
    var vm = this;

    vm.login = function (user){
        return $http.post('/pa165/rest/users/login',user);
    };
    vm.update = function (user){
        return $http.put('/pa165/rest/users/update',user);
    };
    vm.delete = function (user){
        return $http.delete('/pa165/rest/users/delete',user);
    };
    vm.findById = function (id){
        return $http.get('/pa165/rest/users/get'+groupId);
    };
    vm.findByEmail = function (email){
        return $http.get('/pa165/rest/users/get',{
            params: {email: email}
        });
    };
    vm.findWithRecordsById = function (id){
        return $http.get('/pa165/rest/users/getWRecords'+groupId);
    };
    vm.findWithRecordsByEmail = function (email){
        return $http.get('/pa165/rest/users/getWRecords',{
            params: {email: email}
        });
    };
    vm.findWithGroupsById = function (id){
        return $http.get('/pa165/rest/users/getWGroups'+groupId);
    };
    vm.findWithGroupsByEmail = function (email){
        return $http.get('/pa165/rest/users/getWGroups',{
            params: {email: email}
        });
    };
    vm.getAll = function () {
        return $http.get('/pa165/rest/users/all');
    };
    vm.getStatistics = function (id) {
        return $http.get('/pa165/rest/users/getStats',{
            params: {id: id}
        });
    };
    vm.initialize = function(){
        console.log('registered');
    };
    return vm;
}
