'use strict';

var fastFoodApp = angular.module('fastFoodApp', ["ui.bootstrap"]);

fastFoodApp.controller('ProductController', function ($scope, $http, $filter,
                                                      dayService,
                                                      revAndSpendService,
                                                      productService) {

    $scope.day = {};

    $http.get('day/today').success(function (data) {
        $scope.day = data;
        $scope.addRevenueOperation.dayId = data.id;
        $scope.revenueToday = data.revenue;
        $scope.products = data.products; //todo: maybe use day here

        $scope.displayableDate = new Date($scope.day.date).toLocaleString('ru', dateFormatOptions);
    }).error(function () {
        $scope.serverError = 'Ошибка. Не получилось подгрузить продукты';
    });

    $http.get('measureUnit/list').success(function(data) {
       $scope.measureUnits = data;
    }).error(function () {
        $scope.serverError = 'Ошибка. Не получилось подгрузить меры измерения';
    });

    var dateFormatOptions = {
      year: 'numeric',
      month: 'long',
      day: 'numeric',
      weekday: 'long',
      timezone: 'UTC',
    };

    $scope.productCrudOperation = {
        operation: '',
        newProductName: '',
        editedProductId: '',
        measureUnit: '',
        selectedCrudOperation: ''
    };

    $scope.changeCrudOperation = function() {
        switch ($scope.productCrudOperation.operation) {
            case 'create':
                $scope.selectedCrudOperation = 'Создать';
                break;
            case 'edit':
                $scope.selectedCrudOperation = 'Редактировать';
                break;
            case 'delete':
                $scope.selectedCrudOperation = 'Удалить';
                break;
        }

        resetProductCrudOperationFields();
    };

    var resetProductCrudOperationFields = function() {
        $scope.serverError = '';
        $scope.productCrudOperation.editedProductId = 0;
        $scope.productCrudOperation.newProductName = '';
        $scope.productCrudOperation.measureUnit = '';
    };

    $scope.operateCrudProduct = function(editedProductId) {
        switch ($scope.productCrudOperation.operation) {
            case 'create':
                $scope.createProduct();
                break;
            case 'edit':
                $scope.editProduct(editedProductId);
                break;
            case 'delete':
                $scope.deleteProduct(editedProductId);
                break;
        }
    };

    $scope.createProduct = function () {
        if (!productService.validateCreationForm($scope.productCrudOperation)) {
            return false;
        }

        console.log("Create product with name:" + $scope.productCrudOperation.newProductName);
        var newProduct = {
            name: $scope.productCrudOperation.newProductName,
            measureUnit: $scope.productCrudOperation.measureUnit,
            dayId: $scope.day.id
        };
        $http.post('product/create', newProduct).success(function(data) {
            $scope.products.push(data);
            resetProductCrudOperationFields();
        }).error(function(data) {
            $scope.serverError = "Ошибка при сохранении нового продукта";
        });

    };

    $scope.editProduct = function (editedProductId) {
        if (!productService.validateEditForm($scope.productCrudOperation)) {
            return false;
        }

        var productToBeEdited = $filter('filter')($scope.products, {id: editedProductId})[0];
        console.log("Product to be edited: " + productToBeEdited.name);
        var oldProductName = productToBeEdited.name;
        productToBeEdited.name = $scope.productCrudOperation.newProductName;

        $http.post('product/update', productToBeEdited).error(function(data){
            $scope.serverError = "Ошибка при попытке редактирования";
            productToBeEdited.name = oldProductName;
        }).success(function(data) {
            console.log("Product after update: " + productToBeEdited.name);
            resetProductCrudOperationFields();
        });

    };

    $scope.deleteProduct = function(editedProductId) {
        if (!productService.validateDeleteForm($scope.productCrudOperation)) {
            return false;
        }

        var productToBeDeleted = $filter('filter')($scope.products, {id: editedProductId})[0];
        console.log("Product to be deleted: " + productToBeDeleted.name);

        $http.post('product/delete', productToBeDeleted).error(function(data){
            $scope.serverError = "Ошибка при попытке удаления";
        }).success(function(data) {
            resetProductCrudOperationFields();
            var indexToBeDeleted = $scope.products.indexOf(productToBeDeleted);
            $scope.products.splice(indexToBeDeleted, 1);
            console.log("Product '" + productToBeDeleted.name + "' deleted");
        });
    };

    $scope.productAmountOperation = {
        operation: '',
        dayProductId: '',
        amount: '',
        sum: '',
        date: ''
    };

    $scope.changeSelectedAmountOperation  = function() {
        $scope.selectedAmountOperation = $scope.productAmountOperation.operation == 'add' ? 'Добавить' : 'Списать';
        resetProductAmountOperationFieldsExceptOperationItself();
    };

    $scope.operateProductAmount = function () {
        if (!validateAmountOperationForm()) {
            return false;
        }

        console.log("productAmountOperation: " + $filter('json')($scope.productAmountOperation, 4));
        $scope.productAmountOperation.operation = $scope.productAmountOperation.operation.toUpperCase();

        $http.post('product/operateAmount', $scope.productAmountOperation).success(function (product) {
            var prodWhoseAmountOperated = $filter('filter')($scope.products, {dayProductId: $scope.productAmountOperation.dayProductId})[0];
            prodWhoseAmountOperated.addedAmount = product.addedAmount;
            prodWhoseAmountOperated.subtrAmount = product.subtrAmount;

            resetProductAmountOperationFields();
        }).error(function () {
            $scope.serverError = "Ошибка при попытке добавления/списания";
        });

    };

    function validateAmountOperationForm() {
        if ($scope.productAmountOperation.operation == ''
            || $scope.productAmountOperation.operation == undefined

            || $scope.productAmountOperation.dayProductId == ''
            || $scope.productAmountOperation.dayProductId == 0
            || $scope.productAmountOperation.dayProductId == undefined

            || $scope.productAmountOperation.amount == ''
            || $scope.productAmountOperation.amount == 0
            || $scope.productAmountOperation.amount == undefined

            || $scope.productAmountOperation.date == ''
            || $scope.productAmountOperation.date == 0
            || $scope.productAmountOperation.date == undefined
            || $scope.checkIfMaxAllowedDateExceed()
            || $scope.checkIfMinAllowedDateExceed()) {
            return false;
        }
        return true;
    }

    $scope.checkIfMaxAllowedDateExceed = function () {
        //if variable is not initialized yet do not fire validation error
        if ($scope.productAmountOperation == undefined) {
            return false;
        }

        var today = new Date();
        var selectedDate = $scope.productAmountOperation.date;

        return (selectedDate > today);
    };

    $scope.checkIfMinAllowedDateExceed = function () {
        //if variable is not initialized yet do not fire validation error
        if ($scope.productAmountOperation == undefined) {
            return false;
        }

        var todayMonthBefore = new Date().setMonth(new Date().getMonth() - 1);
        var selectedDate = $scope.productAmountOperation.date;

        return (selectedDate < todayMonthBefore);
    };

    function resetProductAmountOperationFields() {
        $scope.serverError = false;
        $scope.productAmountOperation.operation = '';
        $scope.productAmountOperation.dayProductId = '';
        $scope.productAmountOperation.amount = '';
        $scope.productAmountOperation.sum = '';
        $scope.productAmountOperation.date = '';
    }

    function resetProductAmountOperationFieldsExceptOperationItself() {
        $scope.serverError = false;
        $scope.productAmountOperation.dayProductId = '';
        $scope.productAmountOperation.amount = '';
        $scope.productAmountOperation.sum = '';
        $scope.productAmountOperation.date = '';
    }

    $scope.editByFactPopover = {
        templateUrl: 'editByFactPopoverTemplate.html',
        title: 'Редактирование по факту'
    };

    $scope.initByFactPopover = function(product) {
        console.log("Product for editing fact amount: " + $filter('json')(product, 4));

        $scope.editByFact = {
            amount: product.amount,
            product: product
        };
    };

    $scope.operateProductFactAmount = function() {
        var productFactAmountOperation = {
            dayProductId: $scope.editByFact.product.dayProductId,
            amount: $scope.editByFact.amount
        };

        console.log("ProductFactAmountOperation: " +  $filter('json')(productFactAmountOperation, 4));

        $http.post('product/editByFact', productFactAmountOperation).success(function (productResponse) {
            $scope.serverError = '';
            $scope.editByFact.product.factAmount = productResponse.factAmount;
        }).error(function () {
            $scope.serverError = "Ошибка при редактировании по факту";
        });
    };

    $scope.accordion = {
        closeOthers: true,
        first: {
            isOpen: false,
            isDisabled: false
        },
        second: {
            isOpen: false,
            isDisabled: false
        },
        third: {
            isOpen: false,
            isDisabled: false
        },
        fourth: {
            isOpen: false,
            isDisabled: false
        }
    };


    $scope.addRevenueOperation = {
        dayId: '',
        revenue: ''
    };

    $scope.addRevenue = function () {
        if(!revAndSpendService.validateAddRevenueForm($scope.addRevenueOperation)) {
            return false;
        }

        console.log("Add revenue: " + $filter('json')($scope.addRevenueOperation, 4));

        $http.post('day/add-revenue', $scope.addRevenueOperation).success(function (addRevenueOperationResp) {
            $scope.day.revenue = addRevenueOperationResp.revenue;
            $scope.addRevenueOperation.revenue = '';
        }).error(function () {
            $scope.serverError = "Не удалось сохранить выручку";
        });
    };


    $scope.dateForView = {
        date: ''
    }

      $scope.today = function() {
        $scope.dateForView.date = new Date();
      };

      $scope.today();

      $scope.clear = function () {
        $scope.dt = null;
      };


      $scope.toggleMin = function() {
        //$scope.minDate = $scope.minDate ? null : new Date();
        $scope.minDate = new Date(2015, 7, 1);
      };
      $scope.toggleMin();

      $scope.open = function($event) {
        $scope.status.opened = true;
      };

      $scope.dateOptions = {
        formatYear: 'yy',
        startingDay: 1
      };

      $scope.formats = ['dd-MM-yyyy', 'dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
      $scope.format = $scope.formats[0];

      $scope.status = {
        opened: false
      };

      var tomorrow = new Date();
      tomorrow.setDate(tomorrow.getDate() + 1);
      var afterTomorrow = new Date();
      afterTomorrow.setDate(tomorrow.getDate() + 2);
      $scope.events =
        [
          {
            date: tomorrow,
            status: 'full'
          },
          {
            date: afterTomorrow,
            status: 'partially'
          }
        ];

      $scope.getDayClass = function(date, mode) {
        if (mode === 'day') {
          var dayToCheck = new Date(date).setHours(0,0,0,0);

          for (var i=0;i<$scope.events.length;i++){
            var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

            if (dayToCheck === currentDay) {
              return $scope.events[i].status;
            }
          }
        }

        return '';
      };

    $scope.viewByDate = function () {
    var month = $scope.dateForView.date.getMonth() + 1;
    month = month < 10 ? '0' + month : month;
    var dateForView = $scope.dateForView.date.getDate() + '-' + month + '-' + $scope.dateForView.date.getFullYear();

    $http({
            method: 'POST',
            url: 'day/by-date',
            data: "date=" + dateForView,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (data) {
                          $scope.day = data;
                          $scope.addRevenueOperation.dayId = data.id;
                          $scope.revenueToday = data.revenue;
                          $scope.products = data.products; //todo: maybe use day here
                          $scope.displayableDate = new Date($scope.day.date).toLocaleString('ru', dateFormatOptions);
                      }).error(function () {
                          $scope.serverError = 'Ошибка. Не получилось подгрузить продукты';
                      });;
    };

    $scope.cookedAmountOperation = {
        date: '',
        dayId: '',
        amount: ''
    };

    $scope.addCookedAmount = function () {
        if (dayService.addCookedAmountFormNotValid($scope.cookedAmountOperation)) {
            return false;
        }

        $scope.cookedAmountOperation.date = $scope.day.date;
        $scope.cookedAmountOperation.dayId = $scope.day.id;

        console.log("Cooked amount operation: " + $filter('json')($scope.cookedAmountOperation, 4));

        $http.post('day/add-cooked-amount', $scope.cookedAmountOperation).success(function (cookedAmountOperationResp) {
            $scope.day.cookedAmount = cookedAmountOperationResp.amount;
            $scope.cookedAmountOperation.amount = '';
            $scope.successMessage = 'Успех. Произошел перерасчет. Перезагрузите страничку.'
        }).error(function () {
            $scope.serverError = "Не удалось добавить количество приготовленных штук";
        });
    };



});