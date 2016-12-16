/**
 * Created by spriadka on 12/16/16.
 */

angular.module('activeye.services').factory('SelectedUsersService',SelectedUsersService);

function SelectedUsersService(){
    var vm = this;
    vm.selected = undefined;
    vm.getSelected = function(){
        return vm.selected;
    };
    vm.select = function(selected){
        vm.selected = selected;
    };
    vm.flush = function(){
        vm.selected = undefined;
    };
    return vm;
}