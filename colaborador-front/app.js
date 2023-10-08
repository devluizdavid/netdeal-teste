var app = angular.module("colaboradorApp", ["ngRoute"]);

var BASE_URL_API = "http://localhost:8083/api";

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
  console.log(sessionStorage.autenticado);
  if (!sessionStorage.autenticado) {
    $location.path("/login");
    return;
  }

  var config = {
    headers: {
      Authorization: "Bearer " + sessionStorage.token,
    },
  };

  $scope.nomeUsuario  = sessionStorage.nomeUsuario;
  
  $scope.listaUsuario = function () {
    $http.get(BASE_URL_API + "/usuario/colaboradores/"+sessionStorage.usuarioId, config).then(function (response) {
      $scope.colaboradorList = response.data; 
    });
  };
  $scope.logout = function () {
    sessionStorage.token = null;
    sessionStorage.autenticado = false;
    sessionStorage.usuarioId = null;
    sessionStorage.nomeUsuario = null;
    $location.path("/login");
  }
 
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
        $scope.messagem = "Usuário cadastrado com sucesso.";
        $scope.messagemCor = "green";
      })
      .catch(function (error) {
        alert("Erro de autenticação:", error);
        console.error("Erro de autenticação:", error);
        $scope.messagem = "Erro ao cadastrar o usuário";
        $scope.messagemCor = "red";
        $scope.listaUsuario();
      });
  };
 
  $scope.listaUsuario();

});

app.controller("LoginController", function ($scope, $location, $http) {
  sessionStorage.clear();
  $scope.submit = function () {
   
    
    var credentials = {
      login: $scope.login,
      senha: $scope.senha,
    };

    // Realiza uma solicitação POST para autenticar
    $http
      .post(BASE_URL_API + "/auth/token", credentials)
      .then(function (response) {
        sessionStorage.token = response.data.token;
        sessionStorage.autenticado = true;
        sessionStorage.usuarioId = response.data.id;
        sessionStorage.nomeUsuario = response.data.nome;
        $location.path("/home");
      })
      .catch(function (error) {
        var statusCode = error.status;
        if (statusCode == 403) {
          $scope.errorMessage = 'Erro ao efetuar login. Verifique os dados e tente novamente.';
        } else {
          $scope.errorMessage = 'Falha de comunicação com a Api.';
        }
        
      });

    // Implemente a lógica de autenticação aqui, se necessário.
    // Por padrão, o redirecionamento para a página inicial é feito.
  };
});
