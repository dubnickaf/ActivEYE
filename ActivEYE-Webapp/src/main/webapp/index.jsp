<html>
<head>
    <link type="favicon" href="/images/favicon.ico" rel="icon" />
    <link rel="shortcut icon" type="image/ico" href="/images/favicon.ico"/>

    <!-- PatternFly core -->
    <link rel="stylesheet" href="bower_components/patternfly/dist/css/patternfly.min.css">
    <link rel="stylesheet" href="bower_components/patternfly/dist/css/patternfly-additions.min.css">

    <script src="bower_components/jquery/dist/jquery.min.js"></script>
    <script src="bower_components/patternfly/dist/js/patternfly.min.js"></script>
    <script src="bower_components/lodash/lodash.js"></script>

    <script src="bower_components/angular/angular.js"></script>
    <script src="bower_components/angular-ui-router/release/angular-ui-router.js"></script>
    <script src="bower_components/angular-resource/angular-resource.js"></script>

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
    <!-- Controllers -->
    <script src="js/app.js"></script>
    <script src="js/mediator/mediator.js"></script>
    <script src="js/config/config.js"></script>
    <script src="js/service/Services.js"></script>
    <script src="js/service/Session.js"></script>
    <script src="js/service/Router.js"></script>
    <script src="js/service/UserService.js"></script>
    <script src="js/service/GroupService.js"></script>
    <script src="js/service/RecordService.js"></script>
    <script src="js/service/SelectedUsersService.js"></script>
    <script src="js/controller/LoginController.js"></script>
    <script src="js/controller/LoginValidationController.js"></script>
    <script src="js/controller/UpdateProfileController.js"></script>
    <script src="js/controller/DashboardController.js"></script>
    <script src="js/controller/GroupsController.js"></script>
    <script src="js/controller/ActivitiesController.js"></script>
    <script src="js/controller/RecordsController.js"></script>
    <script src="js/controller/CreateRecordController.js"></script>
    <script src="js/patternfly/CreateGroupSelectController.js"></script>
    <script src="js/controller/CreateGroupController.js"></script>
    <script src="js/AppController.js"></script>
    <script src="js/controller/HomeController.js"></script>
    <link rel="stylesheet" href="css/app.css">

    <link rel="import" href="pages/home.html">
    <link rel="import" href="pages/login.html">
    <link rel="import" href="pages/dashboard.html">
    <link rel="import" href="pages/records.html">
    <link rel="import" href="pages/groups.html">



</head>
<body ng-app="mainApp">
    <ui-view></ui-view>
</body>
</html>
