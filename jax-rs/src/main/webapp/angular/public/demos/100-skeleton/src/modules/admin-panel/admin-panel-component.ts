import {demoModule} from "../demo-module";
declare function require(string: string): any;

let template = require('./admin-panel-template.html');

class AdminPanelComponent{

    users : ['John', 'Jim', 'Joe'];

}

demoModule.component('adminPanelComponent', {

    controller : AdminPanelComponent

});