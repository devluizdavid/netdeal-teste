var app = angular.module("authApp", ["ngRoute"]);

var BASE_URL_API = "http://localhost:8083";

app.config(function ($routeProvider, $httpProvider) {
  delete $httpProvider.defaults.headers.common["X-Requested-With"];
  $routeProvider
    .when("/home", {
      templateUrl: "views/home.html",
      controller: "HomeController",
    })
    .when("/login", {
      templateUrl: "views/login.html",
      controller: "LoginController",
    })
    .otherwise({ redirectTo: "/login" });
});

app.controller("HomeController", function ($scope, $http, $location) {
  var encodedCredentials = btoa(
    sessionStorage.credentialLogin + ":" + sessionStorage.credentialSenha
  );

  var config = {
    headers: {
      Authorization: "Basic " + encodedCredentials,
    },
  };

  
  $scope.listaUsuario = function () {
    $http.get(BASE_URL_API + "/usuario", config).then(function (response) {
      $scope.colaboradorList = response.data;
    });
  };

  $scope.salvarUsuario = function () {
    var dadosDoUsuario = {
      nome: $scope.nome,
      login: $scope.login,
      senha: $scope.senha,
      colaboradorPai: sessionStorage.usuarioId,
    };

    $http
      .post(BASE_URL_API + "/usuario", dadosDoUsuario, config)
      .then(function (response) {
        console.log("Listou os dados");
        $scope.listaUsuario();
      })
      .catch(function (error) {
        alert("Erro de autenticação:", error);
        console.error("Erro de autenticação:", error);
        $scope.listaUsuario();
      });
  };

  $scope.listaUsuario();
});

app.controller("LoginController", function ($scope, $location, $http) {
  $scope.submit = function () {
    // Codifique as credenciais para o formato Basic Auth
    var encodedCredentials = btoa($scope.login + ":" + $scope.senha);

    // Configure o cabeçalho Authorization com o valor Basic
    var config = {
      headers: {
        Authorization: "Basic " + encodedCredentials,
      },
    };

    var credentials = {
      login: $scope.login,
      senha: $scope.senha,
    };

    // Realiza uma solicitação POST para autenticar
    $http
      .post(BASE_URL_API + "/login", credentials, config)
      .then(function (response) {
        sessionStorage.credentialLogin = credentials.login;
        sessionStorage.credentialSenha = credentials.senha;
        sessionStorage.usuarioId = response.data.id;
        $location.path("/home");
      })
      .catch(function (error) {
        // Se a autenticação falhar, lida com o erro aqui.
        console.error("Erro de autenticação:", error);
        $location.path("/");
      });

    // Implemente a lógica de autenticação aqui, se necessário.
    // Por padrão, o redirecionamento para a página inicial é feito.
  };
});
