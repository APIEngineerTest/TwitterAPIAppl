<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Twitter API implementation</title>
</head>

<body onload="hideDivs()">
<form name="getData">
	<h1>Twitter API implementation</h1>
	<h3>Please select any feature</h3>
	
	<input type="radio" name="selectFeature" value="feat1" id="feat1" onclick="showDivOnRadClick('feature1')">Get any number of posts of a User<br>
	<input type="radio" name="selectFeature" value="feat2" id="feat2" onclick="showDivOnRadClick('feature2')">Get shared users of any two Users<br>
	
	<div id="feature1" style="display:none">
	<label>User One</label><br>
	<input type="text" name="userOne" id="userOne"></input><br>
	<label>Number of Posts to be Retrieved</label><br>
	<input type="text" name="noPosts" id="noPosts"></input><br>
	</div>
	
	<div id="feature2" style="display:none">
	<label>User One</label><br>
	<input type="text" name="firstUser" id="firstUser"></input><br>
	<label>User Two</label><br>
	<input type="text" name="secondUser" id="secondUser"></input><br>
	</div>
	
	<div id="result">
		
	</div>
	
	
	<input type="button" value="Back" name="back" id="back" onclick="onBack()">
	<input type="button" value="Next" name="next" id="next" onclick="onNext()"><br>
	
	<input type="button" value="Submit"  onclick="onSubmit()">

	<input type="hidden" id="trackPgNo" name="trackPgNo">
	<input type="hidden" id="totalPages" name="totalPages">
	<input type="hidden" id="resultArr" name="resultArr">
	
</form>
</body>

<script>

var contextPath = '<%=request.getContextPath()%>';

function onSubmit(){
	
	var url;
	var userOne;
	var noPosts;
	
	if(validatePage()){
	
		enableBckNxtBtns();
		
		var feature; 
		if(document.getElementById("feat1").checked){
			
			feature = document.getElementById("feat1").value;
			userOne = document.getElementById("userOne").value;
			noPosts = document.getElementById("noPosts").value;
			
			url = contextPath+'/CommonServlet?feature='+feature+'&userOne='+userOne+'&noPosts='+noPosts+'&pageNo=0';
		}else if(document.getElementById("feat2").checked){
			
			feature = document.getElementById("feat2").value;
			firstUser = document.getElementById("firstUser").value;
			secondUser = document.getElementById("secondUser").value;
			
			url = contextPath+'/CommonServlet?feature='+feature+'&firstUser='+firstUser+'&secondUser='+secondUser+'&pageNo=0';
		}
		
		var xhr = new XMLHttpRequest();
	    xhr.onreadystatechange = function() {
	    	if (xhr.readyState == XMLHttpRequest.DONE ) {
	            if (xhr.status == 200) {
	               document.getElementById("resultArr").value = xhr.responseText;
	               splitData();
	            }
	            else if (xhr.status == 400) {
	               alert('There was an error 400');
	            }
	            else {
	                alert('something else other than 200 was returned');
	            }
	         }
	    }
	    xhr.open('GET', url, true);
	    xhr.send(null);
	}
    
}

function onNext(){
	var pageNo = document.getElementById("trackPgNo").value;
		pageNo = Number(pageNo)+1;
		onBackNextClick(pageNo);
}
function onBack(){
	var pageNo = document.getElementById("trackPgNo").value;
	pageNo = Number(pageNo)-1;
	onBackNextClick(pageNo)
}

function onBackNextClick(pageNo){
	
	var url = contextPath+'/CommonServlet?feature=pagination&pageNo='+pageNo ;
	var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
    	if (xhr.readyState == XMLHttpRequest.DONE ) {
            if (xhr.status == 200) {
               document.getElementById("resultArr").value = xhr.responseText;
               splitData();
            }
            else if (xhr.status == 400) {
               alert('There was an error 400');
            }
            else {
                alert('something else other than 200 was returned');
            }
         }else{
         }
    }
    xhr.open('GET', url, true);
    xhr.send();
}


function hideDivs(){
	
	var backBtn = document.getElementById("back");
	var nextBtn = document.getElementById("next");
	
	backBtn.style.visibility = 'hidden';
	nextBtn.style.visibility = 'hidden';
}

function enableBckNxtBtns(){
	var backBtn = document.getElementById("back");
	var nextBtn = document.getElementById("next");
	
	backBtn.style.visibility = 'visible';
	nextBtn.style.visibility = 'visible';
	backBtn.disabled = true;
	//nextBtn.disabled = true;
}

function splitData(){
	var arrData = document.getElementById("resultArr").value;
	var arr;
	arr = arrData.split("~");
	var firstPageNo = arr[0];
	var totalPages = arr[1];
	var tempVar = "";
	document.getElementById("trackPgNo").value = firstPageNo;
	document.getElementById("totalPages").value = totalPages;
	
	for(var i=2; i<arr.length-1;i++){
		tempVar += '<div>'+arr[i]+'</div>'
	}
	document.getElementById("result").innerHTML = tempVar;
	showButtons();
	
}

function showButtons(){
	var pageNo = document.getElementById("trackPgNo").value ;
	var totalPages = document.getElementById("totalPages").value;
	
	var element3 = document.getElementById("back");
	var element4 = document.getElementById("next");
	
	if(pageNo==0){
		element3.disabled = true;
	}
	if(pageNo>0){
		element3.disabled = false;
	}
	if(totalPages>1){
			element4.disabled = false;
	}
	if(totalPages==1 || pageNo==totalPages-1){
		element4.disabled = true;
	}
	
}

function showDivOnRadClick(radioBtnType){
	var radType = radioBtnType;
	document.getElementById("result").innerHTML = "";
	if(radType=='feature1'){
		document.getElementById("feature1").style.display = 'inline';
		document.getElementById("feature2").style.display = 'none';
	}else if(radType=='feature2'){
		document.getElementById("feature1").style.display = 'none';
		document.getElementById("feature2").style.display = 'inline';
	}
}

function validatePage(){
	
	var feat1 = document.getElementById("feat1").checked;
	var feat2 = document.getElementById("feat2").checked;
	
	var valid = true;
	
	if(!feat2 && !feat1){
		alert("Please select a radio button.");
		valid = false;
		return valid;
	}
	if(feat1){
		var userOne = document.getElementById("userOne").value;
		var noPosts = document.getElementById("noPosts").value;
		if(userOne.length==0){
			alert("Please enter the Twitter User Name.");
			valid = false;
			return valid;
		}
		if(noPosts.length==0){
			alert("Please enter the no of Posts you want to see.");
			valid = false;
			return valid;
		}
	}
	if(feat2){
		var firstUser = document.getElementById("firstUser").value;
		var secondUser = document.getElementById("secondUser").value;
		if(firstUser.length==0){
			alert("Please enter the First Twitter User Name.");
			valid = false;
			return valid;
		}
		if(secondUser.length==0){
			alert("Please enter the Second Twitter User Name.");
			valid = false;
			return valid;
		}
	}
	return valid;
}

</script>
</html>