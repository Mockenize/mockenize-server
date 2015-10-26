mockenize.directive('mockList', function () {
    return {
        restrict: 'E',
        replace: true,
        controller: 'mockListController',
        controllerAs: 'vm',
        templateUrl: 'app/components/mocklist/mocklist-template.html'
    }
});