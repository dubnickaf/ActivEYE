/**
 * Created by spriadka on 12/15/16.
 */
angular.module('activeye.services').factory('Router',Router);

Router.$inject = ['$location'];

function Router($location) {
    var vm = this;
    vm.redirect = function (url) {
        console.log("redirecting");
        $location.path(url);
    };
    vm.initialize = function () {
        console.log('Router initialized');
    };
    return vm;
}