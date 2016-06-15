import {ContentPanelComponent} from "./content-panel/content-panel";
import {ToolbarComponent} from "./toolbar/toolbar-component";
import {SidebarComponent} from "./sidebar/sidebar-component";
import {SkeletonComponent} from "./skeleton/skeleton.component";
import {LoginService} from "./login/login-service";

console.log('Creating an angular module - Should appear only once.');
declare var angular: ng.IAngularStatic;
const myModule : ng.IModule = angular.module('demo', []);


/**
 * Components
 */
myModule.component('skeleton', SkeletonComponent);
myModule.component('contentPanel', ContentPanelComponent);
myModule.component('toolbar', ToolbarComponent);
myModule.component('sidebar', SidebarComponent);


/**
 * Services
 */
myModule.service('loginService', LoginService);

export const demoModule = myModule;





