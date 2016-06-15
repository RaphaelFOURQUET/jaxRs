declare function require(string: string): any;

var template = require('./template.html');


export class Admin{

    name:string;
    constructor(name : string){
        this.name = name;
        console.log('creating an admin');
    }

    toString(){
        return this.name;
    }

    toHtml(){
        return template.replace('{{name}}', this.name);
    }
}

