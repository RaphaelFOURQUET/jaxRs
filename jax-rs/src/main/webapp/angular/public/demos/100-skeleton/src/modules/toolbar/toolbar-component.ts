declare function require(string: string): any;
let template = require('./toolbar-template.html');

class ToolbarController{


}

export const ToolbarComponent : ng.IComponentOptions= {
    controller : ToolbarController,
    template : template
};

