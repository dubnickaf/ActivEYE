
angular.module('activeye.services').factory('RecordService', RecordService);

UserService.$inject = ['$http'];

function RecordService($http) {
    var vm = this;

    vm.create = function (record){
        return $http.post('/pa165/rest/records/create',record);
    };
    vm.update = function (record){
        return $http.put('/pa165/rest/records/update',record);
    };
    vm.delete = function (id){
        return $http.delete('/pa165/rest/records/delete/' + id);
    };
    vm.findById = function (id){
        return $http.get('/pa165/rest/records/get'+id);
    };
    vm.findAll = function () {
        return $http.get('/pa165/rest/records/get/all');
    };
    return vm;
}