/**
 * Created by spriadka on 12/15/16.
 */
angular.module('activeye.services').factory('Session',Session);

function Session() {
    var vm = this;
    vm.user = undefined;
    vm.saveUser = function(user){
        vm.user = user;
    };
    vm.getUser = function(){
        return vm.user;
    };
    vm.initialize = function(){
        console.log('Session initialized');
    };
    return vm;
}