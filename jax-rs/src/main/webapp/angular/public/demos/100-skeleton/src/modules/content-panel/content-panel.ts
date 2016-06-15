declare function require(string: string): any;
let template = require('./content-panel-template.html');

class ContentPanelController{

    moreContent = 'Additional synamic Content';
}

export const ContentPanelComponent : ng.IComponentOptions= {
    controller : ContentPanelController,
    template : template
};

