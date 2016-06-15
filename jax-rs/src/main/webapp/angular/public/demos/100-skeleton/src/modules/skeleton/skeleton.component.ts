declare function require(string: string): any;
let template = require('./skeleton.template.html');

class SkeletonController{


}

export const SkeletonComponent : ng.IComponentOptions= {
    controller : SkeletonController,
    template : template
};




