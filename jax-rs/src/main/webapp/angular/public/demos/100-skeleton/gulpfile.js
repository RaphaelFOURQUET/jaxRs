'use strict';

var gulp = require('gulp');


var browserifyTask = require("./tasks/browserify-task");
var watchifyTask = require("./tasks/watchify-task");
var hmrTask = require("./tasks/hmr-task");
var cleanTask = require("./tasks/clean-task");


gulp.task('by', ['assets'], browserifyTask);
gulp.task('wy', watchifyTask);
gulp.task('hmr', hmrTask);
gulp.task('clean', cleanTask);

gulp.task('assets', function(){
        gulp.src('assets/*').pipe(gulp.dest('dist/'));
});

gulp.task('prod', ['by'], function(){

});
