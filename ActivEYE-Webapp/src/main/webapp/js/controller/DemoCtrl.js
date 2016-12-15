/**
 * Created by spriadka on 12/11/16.
 */
angular.module('mainApp').controller('DemoController',DemoController);

function DemoController(UserService,mediator){
    mediator.listen('user:login:validation').act(function(event,data){
        UserService.login(data,function(res){
            console.log(res);
        })
    });
}