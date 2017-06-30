<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;" charset="utf-8">
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id"
	content="${abc.getGoogleClientId()}">

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<script src="https://apis.google.com/js/platform.js?onload=renderButton"async defer></script>
<jsp:useBean id="abc" class="_00_bean.Global" scope="application" />
<title>OAuth example</title>
</head>
<script>
	function onSignIn(googleUser) {
		var profile = googleUser.getBasicProfile();
		console.log('ID: ' + profile.getId());
		console.log('Name: ' + profile.getName());
		console.log('Image URL: ' + profile.getImageUrl());
		console.log('Email: ' + profile.getEmail());
		console.log('id_token: ' + googleUser.getAuthResponse().id_token);
		var redirectUrl = 'login';
		var form = $('<form action="' + redirectUrl + '" method="post">'
				+ '<input type="hidden" name="id_token" value="'
				+ googleUser.getAuthResponse().id_token + '" />' + '</form>');
		$('body').append(form);
		form.submit();
	}
	function renderButton() {
		gapi.signin2.render('btGoogle', {
			'scope' : 'profile email',
			'width' : 297,
			'height' : 46,
			'longtitle' : true,
			'theme' : 'dark',
			'onsuccess' : onSignIn
		});
	}
</script>
<body>
	<h2>OAuth example</h2>
	<br> DEF:
	<div class="g-signin2" data-onsuccess="onSignIn"></div>
	<hr>
	<br> renderButton()
	<div id="btGoogle" data-onsuccess="onSignIn"></div>
</body>
</html>