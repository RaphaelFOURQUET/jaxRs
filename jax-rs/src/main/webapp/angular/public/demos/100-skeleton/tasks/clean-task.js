var gulp = require('gulp');
//Vanilla node for deleting files and folders
var del = require('del');

//easily get the file path of files in the stream (and pass it to the del method)
var vinylPaths = require('vinyl-paths');



var cleanTask = function (callback, devMode) {

    return gulp
    //{read:false} will avoid to lose time to read these files
        .src(['.tmp','tmp-test', 'dist'], { read: false })
        //get the paths of each files and delete them
        .pipe(vinylPaths(del));

};

module.exports = cleanTask;
