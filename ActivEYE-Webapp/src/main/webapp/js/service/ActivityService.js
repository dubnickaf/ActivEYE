/**
 * @author  Matej Vasko 422713
 * @version 1.0
 * @since   15-12-2016
 */
angular.module('activeye.services').factory('ActivityService', ActivityService);

ActivityService.$inject = ['$http'];

function ActivityService($http) {
    var vm = this;
    vm.create = function () {
        return $http.get('/pa165/rest/activities/create')
    };
    vm.update = function () {
        return $http.put('/pa165/rest/activities/update')
    };
    vm.delete = function () {
        return $http.delete('/pa165/rest/activities/delete/:activityId')
    };
    vm.findById = function () {
        return $http.get('/pa165/rest/activities/get/:activityId')
    };
    vm.findByName = function (name){
        return $http.get('/pa165/rest/activities/get',{
            params: {name: name}
        });
    };
    vm.getAll = function () {
        return $http.get('/pa165/rest/activities/get/all')
    };
    return vm;
}