/**
 * Created by rodrigo on 10/25/15.
 */
mockenize.service('mockenizeService', function ($http) {
    var self = this;

    self.getMocks = function () {
        return $http.get('/_mockenize').then(function (response) {
            return response.data;
        });
    };

    self.createMock = function (mock) {
        return $http.post('/_mockenize', mock).then(function (response) {
            return mock;
        });
    };

    self.deleteMock = function (url) {
        return $http({
            url: '/_mockenize',
            method: 'DELETE',
            data: [{ url: url}],
            headers: {
                'Content-type': 'application/json'
            }
        }).then(function (response) {
            return response.data;
        });
    }
});