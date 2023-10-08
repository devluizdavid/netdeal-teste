'use strict';

angular.module('myApp')
  .service('AuthService', function ($q, $timeout) {
    var isAuthenticated = false;

    var BASE_URL_API = "http://localhost:8083/api";
    
    var credentials = {
      login: $scope.login,
      senha: $scope.senha,
    };

    
    // Simulated login logic
    this.login = function (username, password) {
      var deferred = $q.defer();

      $http
      .post(BASE_URL_API + "/auth/token", credentials)
      .then(function (response) {
        sessionStorage.token = response.data.token;
        isAuthenticated = true;
        deferred.resolve();
        sessionStorage.usuarioId = response.data.id;
        $location.path("/home");
      })
      .catch(function (error) {
        console.error("Erro de autenticação:", error);
        isAuthenticated = false;
        deferred.reject('Invalid credentials');
        $location.path("/");
      });
      return deferred.promise;
    };
 
    this.logout = function () {
      isAuthenticated = false;
      sessionStorage.token = null; 
      sessionStorage.usuarioId = null;
    };
 
    this.isAuthenticated = function () {
      return isAuthenticated;
    };
  });