angular.module("forum", [])
    .controller("UserController", function($scope, $http, userService)  {
     
        //Dans view body : add ng-controller
        
        //dans le $scope : add users
        $scope.users = userService.getUsers();
        
        //afficher users en HTML

    })
    
    .controller("ForumController", function ($scope, $http, topicService, userService, apiService) {

        $scope.users = userService.getUsers();

        $scope.data = {
            content: "",
            user: ''
        };

        $http.get(apiService.root+"topics/")
            .then(function (response) {     //Penser au fait que cet appel sera asynchrone, donc recuperer dans le then.
                console.log(response);
                /*console.log(response.data[0]);*/
                $scope.topics = response.data;
            });
        //$scope.topics = topicService.getTopics();
        $scope.isUndefined = function(name) {
            return name === undefined;
        };
        $scope.formatCommenterName = function(name) {
            if(name === undefined)
                return 'Anonymous';
            else return name;
        };
        $scope.up = function(comment) {
            if(comment.score === undefined) comment.score = 0;
            comment.score++;
        };
        $scope.down = function(comment) {
            if(comment.score === undefined) comment.score = 0;
            comment.score--;
        };
        $scope.getScoreClass = function(score) {
            if(score === undefined || score === 0) return '';
            if(score > 0) return 'like';
            if(score < 0) return 'dislike';

        };
        $scope.createTopic = function () {
            console.log("topicUser");
            console.log($scope.model.topicUser);
            console.log("topicTitle");
            console.log($scope.model.topicTitle);

            $scope.data.user = $scope.model.topicUser;
            $scope.data.title = $scope.model.topicTitle;

            $http.post("http://localhost:3000/api/topics/", $scope.data)
                .then(function (response) {
                    $scope.topics.push(response.data);
                })
                .catch(function (error) {
                    console.log(error);
                    alert("ERROR "+error.status+" "+error.statusText);
                })

        };

    })
    
    .factory("userService", function($q, $http, apiService){

        var users = null; // could be used as a cache

        var service = {

            getUsers : function(){
               //return from mock-data
                return globals.users;
            },
            getAdmins : function(){
                return service.getUsers().then(function(users){
                    //filter, map, some, foreach
                    return users.filter(function(user){
                        return user.admin;
                    })
                })
            }
        };
        return service;
    }).factory("apiService", function(){

        var service ={
            root : "/jax-rs/forum/"
        };
        return service;
    })

    .factory("topicService", function(apiService) {
        //var topics = null; // could be used as a cache

        var service = {

            getTopics : function(){
                //return from mock-data
                return globals.topics;
            }
        };
        return service;
    });





























