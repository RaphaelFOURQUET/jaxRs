/**
 * Created by  User: nicorama
 * Date: 03/11/2014 ; Time: 19:51
 */
'use strict';

/* https://github.com/angular/protractor/blob/master/docs/toc.md */


console.log("in protractor spec")
var appUrl = 'http://localhost:63342/formations-javascript/angular-advanced/demos/10-testing/b-e2e/src/index.html';

describe('Protractor and Webdriver', function() {
    it('should be true', function() {
        expect(true).toBe(true);    
    })
      
})

describe('my app', function() {


    browser.get(appUrl);

    it('should have a click button', function() {


        var buttons = element.all(by.css('button.load-users'))
        expect(buttons.count()).toBe(1);

    });


    
    it('should load users on click', function () {

        var button = element(by.css('button.load-users'))

        expect(button.isPresent()).toBeTruthy();
        button.click();

        var items = element.all(by.css('li'));
        expect(items.count()).toBeGreaterThan(6)
        expect(items.count()).toBeLessThan(25)


    });


});
