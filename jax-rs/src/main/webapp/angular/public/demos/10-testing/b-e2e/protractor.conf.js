/**
 * Created by  User: nicorama
 * Date: 03/11/2014 ; Time: 19:46
 */
exports.config = {
    seleniumAddress: 'http://localhost:4444/wd/hub',
    specs: ['./spec.js'],
    framework:'jasmine2'  //if not, will bug with MacOS
};