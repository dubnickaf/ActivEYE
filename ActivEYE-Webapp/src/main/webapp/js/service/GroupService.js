/**
 * @author Branislav Bajuzik; 442772
 */

angular.module('activeye.services').factory('GroupService', GroupService);

GroupService.$inject = ['$http'];

function GroupService($http) {
    var vm = this;
    vm.create = function (group) {
        return $http.post('/pa165/rest/groups/create',group)
    };
    vm.update = function () {
        return $http.put('/pa165/rest/groups/update')
    };
    vm.delete = function (groupId) {
        return $http.delete('/pa165/rest/groups/delete/:groupId')
    };
    vm.findById = function (groupId) {
        return $http.get('/pa165/rest/groups/get/:groupId')
    };
    vm.findAll = function () {
        return $http.get('/pa165/rest/groups/get/all')
    };
    vm.findAllUsers = function (groupId) {
        return $http.get('/pa165/rest/groups/get/all/:groupId')
    };
    vm.addUser = function (groupId) {
        return $http.put('/pa165/rest/groups/update/:groupId')
    };
    return vm;
}