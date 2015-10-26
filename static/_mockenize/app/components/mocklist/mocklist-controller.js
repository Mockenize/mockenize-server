/**
 * Created by rodrigo on 10/25/15.
 */
mockenize.controller('mockListController', function ($uibModal, codeMirrorOptions, mockenizeService) {
    var vm = this;
    vm.codeMirrorOptions = codeMirrorOptions;
    vm.mocks = {};

    loadMocks();

    vm.hasMocks = function () {
        return angular.equals(vm.mocks, {});
    };

    vm.openMockForm = function () {
        var instance = $uibModal.open({
            controller: 'mockFormController',
            bindToController: true,
            controllerAs: 'vm',
            modal: 'lg',
            templateUrl: 'app/components/mocklist/mockform-template.html'
        });

        instance.result.then(function (mock) {
            loadMocks();
        })
    };

    vm.selectMock = function (mock) {
        vm.selectedMock = mock;
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