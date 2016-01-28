mockenize.directive('mockForm', function () {
    return {
        restrict: 'E',
        replace: true,
        controller: 'mockFormController',
        controllerAs: 'vm',
        templateUrl: 'app/components/mockform/mockform-template.html',
        scope: {
            name: '@name'
        },

        link: function (scope, el, attrs, ctrl) {
            scope.$parent.vm.mockForm = ctrl;
        }
    }
});