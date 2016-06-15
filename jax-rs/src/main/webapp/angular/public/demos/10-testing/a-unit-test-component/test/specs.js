describe('sorting the list of users', function() {
    it('sorts in descending order of name size', function() {
        var users = ['jackye', 'igor', 'Francesca'];
        var sorted = sortBySize(users);
        expect(sorted).toEqual(['jackye', 'igor', 'Francesca']);
    });
});




describe('Unit component as a directive', function() {
    var $compile,
        $rootScope;

    // Load the demo module, which contains the directive
    beforeEach(module('demo'));

    // Store references to $rootScope and $compile
    // so they are available to all tests in this describe block
    beforeEach(inject(function(_$compile_, _$rootScope_){
        // The injector unwraps the underscores (_) from around the parameter names when matching
        $compile = _$compile_;
        $rootScope = _$rootScope_;
    }));

    $rootScope.users=['John', 'Jim'];

    it('shows a number of people', function() {
        // Compile a piece of HTML containing the directive
        var element = $compile("<users-component users='users'>")($rootScope);
        // fire all the watches, so the scope expression {{1 + 1}} will be evaluated
        $rootScope.$digest();
        // Check that the compiled element contains the templated content
        expect(element.html()).toContain("John");
    });
});