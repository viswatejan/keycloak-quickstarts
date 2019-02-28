<#import "/spring.ftl" as spring />
<#assign xhtmlCompliant = true in spring>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Photoz HTML5 Client</title>

    <!-- Load AngularJS -->
    <script src="<@spring.url '/lib/angular/angular.min.js'/>"></script>
    <script src="<@spring.url '/lib/angular/angular-resource.min.js'/>"></script>
    <script src="<@spring.url '/lib/angular/angular-route.min.js'/>"></script>
    <script src="<@spring.url '/lib/jwt-decode.min.js'/>"></script>

    <script src="${keycloak_auth_server_url}/js/keycloak.js"></script>
    <script src="${keycloak_auth_server_url}/js/keycloak-authz.js"></script>
    <script src="<@spring.url '/js/identity.js'/>" type="text/javascript"></script>
    <script src="<@spring.url '/js/app.js'/>" type="text/javascript"></script>
</head>

<body data-ng-controller="TokenCtrl">

<a href data-ng-click="showRpt()">Show Requesting Party Token </a> | <a href data-ng-click="showAccessToken()">Show Access Token </a> | <a href data-ng-click="requestEntitlements()">Request Entitlements</a> | <a href="" ng-click="Identity.account()">My Account</a> | <a href="" ng-click="Identity.logout()">Sign Out</a>

<div id="content-area" class="col-md-9" role="main">
    <div id="content" ng-view/>
</div>

<pre style="background-color: #ddd; border: 1px solid #ccc; padding: 10px;" id="output"></pre>

</body>
</html>
