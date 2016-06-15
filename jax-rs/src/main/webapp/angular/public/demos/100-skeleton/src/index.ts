import './modules/demo-module';
import './my-declarations.d.ts';
declare function require(string: string): any;

import {demoModule} from "./modules/demo-module";
import {Admin} from './modules/domain/admin';
import './modules/skeleton/skeleton.component.ts'
import './modules/admin-panel/admin-panel-component.ts'
import './modules/content-panel/content-panel.ts'
import {LoginService} from './modules/login/login-service.ts'
import './modules/sidebar/sidebar-component.ts'
import './modules/toolbar/toolbar-component.ts'





var loggedIn = new Admin('John Doe');

demoModule.run( (loginService: LoginService)=>{

    //we'll do our login that way has for now
    loginService.user = loggedIn;

} );



