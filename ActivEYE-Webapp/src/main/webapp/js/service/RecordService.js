/**
 * @author Branislav Bajuzik; 442772
 */

angular.module('activeye.services').factory('RecordService', RecordService);

RecordService.$inject = ['$http'];

function RecordService($http) {
    var vm = this;
    vm.create = function () {
        return $http.get('/pa165/rest/records/create')
    };
    vm.update = function () {
        return $http.put('/pa165/rest/records/update')
    };
    vm.delete = function () {
        return $http.delete('/pa165/rest/records/delete/:recordId')
    };
    vm.findById = function () {
        return $http.get('/pa165/rest/records/get/:recordId')
    };
    vm.findAll = function () {
        return $http.get('/pa165/rest/records/get/all')
    };
    return vm;
}