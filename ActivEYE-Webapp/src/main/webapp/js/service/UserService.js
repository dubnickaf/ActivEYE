/**
 * authenticates user
 * @author dubnickaf@gmail.com
 */

angular.module('activeye.services').factory('UserService', UserService);

UserService.$inject = ['$resource'];

function UserService($resource) {
        return $resource(null, null, {
            getAll: {
                method: 'GET',
                isArray: true,
                url: '/pa165/rest/users/all'
            },
            login: {
                method: 'POST',
                params: '@NotAuthenticatedUser',
                url: '/pa165/rest/users/login'
            }
        });
}
