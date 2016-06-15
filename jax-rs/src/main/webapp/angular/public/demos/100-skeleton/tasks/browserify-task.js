var gulp = require('gulp');
var browserify = require('browserify');
//Watch for file changes and make events
var watchify     = require('watchify');
//Use text stream for better interoperability with the existing npm stream ecosystem
var vinylSourceStream = require('vinyl-source-stream');
//Extract source maps from vinyl stream
var exorcist = require('exorcist');
var tsify = require('tsify');
var stringify = require('stringify');


var browserifyTask = function (callback, devMode) {


     bundleWithStringify();
    //watchifyAndTsify();


};


/**
 * We do all in once :
 * - define browserify sources and transformations
 * - bundle
 * - pipe
 */
function bundleWithStringify(){

    browserify({debug:true})
        .transform(stringify, {
            appliesTo: { includeExtensions: ['.hjs', '.html', '.txt'] }
        })
        .add('./src/index.ts')
        .add('./typings/browser.d.ts')
        .plugin(tsify)
        .bundle()
        .on('error', function (error) { console.error(error.toString()); })
        .pipe(exorcist('./dist/app-build.js.map'))
        .pipe(vinylSourceStream('./dist/app-build.js'))
        .pipe(gulp.dest('./'));
}


function pipe(browserifiedSource){

    browserifiedSource
        .bundle()
        .on('error', function (error) { console.error(error.toString()); })
        .pipe(exorcist('./dist/app-build.js.map'))
        .pipe(vinylSourceStream('./dist/app-build.js'))
        .pipe(gulp.dest('./'));

}


/**
 * we split :
 * - define sources and transformations
 * - we watch
 * - we pipe
 */
function watchifyAndTsify(){
    var browserifiedSource = getTransformedSource();
    var watchifiedSource = watchify(browserifiedSource);
    watchSources(watchifiedSource);

    //create the bundle
    pipe(watchifiedSource);
}

function watchSources(watchifiedSource){
    //when a file is changed, we will package, but work only on the small changed file
    watchifiedSource .on('update', function(){
        console.log("updated source");
        pipe(watchifiedSource);
    });

    //when a file is changed, we will output the change
    watchifiedSource.on('log', function (msg) {
        console.log(msg);
    });
}


function getTransformedSource(){
    return browserify({debug:true})
        .transform(stringify, {
            appliesTo: { includeExtensions: ['.hjs', '.html', '.txt'] }
        })
        .add('index.ts')
        .plugin(tsify);
}





// Exporting the task so we can call it directly in our watch task, with the 'devMode' option
module.exports = browserifyTask;