declare function require(string: string): any;
let template = require('./sidebar-template.html');

class SidebarController{

    //to be set by login
    isAdmin = true;

}

export const SidebarComponent : ng.IComponentOptions= {
    controller : SidebarController,
    template : template
};

