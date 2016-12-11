<html>
<head>
    <!-- PatternFly core -->
    <link rel="stylesheet" href="bower_components/patternfly/dist/css/patternfly.min.css">
    <link rel="stylesheet" href="bower_components/patternfly/dist/css/patternfly-additions.min.css">

    <script src="bower_components/jquery/dist/jquery.min.js"></script>
    <script src="bower_components/patternfly/dist/js/patternfly.min.js"></script>

    <script src="bower_components/angular/angular.js"></script>
    <script src="bower_components/angular-ui-router/release/angular-ui-router.js"></script>

    <!-- Angular-PatternFly Styles -->
    <link rel="stylesheet" href="bower_components/angular-patternfly/dist/styles/angular-patternfly.min.css">

    <!-- Angular-Bootstrap -->
    <script src="bower_components/angular-bootstrap/ui-bootstrap.min.js"></script>
    <script src="bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>

    <!-- Angular-Sanitize -->
    <script src="bower_components/angular-sanitize/angular-sanitize.min.js"></script>

    <!-- Angular-PatternFly  -->
    <script src="bower_components/angular-patternfly/dist/angular-patternfly.min.js"></script>

    <!-- C3, D3 - Charting Libraries. Only required if you are using the 'patternfly.charts' module-->
    <script src="bower_components/c3/c3.min.js"></script>
    <script src="bower_components/d3/d3.min.js"></script>

    <!-- APP -->
    <script src="js/app.js"></script>
    <script src="js/controller/DemoCtrl.js"></script>
    <script src="js/controller/LoginValidationController.js"></script>

    <link rel="stylesheet" href="css/app.css">
    <link rel="import" href="pages/login.html">



</head>
<body ng-app="mainApp">
    <ui-view></ui-view>
</body>
</html>
