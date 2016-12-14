angular.module('mainApp')
    .factory('UserService', function ($http) {
        return {
            /**
             * authenticates user
             * @author dubnickaf@gmail.com
             */
            authenticateUser: function (nauDto) {

                return $http.get('api/login', {
                    params: {
                        nauDto:nauDto
                    }
                }).then(function (response) {
                    return response;
                });
            }
        }
    });
