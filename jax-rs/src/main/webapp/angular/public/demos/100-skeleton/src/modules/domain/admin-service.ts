import {demoModule} from "../demo-module";

import {Admin} from "./admin";

class AdminService{

    $http:ng.IHttpService;

    constructor($http:ng.IHttpService){
        this.$http =  $http;
    }

    getAdmin (id : number):ng.IPromise<Admin> {
        return this.$http.get(`/api/admins/${id}`).then(response => response.data);
    }

}

demoModule.factory("adminService", AdminService);

