/**
 * Created by spriadka on 12/17/16.
 */


angular.module('activeye.services').factory('CreateRecordService',CreateRecordService);

CreateRecordService.$inject = [];

function CreateRecordService(){
    var vm = this;
    vm.activity = undefined;
    vm.startTime = undefined;
    vm.endTime = undefined;
    vm.calories = undefined;
    vm.getStartTime = function(){
        return vm.startTime;
    };
    vm.getEndTime = function(){
        return vm.endTime;
    };
    vm.getActivity = function(){
        return vm.activity;
    };
    vm.setStartTime = function(startTime){
        vm.startTime = startTime;
    };
    vm.setEndTime = function(endTime){
        vm.endTime = endTime;
    };
    vm.setActivity = function(activity){
        vm.activity = activity;
    };
    vm.setCalories = function(calories){
        vm.calories = calories;
    };
    vm.getCalories = function(){
        return vm.calories;
    };
    return vm;
}