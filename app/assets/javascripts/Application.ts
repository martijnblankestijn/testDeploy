/// <reference path='_all.ts' />

/**
 * The main TodoMVC app module.
 *
 * @type {angular.Module}
 */
module todos {
    'use strict';

    console.log("starting angular app")
    var todomvc = angular.module('todomvc', [])
        .controller('todoCtrl', TodoCtrl)
        .directive('todoBlur', todoBlur)
        .directive('todoFocus', todoFocus)
        .service('todoStorage', TodoStorage);
    console.log("angular app started")
}