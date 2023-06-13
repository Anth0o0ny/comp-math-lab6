<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>lab6</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/KaTeX/0.7.1/katex.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/KaTeX/0.7.1/katex.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        * {
            font-family: 'Aclonica', math !important;
            font-size: 18px;
        }

        .input.is-danger {
            border-color: red;
        }
    </style>
</head>

<style>
    body {
        background-color: lightgoldenrodyellow;
    }
</style>

<body>

<section class="section">

    <div class="columns is-centered is-vcentered has-text-centered">
        <div class="column is-4">

            <div class="box">
                <div class="py-2">
                    <label class="label" for="data">Введите исходные данные:</label>
                </div>
                <div id="data" class="control is-black has-text-dark is-medium">
                    <div class="box"><label id="x-0-label" class="label" for="x-0">x0:</label><input class="input" size="10" id="x-0"
                                                                                                     type="text"></div>
                    <div class="box"><label id="x-n-label" class="label" for="x-n">xn:</label><input class="input" size="10" id="x-n"
                                                                                                     type="text"></div>
                    <div class="box"><label id="y-x-0-label" class="label" for="y-x-0">y(x0):</label><input class="input" size="10"
                                                                                                            id="y-x-0" type="text">
                    </div>
                    <div class="box"><label id="h-label" class="label" for="h">h:</label><input class="input" size="10" id="h"
                                                                                                type="text"></div>
                    <div class="box"><label id="eps-label" class="label" for="eps">eps:</label><input class="input" size="10" id="eps"
                                                                                                      type="text"></div>
                </div>
            </div>

        </div>
        <div class="column is-4">

            <div class="box">

                <div class="py-2">
                    <label class="label" for="sde-div">Выберите ОДУ:</label>
                </div>

                <div id="sde-div" class="control box has-text-left">
                    <div class="py-3">
                        <label class="radio" style="min-height: 70px">
                            <input type="radio" id="lecture-sde" name="sde" checked>
                            <span id="sde-1-span" style="min-height: 60px" class="ml-5 tag is-large is-dark">Lecture SDE</span>
                        </label>
                    </div>
                    <div class="py-3" style="min-height: 70px">
                        <label class="radio">
                            <input type="radio" id="site-sde" name="sde">
                            <span id="sde-2-span" style="min-height: 60px" class="ml-5 tag is-large is-dark">Site SDE</span>
                        </label>
                    </div>
                    <div class="py-3" style="min-height: 70px">
                        <label class="radio">
                            <input type="radio" id="my-sde" name="sde">
                            <span id="sde-3-span" style="min-height: 60px" class="ml-5 tag is-large is-dark">My SDE</span>
                        </label>
                    </div>
                </div>

                <div id="submit-div">
                    <div><button id="submit-button" class="button is-dark">Решить задачу КОШИ!</button></div>
                </div>

                <div id="submit-helper" class="notification is-centered is-danger has-text-centered my-3" style="display: none">
                    <button id="notification-button" class="delete"></button>
                    <div id="conditions"></div>
                </div>

            </div>

        </div>
    </div>

</section>

<section class="section">
    <div class="columns is-centered is-vcentered has-text-centered">
        <div class="column is-4">
            <div class="field">
                <label class="label" for="result">Результат:</label>
                <div class="control">
                    <textarea id="result" class="textarea" rows="8" cols="10" readonly></textarea>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="section">
    <div class="columns is-vcentered is-centered has-text-centered box">
        <div id="eulerGraphicFrame" class="column is-9">
            <div>
                <span class="tag is-warning is-medium">График Эйлера</span>
            </div>
            <canvas id="eulerGraphic"></canvas>
        </div>
    </div>
</section>

<section class="section">
    <div class="columns is-vcentered is-centered has-text-centered box">
        <div id="rungeKuttaGraphicFrame" class="column is-9">
            <div>
                <span class="tag is-warning is-medium">График Рунге-Кутта</span>
            </div>
            <canvas id="rungeKuttaGraphic"></canvas>
        </div>
    </div>
</section>

<section class="section">
    <div class="columns is-vcentered is-centered has-text-centered box">
        <div id="adamsGraphicFrame" class="column is-9">
            <div>
                <span class="tag is-warning is-medium">График Адамса</span>
            </div>
            <canvas id="adamsGraphic"></canvas>
        </div>
    </div>
</section>

<section class="section">
    <div class="columns is-centered has-text-centered">
        <div class="column is-4">
            <div class="box resultTables">
                <div>
                    <span class="tag is-warning is-medium">Таблица Эйлера</span>
                </div>

                <div id="eulerTable">

                </div>
            </div>
        </div>
        <div class="column is-4">
            <div class="box resultTables">
                <div>
                    <span class="tag is-warning is-medium">Таблица Рунге-Кутта</span>
                </div>

                <div id="rungeKuttaTable">

                </div>
            </div>
        </div>
        <div class="column is-4">
            <div class="box resultTables">
                <div>
                    <span class="tag is-warning is-medium">Таблица Адамса</span>
                </div>
                <div id="adamsTable">

                </div>
            </div>
        </div>
    </div>
</section>


<script src="<c:url value="/js/formatter.js"/>"></script>
<script src="<c:url value="/js/dataListener.js"/>"></script>
<script src="<c:url value="/js/submitting.js"/>"></script>
</body>




