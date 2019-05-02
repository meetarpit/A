/**
 * 
 */
var app=angular.module("register",[]);
app.controller("regController",function($scope,$http){
	$scope.adminForm={roles:{id:-1,rolename:""},aid:-1,aemail:"",apass:"",gender:"",address:"",phone:""};
	$scope.adminList=[];
	$scope.editList=false;
	$scope.mainList=true;
	$scope.showUserList=true;
	$scope.showAdminList=false;
	

	getAllAdminList();
	
	
	
	
	$scope.insertAdmin=function(){
	
		var method="";
		var url="";
	
		if($scope.adminForm.aid==-1){
			method="POST";
			url="adminlogin";
		}
		else{
			method="PUT";
			url="editAdminDetails/"+$scope.adminForm.aid;
		}
		$http({
			method:method,
			url:url,
			data:angular.toJson($scope.adminForm),
			header:{
				'Content-Type':'application/json'
			}
		}).then(_sucess,_error);
		
	}
	function _sucess(){
		console.log("inserted");
		getAllAdminList();
		clearForm();
	}
	function _error(){
		console.log("Rejected")
	}
	
	$scope.editAdmin=function(admin){
		$scope.adminForm.aid=admin.aid;
		$scope.adminForm.aemail=admin.aemail;
		$scope.adminForm.apass=admin.apass;
		$scope.adminForm.gender=admin.gender;
		$scope.adminForm.address=admin.address;
		$scope.adminForm.phone=admin.phone;
		$scope.adminForm.roles.rolename=admin.roles.rolename;
	}
	
	function getAllAdminList(){
		$http({
			method:"GET",
			url:"adminList"			
		}).then(function sucessCallBack(response){
			$scope.adminList=response.data;
			console.log("$scope.adminList"+response.data);
		},_error)
	}
	
	function clearForm(){
		$scope.adminForm.aid=-1;
		$scope.adminForm.aemail="";
		$scope.adminForm.apass="";
		$scope.adminForm.gender="";
		$scope.adminForm.address="";
		$scope.adminForm.phone="";
		$scope.adminForm.roles.rolename="";
		
	}
	$scope.deleteAdmin=function(admin,index){
		alert("Are you sure,You want to delete this record");
		$http({
			method:"DELETE",
			url:"delete/"+admin.aid,
		}).then(_sucess,_error);
		$scope.adminList.splice(1,index);		
	}
	
	
	
});