'use strict';

fastFoodApp.service("dayService", function () {

    this.addCookedAmountFormNotValid = function (cookedAmountOperation) {
        return cookedAmountOperation.amount == 0
               || cookedAmountOperation.amount == ''
               || cookedAmountOperation.amount == undefined
    }
});