/**
 * 
 */
var app=angular.module("register",[]);
app.controller("registerController",function($scope,$http){
	
	$scope.registration={userId:-1,fname:"",lname:"",email:"",password:"",gender:"",address:"",phone:""}
	$scope.userList=[];
	$scope.editList=false;
	$scope.mainList=true;
	$scope.showUserList=true;
	$scope.showAdminList=false;
	
$scope.showAdminList1=function(){
	console.log("inside showAdminList");
	$scope.editList=false;
	$scope.mainList=false;
	$scope.showUserList=false;
	$scope.showAdminList=true;
	}
	
	
	$scope.userSubmit=function(){
		var method="";
		var url="";
		
		if($scope.registration.userId==-1){
			method="POST";
			url="create";
		}
		else{
			method="PUT";
		   url="editUser/"+$scope.registration.userId;
			console.log("$scope.registration.userId"+$scope.registration.userId);
		}
		
		$http({
			method:method,
			url:url,
			data:angular.toJson($scope.registration),
			header:{
				'Content-Type':'application/json'
			}
		}).then(_success,_error);
		
	}
	
	function _success(){
		$scope.getUserList();
		$scope.editList=false;
		$scope.mainList=true;
		clearForm();
		console.log("success");
	}
	function _error(){
		console.log("Error")
	}
	
	var onInit = function () {
		$scope.getUserList();
		$scope.editList=false;
		$scope.mainList=true;
	};
	angular.element(document).ready(onInit);
	
	function clearForm(){
		$scope.registration.userId=-1;
		$scope.registration.fname="";
		$scope.registration.lname="";
		$scope.registration.email="";
		$scope.registration.password="";
		$scope.registration.gender="";
		$scope.registration.address="";
		$scope.registration.phone="";
	}
	
$scope.getUserList=function(){
		$http({
			method:"GET",
			url:"userList"
		}).then(function successCallBack(response){
			$scope.userList=response.data;
			console.log("$scope.userList"+JSON.stringify(response.data));
		},_error);
	}
	
$scope.deleteUser=function(user,i){
	alert("Are You Sure You Want To Delete this Record?")
	console.log("inside delete"+user.userId);
	$http({
		method:"DELETE",
		url:"deleteUser/"+user.userId
	}).then(_success,_error);
	$scope.userList.splice(1,i)
}	
$scope.editUser=function(user){
	$scope.registration.userId=user.userId;
	$scope.registration.fname=user.fname;
	$scope.registration.lname=user.lname;
	$scope.registration.email=user.email;
	$scope.registration.password=user.password;
	$scope.registration.gender=user.gender;
	$scope.registration.address=user.address;
	$scope.registration.phone=user.phone;
	$scope.editList=true;
	$scope.mainList=false;
}
$scope.searchDataAtAdminSide=function(){
	var a=document.uform.search.value;
	
	$scope.registration.fname=a;
	
	console.log("in search"+JSON.stringify($scope.registration));
	
	if($scope.registration==null || $scope.registration==undefined || $scope.registration==""){
		$scope.getUserList();
	}
	else{
		$http({
			method:"POST",
			url:"multiSearch",
			data:angular.toJson($scope.registration)
		}).then(function SuccessCallBack(response){
			$scope.userList=response.data;
			console.log("$scope.userList"+JSON.stringify(response.data));
		},_error)
	}
}

});