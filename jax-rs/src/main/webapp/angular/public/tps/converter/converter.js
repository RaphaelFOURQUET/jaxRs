var module = angular.module("converter", []);

module.controller("ConverterCtrl", function($scope){

    $scope.model = {
        input : '0',
        inputUnit : "km",
        outputUnit : "m"
    };


    $scope.units = [ "km", "m", "mm", "miles", "yards", "feet" ];

    $scope.showStuff = function(){
        alert($scope.inputUnit);
    };

    $scope.convert = function(){
        //call global convert()
        //console.log($scope.model.input);
        $scope.output = globalConvert($scope.model.input, $scope.model.inputUnit, $scope.model.outputUnit);

    };



    //TODO : use correctly ng-model to match the convert() function call    RFRF: Ok
    //use ng-change to remove the click button      RFRF: Ok



});


var conversion = [
    [ 1, 1000, 1000000, 0.6213711, 1094, 3280.8399166 ],
    [ 0.001, 1, 1000, 0.0006213, 1.0938712, 3.2804478 ],
    [ 0.000001, 0.001, 1, 0.0000006213, 0.0010938712, 0.0032804478 ],
    [ 1.6093442, 1609.3442, 1609344.2, 1, 1760.6225973, 5280.0007769 ],
    [ 0.000914, 0.914, 914, 0.0005679, 1, 2.9933802 ],
    [ 0.0003047, 0.3047, 304.7, 0.0001893, 0.3331342, 1 ]
];

var globalUnits = [ "km", "m", "mm", "miles", "yards", "feet" ];

var globalConvert = function (input, inputUnit, outputUnit) {

    //console.log(input);

    var result;
    var idUnite1 = 0;
    var idUnite2 = 0;

    if (!input) {
        return "awful error, no valid input ";
    } else {
        for (var i = 0; i < globalUnits.length; i++) {

            if (inputUnit == globalUnits[i]) {//---> KEEP
                idUnite1 = i;
            }
            if (outputUnit == globalUnits[i]) {
                idUnite2 = i;
            }
        }
        result = parseFloat(input) * conversion[idUnite1][idUnite2];
        /*console.log(input);
        console.log(parseFloat(input));
        console.log(conversion[idUnite1][idUnite2]);
        console.log(result);
        console.log(10*conversion[idUnite1][idUnite2]);*/
        return result;
    }
};