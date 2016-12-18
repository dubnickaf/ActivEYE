/**
 * Created by spriadka on 12/16/16.
 */

angular.module('activeye.services').factory('SelectedUsersService',SelectedUsersService);

function SelectedUsersService(){
    var vm = this;
    vm.multipleSelect = [];
    vm.getSelected = function(){
        console.log("vm.multipleSelect");
        console.log(vm.multipleSelect);
        return vm.multipleSelect;
    };
    vm.select = function(selected){
        vm.multipleSelect = selected;
    };
    vm.flush = function(){
        vm.multipleSelect = undefined;
    };
    return vm;
}