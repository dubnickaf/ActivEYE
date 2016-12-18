/**
 * Created by spriadka on 12/16/16.
 */

angular.module('activeye.services').factory('SelectedUsersService',SelectedUsersService);

function SelectedUsersService(){
    var vm = this;
    vm.multipleSelect;
    vm.getSelected = function(){
        console.log("From multipleselect",vm.multipleSelect);
        return vm.multipleSelect;
    };
    vm.setSelected = function(groupsArray){
        vm.multipleSelect = groupsArray;
        console.log("to multipleselect",vm.multipleSelect);
    };

    vm.flush = function(){
        console.log("flush");
        vm.multipleSelect = undefined;
    };
    return vm;
}