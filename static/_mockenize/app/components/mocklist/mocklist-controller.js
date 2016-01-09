/**
 * Created by rodrigo on 10/25/15.
 */
mockenize.controller('mockListController', function (codeMirrorOptions, mockenizeService) {
    var vm = this;
    vm.codeMirrorOptions = codeMirrorOptions;
    vm.mocks = {};
    vm.selectedMock = null;

    loadMocks();

    vm.hasMocks = function () {
        return angular.equals(vm.mocks, {});
    };

    vm.openMockForm = function () {
        vm.selectedMock = {
            method: 'GET',
            responseCode: 200,
            headers: {}
        };

        vm.mockForm.load(vm.selectedMock).then(loadMocks);
    };

    vm.selectMock = function (mock) {
        vm.selectedMock = mock;
        vm.mockForm.load(angular.copy(mock)).then(loadMocks);
    };

    vm.deleteMock = function (url) {
        if (confirm("Are you sure?")) {
            mockenizeService.deleteMock(url).then(function () {
                delete vm.mocks[url];
            });
        }
    };

    vm.countKeys = function (obj) {
        if (!obj) {
            return 1;
        }

        return Object.keys(obj).length + 1;
    };

    function loadMocks() {
        mockenizeService.getMocks().then(function (mocks) {
            vm.mocks = mocks;
        });
    }
});