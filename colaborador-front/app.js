var app = angular.module('authApp', ['ngRoute']);

var BASE_URL_API = 'http://localhost:8083'

app.config(function($routeProvider, $httpProvider) {
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
  $routeProvider
    .when('/home', {
      templateUrl: 'views/home.html',
      controller: 'HomeController',
    })
    .when('/login', {
      templateUrl: 'views/login.html',
      controller: 'LoginController',
    })
    .otherwise({ redirectTo: '/login' });
});

app.controller('HomeController', function($scope, $http) {
    var encodedCredentials = btoa(sessionStorage.credentialLogin + ':' +  sessionStorage.credentialSenha);
    console.log(encodedCredentials);
    var config = {
        headers: {
            'Authorization': 'Basic ' + encodedCredentials
        }
    };

   $http.get(BASE_URL_API+'/colaborador', config)
    .then(function(response) {
        $scope.colaboradorList = response.data;
      
    }); 
});

app.controller('LoginController', function($scope, $location, $http) {
    


  $scope.submit = function() {
    // Codifique as credenciais para o formato Basic Auth
    var encodedCredentials = btoa($scope.login + ':' +  $scope.senha);
     
    // Configure o cabeçalho Authorization com o valor Basic
    var config = {
        headers: {
            'Authorization': 'Basic ' + encodedCredentials
        }
    };

    var credentials = {
        login: $scope.login,
        senha: $scope.senha
      };

    // Realiza uma solicitação POST para autenticar
    $http.post(BASE_URL_API+'/login', credentials, config)
    .then(function (response) {
        sessionStorage.credentialLogin = credentials.login;
        sessionStorage.credentialSenha = credentials.senha;
        $location.path('/home');
    })
    .catch(function (error) {
      // Se a autenticação falhar, lida com o erro aqui.
      console.error('Erro de autenticação:', error);
      $location.path('/');   
    });

    // Implemente a lógica de autenticação aqui, se necessário.
    // Por padrão, o redirecionamento para a página inicial é feito.
    
  };
});
