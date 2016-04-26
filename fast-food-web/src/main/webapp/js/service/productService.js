'use strict';

fastFoodApp.service("productService", function () {

    this.validateCreationForm = function (productCrudOperation) {
        return  !(productCrudOperation.newProductName == ''
            || productCrudOperation.newProductName == undefined
            || productCrudOperation.measureUnit == ''
            || productCrudOperation.measureUnit == undefined)            
    };

    this.validateEditForm = function (productCrudOperation) {
        return !(productCrudOperation.newProductName == ''
            || productCrudOperation.newProductName == undefined
            || productCrudOperation.editedProductId == 0
            || productCrudOperation.editedProductId == ''
            || productCrudOperation.editedProductId == undefined)
    };

    this.validateDeleteForm = function (productCrudOperation) {
        return !(productCrudOperation.editedProductId == 0
            || productCrudOperation.editedProductId == ''
            || productCrudOperation.editedProductId == undefined)
    };

});