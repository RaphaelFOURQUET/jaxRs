
function UserCtrl(){

    console.log('Component users at the start :', this.users);
    this.sortedUsers = this.users.sort();
}

angular.module("demo", [])
    .controller('AppCtrl', function($scope){
        $scope.users = ['John', 'Jim', 'Albert', 'Luc', 'Sheldon'];
    })
    .component("usersComponent", {  // returns an object, not a function

        template: '<ul><li ng-repeat = "user in $ctrl.sortedUsers">{{user}}</li></ul>',
        controller : UserCtrl,
        bindings:{
            users : '='
        }

    });

