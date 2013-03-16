<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="message" tagdir="/WEB-INF/tags/message"%>
<%@taglib prefix="cspage" tagdir="/WEB-INF/tags/page"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<cspage:defaultpage title="Home Page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/menu.css">
	<script src="${pageContext.request.contextPath}js/p/home.js" type="text/javascript"></script>
	<div class="infoDiv">
		<h2>Readme - a file that no one reads.</h2>
		<p>
			If you are here, then you have registered yourself and wish to make use of our
			application. We are thankful to you for providing us with this opportunity.
		</p>
		<h4>How do I begin?</h4>
		<p>
			Application is built into 4 basic parts
			<ol>
				<li>Header - the part that mentions the application name and a logout button</li>
				<li>Navigation Menu (on left) - the part that allows you to perform required
				tasks.</li>
				<li>Mid-Pane - the part contains this text that you are reading right now.</li>
				<li>Footer - the part that will contain some information about us.</li>
			</ol>
			You would mostly work on the Navigation menu (ofcourse for navigation) and the
			Mid-Pane. The mid pane will contain all the forms you need to fill, all the data
			that we have to show to you, and everything this application does.
			<br/><br/>
			Ideally you would begin by creating users. These are members of your group who would
			share common expenditure with you. You should create logins for each of your friend
			immediately as we will consider all of the created IDs when calculating share, 
			immaterial of when the user is created.
			<br/><br/>
			Next you would create items. These items are the items you usually purchase for
			common expenditure. Create as many items as you can. Even if you forget to create
			an item now, you can always come back and create it at later point in time.
			<br/><br/>
			Then you would start keying in your purchases. The purchases are items you have 
			purchased as a part of your common expenditure. To enter an item here for purchase
			you need to have it in the "items" section. Why do you need to do so? Because when
			we extend the functionality of our application, we can show you graphs and reports
			based on your purchases. These will help you understand your expenditure patterns.
			<br/><br/>
			And then you can view all your purchases. And on one fine day, you can calculate
			all that you have purchases. It will act as a marker in your purchases, and would
			be available to you in "Show Calculations" section. When you calculate, you come
			to know of the amount that is shared between all members of your group. At a later
			point in time, you could come back and view all information again.
			<br/>
			But yes, all the purchases will be removed once you calculate the purchases and a 
			new list of purchases will begin.
		</p>

		Please select a link from left to continue.
	</div>
</cspage:defaultpage>