/**
 * authenticates user
 * @author dubnickaf@gmail.com
 */

angular.module('activeye.services')
    .service('UserService', ['$resource',function ($resource) {
        return $resource('/rest/users',null,{
                query: {
                    method : 'GET',
                    url: 'rest/users/all',
                    isArray: true
                }
        });
    }]);
