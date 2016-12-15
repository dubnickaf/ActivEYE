/**
 * @author Branislav Bajuzik; 442772
 */

angular.module('activeye.services').factory('GroupService', GroupService);

GroupService.$inject = ['$http'];

function GroupService($http) {
    var vm = this;
    vm.create = function () {
        return $http.get('/pa165/rest/groups/create')
    };
    vm.update = function () {
        return $http.put('/pa165/rest/groups/update')
    };
    vm.delete = function () {
        return $http.delete('/pa165/rest/groups/delete/:groupId')
    };
    vm.findById = function () {
        return $http.get('/pa165/rest/groups/get/:groupId')
    };
    vm.findAll = function () {
        return $http.get('/pa165/rest/groups/get/all')
    };
    vm.findAllUsers = function () {
        return $http.get('/pa165/rest/groups/get/all/:groupId')
    };
    vm.addUser = function () {
        return $http.put('/pa165/rest/groups/update/:groupId')
    };
    return vm;
}