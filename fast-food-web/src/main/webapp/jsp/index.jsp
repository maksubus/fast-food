<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--
  User: maks
  Date: 19.07.15
--%>

<c:set var="context" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html ng-app="fastFoodApp">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="img/favicon.ico">

    <title>FastFood</title>

    <link rel="stylesheet" href="${context}/bootstrap/css/bootstrap.css">
    <%--<link rel="stylesheet" href="${context}/css/style.css">--%>
    <%--<script src="${context}/js/lib/jquery-1.9.1.js"></script>--%>
    <script src="${context}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${context}/lib/angular.min.js"></script>
    <script src="${context}/lib/angular-animate.min.js"></script>
    <script src="${context}/lib/ui-bootstrap-tpls-0.13.2.min.js"></script>

    <script src="${context}/js/controllers.js"></script>
    <script src="${context}/js/service/dayService.js"></script>
    <script src="${context}/js/service/productService.js"></script>
    <script src="${context}/js/service/revenueAndSpendingService.js"></script>
</head>
<body>

<div class="container-fluid" ng-controller="ProductController">
    <!-- ERROR MESSAGE BLOCK -->
    <div class="row" ng-show="serverError">
        <div class="col-sm-12">
            <div class="alert alert-danger" role="alert">
                {{serverError}}
            </div>
        </div>
    </div>

    <!-- SUCCESS MESSAGE BLOCK -->
    <div class="row" ng-show="successMessage">
            <div class="col-sm-12">
                <div class="alert alert-success" role="alert">
                    {{successMessage}}
                </div>
            </div>
        </div>

    <div class="row">

        <!-- START OF LEFT COLUMN -->
        <div class="col-xs-12 col-sm-4 col-md-5 col-lg-3">
            <accordion close-others="accordion.closeOthers">

                <!-- START OF PRODUCT CRUD OPERATION PANEL -->
                <accordion-group heading="1. Создание/Редактирование/Удаление продуктов"
                                 is-open="accordion.first.isOpen" is-disabled="accordion.first.isDisabled">
                    <form name="productOperationForm" class="form-horizontal">
                        <div class="form-group">
                            <label for="operation" class="col-sm-4 control-label">Операция</label>

                            <div class="col-sm-8">
                                <select id="operation" class="form-control input-sm" name="operation"
                                        ng-model="productCrudOperation.operation"
                                        ng-change="changeCrudOperation()">
                                    <option value="">Выбрать операцию ...</option>
                                    <option value="create">Создать</option>
                                    <option value="edit">Редактировать</option>
                                    <option value="delete">Удалить</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group" ng-show="productCrudOperation.operation == 'create'">
                            <label for="newProductName" class="col-sm-4 control-label">Название</label>

                            <div class="col-sm-8">
                                <input type="text" id="newProductName" class="form-control input-sm"
                                       name="newProductName" ng-model="productCrudOperation.newProductName"
                                       ng-minlength="2" ng-maxlength="50"
                                       ng-pattern="/^[\w\-\s\u0410-\u044F\u0401\u0451]+$/"
                                       required>

                                <p class="bg-danger"
                                   ng-show="productOperationForm.newProductName.$error.minlength">
                                    Минимальная длина 2 символа</p>

                                <p class="bg-danger"
                                   ng-show="productOperationForm.newProductName.$error.maxlength">
                                    Максимальная длина 50 символов</p>

                                <p class="bg-danger"
                                   ng-show="productOperationForm.newProductName.$error.pattern">Разрешены
                                    буквы а-z,A-Z,а-я,А-Я, цифры, дефис, нижнее подчеркивание</p>

                                <p class="bg-danger"
                                   ng-show="productOperationForm.newProductName.$error.required">Поле
                                    обязательно для заполнения</p>
                            </div>
                        </div>

                        <div class="form-group"
                             ng-show="productCrudOperation.operation == 'edit' || productCrudOperation.operation == 'delete'">
                            <label for="productId" class="col-sm-4 control-label">Продукт</label>

                            <div class="col-sm-8">
                                <select id="productId" class="form-control input-sm"
                                        name="editedProductId" ng-model="productCrudOperation.editedProductId" required
                                        ng-options="product.id as product.name for product in products">
                                    <option value="">Выбрать продукт ...</option>
                                </select>

                                <p class="bg-danger"
                                   ng-show="productOperationForm.editedProductId.$error.required">Поле
                                    обязательно для заполнения</p>
                            </div>
                        </div>

                        <div class="form-group" ng-show="productCrudOperation.operation == 'edit'">
                            <label for="newNameForEdit" class="col-sm-4 control-label">Новое
                                название</label>

                            <div class="col-sm-8">
                                <input type="text" id="newNameForEdit" class="form-control input-sm"
                                       name="newNameForEdit" ng-model="productCrudOperation.newProductName"
                                       ng-minlength="2" ng-maxlength="50"
                                       ng-pattern="/^[\w\-\s\u0410-\u044F\u0401\u0451]+$/"
                                       required>

                                <p class="bg-danger"
                                   ng-show="productOperationForm.newNameForEdit.$error.minlength">
                                    Минимальная длина 2 символа</p>

                                <p class="bg-danger"
                                   ng-show="productOperationForm.newNameForEdit.$error.maxlength">
                                    Максимальная длина 50 символов</p>

                                <p class="bg-danger"
                                   ng-show="productOperationForm.newNameForEdit.$error.pattern">
                                    Разрешены буквы а-z,A-Z,а-я,А-Я, цифры, дефис, нижнее подчеркивание</p>

                                <p class="bg-danger"
                                   ng-show="productOperationForm.newNameForEdit.$error.required">Поле
                                    обязательно для заполнения</p>
                            </div>
                        </div>

                        <div class="form-group" ng-show="productCrudOperation.operation == 'create'">
                            <label for="measureUnit" class="col-sm-4 control-label">Единица измерения</label>

                            <div class="col-sm-8">
                                <select id="measureUnit" class="form-control input-sm"
                                        name="measureUnit" ng-model="productCrudOperation.measureUnit" required
                                        ng-options="measureUnit.name as measureUnit.shortCode for measureUnit in measureUnits">
                                    <option value="">Выбрать единицу измерения ...</option>
                                </select>

                                <p class="bg-danger"
                                   ng-show="productOperationForm.measureUnit.$error.required">
                                    Поле обязательно для заполнения</p>
                            </div>
                        </div>

                        <button class="btn btn-sm btn-warning center-block"
                                ng-show="productCrudOperation.operation != '' && productCrudOperation.operation != undefined"
                                ng-click="operateCrudProduct(productCrudOperation.editedProductId)">
                            {{selectedCrudOperation}}
                        </button>
                    </form>
                </accordion-group>
                <!-- END OF PRODUCT CRUD OPERATION PANEL -->

                <!-- START OF PRODUCT ADD/SUBTRACT OPERATION PANEL-->
                <accordion-group heading="2. Добавить/Списать продукты" is-open="accordion.second.isOpen"
                                 is-disabled="accordion.second.isDisabled">
                    <form name="productAmountOperationForm" class="form-horizontal">
                        <div class="form-group">
                            <label for="amountOperation" class="col-sm-4 control-label">Операция</label>

                            <div class="col-sm-8">
                                <select id="amountOperation" name="amountOperation" class="form-control input-sm"
                                        ng-model="productAmountOperation.operation"
                                        ng-change="changeSelectedAmountOperation()">
                                    <option value="">Выбрать операцию ...</option>
                                    <option value="add">Добавить</option>
                                    <!--todo: fix here to bee from dynamic options -->
                                    <option value="writeOff">Списать</option>
                                </select>

                                <p class="bg-danger"
                                   ng-show="productAmountOperationForm.amountOperation.$error.required">
                                    Поле обязательно для заполнения</p>
                            </div>
                        </div>

                        <div ng-show="productAmountOperation.operation == 'add' || productAmountOperation.operation == 'writeOff'">
                            <div class="form-group">
                                <label for="amountEditedProductId"
                                       class="col-sm-4 control-label">Продукт</label>

                                <div class="col-sm-8">
                                    <select id="amountEditedProductId" name="amountEditedProductId"
                                            class="form-control input-sm"
                                            ng-model="productAmountOperation.dayProductId" required
                                            ng-options="product.dayProductId as product.name for product in products">
                                        <option value="">Выбрать продукт ...</option>
                                    </select>

                                    <p class="bg-danger"
                                       ng-show="productAmountOperationForm.amountEditedProductId.$error.required">
                                        Поле обязательно для заполнения</p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="productAmount" class="col-sm-4 control-label">
                                    Количество</label>

                                <div class="col-sm-8">
                                    <input type="number" id="productAmount" name="productAmount"
                                           class="form-control input-sm"
                                           ng-model="productAmountOperation.amount" min="0" required>

                                    <p class="bg-danger"
                                       ng-show="productAmountOperationForm.productAmount.$error.number">
                                        Разрешены цифры, точка</p>

                                    <p class="bg-danger"
                                       ng-show="productAmountOperationForm.productAmount.$error.required">
                                        Поле обязательно для заполнения</p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="amountOperationSum" class="col-sm-4 control-label">
                                    Сумма</label>

                                <div class="col-sm-8">
                                    <input type="number" id="amountOperationSum" name="amountOperationSum"
                                           class="form-control input-sm"
                                           ng-model="productAmountOperation.sum">

                                    <p class="bg-danger"
                                       ng-show="productAmountOperationForm.amountOperationSum.$error.number">
                                        Разрешены цифры, точка</p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="amountEditDate" class="col-sm-4 control-label">
                                    Дата</label>

                                <div class="col-sm-8">
                                    <input type="date" id="amountEditDate" name="amountEditDate"
                                           ng-model="productAmountOperation.date" required/>

                                    <p class="bg-danger"
                                       ng-show="productAmountOperationForm.amountEditDate.$error.required">
                                        Поле обязательно для заполнения</p>

                                    <p class="bg-danger"
                                       ng-show="productAmountOperationForm.amountEditDate.$error.date">
                                        Неправильная дата</p>

                                    <p class="bg-danger"
                                       ng-show="checkIfMaxAllowedDateExceed()">
                                        Дата не может быть в будущем</p>

                                    <p class="bg-danger"
                                       ng-show="checkIfMinAllowedDateExceed()">
                                        Дата не может быть больше чем месяц назад</p>


                                </div>
                            </div>

                            <button class="btn btn-sm btn-warning center-block" ng-click="operateProductAmount()">
                                {{selectedAmountOperation}}
                            </button>
                        </div>
                    </form>
                </accordion-group>
                <!-- END OF PRODUCT ADD/SUBTRACT OPERATION PANEL -->

                <!-- START OF ADDING REVENUE/EXPENSES PANEL -->
                <accordion-group heading="3. Ввести выручку/расходы" is-open="accordion.third.isOpen"
                                 is-disabled="accordion.third.isDisabled">
                    <form name="addRevenueForm" class="form-horizontal">
                        <div class="form-group">
                            <label for="revenue" class="col-sm-4 control-label">
                                Сумма</label>

                            <div class="col-sm-8">
                                <input type="number" id="revenue" name="revenue"
                                       class="form-control input-sm"
                                       ng-model="addRevenueOperation.revenue">

                                <p class="bg-danger"
                                   ng-show="addRevenueForm.revenue.$error.number">
                                    Разрешены цифры, точка</p>
                            </div>
                        </div>
                        <button class="btn btn-sm btn-warning center-block" ng-click="addRevenue()">
                            Сохранить
                        </button>
                    </form>
                </accordion-group>
                <!-- END OF ADDING REVENUE/EXPENSES PANEL -->

                <!-- START OF DATEPICKER PANEL -->
                <accordion-group heading="4. Выбрать дату просмотра" is-open="accordion.fourth.isOpen"
                                                 is-disabled="accordion.fourth.isDisabled">
                    <form name="addRevenueForm" class="form-horizontal">
                        <div class="form-group">
                            <label for="revenue" class="col-sm-4 control-label">
                                                Дата</label>

                            <div class="col-sm-8">
                                <p class="input-group">
                                    <input type="text" class="form-control" datepicker-popup="{{format}}"
                                            ng-model="dateForView.date" is-open="status.opened"
                                            min-date="minDate" max-date="'2020-06-22'"
                                            datepicker-options="dateOptions"
                                            ng-required="true" close-text="Close" />
                                    <span class="input-group-btn">
                                        <button type="button" class="btn btn-default" ng-click="open($event)">
                                            <i class="glyphicon glyphicon-calendar"></i>
                                        </button>
                                    </span>
                                </p>
                            </div>
                        </div>
                        <button class="btn btn-sm btn-warning center-block" ng-click="viewByDate()">
                            Показать
                        </button>
                    </form>
                </accordion-group>
                <!-- END OF ADDING DATEPICKER PANEL -->

                <!-- START OF ADDING AMOUNT OF COOKED ITEMS PANEL -->
                <accordion-group heading="5. Ввести количество приготовленых штук" is-open="accordion.fifth.isOpen"
                                 is-disabled="accordion.fifth.isDisabled">
                    <form name="addCookedAmountForm" class="form-horizontal">
                        <div class="form-group">
                            <label for="addCookedAmount" class="col-sm-4 control-label">
                                Количество</label>

                            <div class="col-sm-8">
                                <input type="number" id="addCookedAmount" name="amount"
                                       class="form-control input-sm"
                                       ng-model="cookedAmountOperation.amount">

                                <p class="bg-danger"
                                   ng-show="addCookedAmountForm.amount.$error.number">
                                    Разрешены цифры, точка</p>
                            </div>
                        </div>
                        <button class="btn btn-sm btn-warning center-block" ng-click="addCookedAmount()">
                            Добавить
                        </button>
                    </form>
                </accordion-group>
                <!-- END OF ADDING AMOUNT OF COOKED ITEMS PANEL -->

            </accordion>
        </div>
        <!-- END OF LEFT COLUMN -->

        <!-- START OF MIDDLE COLUMN -->
        <div class="col-xs-12 col-sm-8 col-md-7 col-lg-5">

            <p class="bg-primary text-center">{{displayableDate}}</p>

            <!-- START OF CURRENT STATE PANEL -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">6. Данные по продуктам</h3>
                </div>

                <table class="table table-bordered table-hover table-striped">
                    <tr>
                        <td>Название</td>
                        <td>Расчетное количество</td>
                        <td colspan="2">По факту</td>
                        <td>Добавлено</td>
                        <td>Списано</td>
                        <td>Итого</td>
                    </tr>

                    <tr ng-repeat="product in products | orderBy: 'name'">
                        <td>{{product.name}}</td>
                        <td>{{product.calcAmount}} {{product.measureUnit.shortCode}}</td>
                        <td>
                            {{product.factAmount}} {{product.measureUnit.shortCode}}

                        </td>
                        <td>
                            <button popover-template="editByFactPopover.templateUrl"
                                    popover-title="{{editByFactPopover.title}}"
                                    popover-placement="bottom"
                                    ng-click="initByFactPopover(product)"
                                    class="btn btn-default">
                                <img src="${context}/img/edit-icon.png"/>
                            </button>
                        </td>
                        <td>
                            {{product.addedAmount}} {{product.measureUnit.shortCode}}
                        </td>
                        <td>
                            {{product.subtrAmount}} {{product.measureUnit.shortCode}}
                        </td>
                        <td>
                            {{product.factAmount + product.addedAmount - product.subtrAmount}}
                        </td>

                    </tr>
                </table>
            </div>
            <!-- END OF CURRENT STATE PANEL -->

            <!-- START OF EDIT BY FACT POPOVER -->
            <script type="text/ng-template" id="editByFactPopoverTemplate.html">
                <form name="productByFactForm">
                    <div class="form-group">
                        <label for="amountByFact">Количество</label>
                        <input type="text" ng-model="editByFact.amount" class="form-control" id="amountByFact">
                    </div>
                    <button class="btn btn-sm btn-warning center-block" ng-click="operateProductFactAmount()">
                        Submit
                    </button>
                </form>
            </script>
            <!-- END OF EDIT BY FACT POPOVER -->

        </div>
        <!-- END OF MIDDLE COLUMN -->

        <!-- START OF RIGHT COLUMN -->
        <div class="col-xs-12 col-sm-12 col-md-5 col-lg-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Ещё данные</h3>
                </div>

                <table class="table table-bordered table-hover table-striped">
                <tr>
                    <td>Выручка</td>
                    <td>Расходы</td>
                    <td>Приготовлено штук</td>
                </tr>
                <tr>
                    <td>{{day.revenue}}</td>
                    <td>{{day.expense}}</td>
                    <td>{{day.cookedAmount}}</td>
                </tr>
                </table>
            </div>
        </div>
        <!-- END OF RIGHT COLUMN -->

    </div>
</div>

</body>
</html>
