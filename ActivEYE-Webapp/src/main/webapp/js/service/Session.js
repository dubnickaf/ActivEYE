/**
 * Created by spriadka on 12/15/16.
 */
angular.module('activeye.services').factory('Session',Session);

function Session() {
    var vm = this;
    vm.user = undefined;
    vm.loggedIn = false;
    vm.isAnyoneLoggedIn = function(){
        return vm.loggedIn;
    };
    vm.saveUser = function(user){
        if (user === undefined) vm.loggedIn = false; else vm.loggedIn = true;
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