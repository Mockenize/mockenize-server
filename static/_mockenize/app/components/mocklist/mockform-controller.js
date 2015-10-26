/**
 * Created by rodrigo on 10/25/15.
 */
mockenize.controller('mockFormController', function ($uibModalInstance, codeMirrorOptions, mockenizeService) {
    var vm = this;
    vm.codeMirrorOptions = codeMirrorOptions;
    vm.newHeaderKey = null;
    vm.newHeaderValue = null;
    vm.mock = {
        method: 'GET',
        responseCode: 200,
        headers: {}
    };

    vm.dismiss = function () {
        $uibModalInstance.dismiss();
    };

    vm.isValid = function () {
        return vm.mockForm.$dirty && vm.mockForm.$valid;
    };

    vm.save = function () {
        mockenizeService.createMock(vm.mock).then(function (mock) {
            $uibModalInstance.close(mock);
        });
    };

    vm.addHeader = function () {
        if (!vm.newHeaderKey || !vm.newHeaderValue) {
            return;
        }

        vm.mock.headers[vm.newHeaderKey] = vm.newHeaderValue;
        vm.newHeaderKey = null;
        vm.newHeaderValue = null;
    };

    vm.selectHeader = function (key) {
        vm.newHeaderKey = key;
        vm.newHeaderValue = vm.mock.headers[key];
    };

    vm.removeHeader = function (key) {
        delete vm.mock.headers[key];
    };
});