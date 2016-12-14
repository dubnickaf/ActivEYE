angular.module('mainApp')
    .factory('UserService', function ($http) {
        return {
            /**
             * authenticates user
             * @author dubnickaf@gmail.com
             */
            authenticateUser: function (nauDto) {

                return $http.get('users/login', {
                    params: {
                        nauDto:nauDto
                    }
                }).then(function (response) {
                    return response;
                });
            }
        }
    });
