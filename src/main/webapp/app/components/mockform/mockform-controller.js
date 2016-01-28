/**
 * Created by rodrigo on 10/25/15.
 */
mockenize.controller('mockFormController', function ($q, httpMethods, httpHeaders, statusCodes, codeMirrorOptions, mockenizeService, Notification) {
    var vm = this;
    vm.httpMethods = httpMethods;
    vm.httpHeaders = httpHeaders;
    vm.statusCodes = statusCodes;
    vm.codeMirrorOptions = codeMirrorOptions;
    vm.newHeaderKey = null;
    vm.newHeaderValue = null;
    vm.model = null;
    vm.onSave = null;

    vm.isValid = function () {
        return vm.mockForm.$dirty && vm.mockForm.$valid;
    };

    vm.load = function (mock, onSave) {
        vm.model = mock;
        vm.onSave = $q.defer();
        return vm.onSave.promise;
    };

    vm.save = function () {
        mockenizeService.createMock(vm.model).then(function (mock) {
            vm.model = mock;
            Notification.success("Mock saved.");
            vm.onSave.resolve(mock);
        }, function () {
            Notification.error("An error occured while trying to save mock.");
        });
    };

    vm.addHeader = function () {
        if (!vm.newHeaderKey || !vm.newHeaderValue) {
            return;
        }

        vm.model.headers[vm.newHeaderKey] = vm.newHeaderValue;
        vm.newHeaderKey = null;
        vm.newHeaderValue = null;
    };

    vm.selectHeader = function (key) {
        vm.newHeaderKey = key;
        vm.newHeaderValue = vm.model.headers[key];
    };

    vm.removeHeader = function (key) {
        delete vm.model.headers[key];
    };
});