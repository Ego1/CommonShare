<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="cs" tagdir="/WEB-INF/tags/message"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<cspage:fullpage title="Login">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/p/login.css"/>
	<script src="${pageContext.request.contextPath}js/common/login.js" type="text/javascript"></script>
	<script type="text/javascript">
		var ERROR_MISSING_ID_AND_PASSWORD = "Please enter both user id and password.";
	</script>
	<div class="loginDiv">
		<form action="${pageContext.request.contextPath}login.action" id='loginForm' method="POST">
			<div class="formdiv">
				<cs:messageDisplay/>
				<div class="formitem">
					<label for="loginName">Login Name</label>
					<input type="text" id="loginName" name="login.loginName"/>
				</div>
				<div class="formitem">
					<label for="password">Password</label>
					<input type="password" id="password" name="login.password"/>
				</div>
				<button id="submit" class="submitbutton">Login</button>				
				<span>
					<a href="/showGroupRegistrationPage.action">Register yourself</a>
				</span>					
			</div>
		</form>
	</div>
	<div class="infoDiv">
			<h3>We heartily welcome you to Common Share management software.</h3>
			<h4>Why is it so dark in here?</h4>
			<p>
				When we were in college, we came across <a href="http://blackle.com/">black google</a>.
				The concept blew our minds off. We were amazed. And now when we really think of it - 
				it sounds more true. As a software engineer, I spend more than 15 hours a day. And
				this means a lot of energy.<br/>
				So we thought, why not use this as a medium to help our planet a bit. We know its not
				enough, but at-least it is a step.<br/>
				And we encourage all of you to use <a href="http://blackle.com/">black google</a> for
				your search instead of "google.com". This is a "google.com", just black in colour.
			</p>
			<h4>What is this?</h4>			
			<p>
				We have create an application to help you manage your common shares.
				We live with out friends in a rent out house. But we share our expenditures.
				Yes, we can maintain a notebook and perform calculations. Or we could use
				google docs to manage and maintain the share. But all these hacks and time
				consuming. There is a need to maintain a common expenditure point. And we came
				up with our solution.
				<br/>
				And we wish it helps you the way it helps us.
				<br/>
				The use of application is not limited to friends staying together. It could be
				used by a group of people sharing their expenditures.
			</p>
			<h4>Why is it so slow? And why such a strange URL?</h4>
			<p>
				We have hosted this application on a cloud. And we haven't invested even a
				single paise in it. We have built it using our blood and sweat *pun intended*.
				And hence it is slow as we haven't got much processing power.
				<br/>
				And we love this URL. Yes, it is strange. But it is funny.<br/>
				But yes, we will soon purchase a domain if you find this application useful.
			</p>
			<h4>Which version is this?</h4>
			<p>
				This is a beta version of application. Yes, we have a long way to go to come up
				with a full fledged working application. And this is not possible without your
				co-operation.
				<br/>
				<ul>
					<li>We need suggestions for new functionalities.</li>
					<li>We need your help in bug finding.</li>
					<li>We need you to use it (It encourages us).</li>
				</ul>
				We will keep your data safe and it will be available to you when the new 
				version comes up. Don't worry!
			</p>
			<h4>How can I reach you?</h4>
			<p>
				You can contact us at harsh.vardhan.singh@outlook.com.<br/>
				This is a temporary arrangement till we come across a better approach.
			</p>
		</div>
</cspage:fullpage>