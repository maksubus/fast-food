'use strict';

fastFoodApp.service("revAndSpendService", function ($http, $filter) {

    this.validateAddRevenueForm = function (addRevenueOperation) {
        return !(addRevenueOperation.revenue == ''
        || addRevenueOperation.revenue == 0
        || addRevenueOperation.revenue == undefined);
    }
});