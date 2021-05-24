	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">
	
	  
	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
	
		<h1>PUBHUB <small>Book Tags Library</small></h1>
		<hr class="book-primary">

		<!-- NOTE: This form uses the enctype="multipart/form-data" attribute because it contains a file upload control (<input type="file" ... />).
				To support this special enctype, the PublishBookServlet also has the @MultiPartConfig annotation. You only need to use this
				enctype and its corresponding annotation if you need to use a file upload control. Do not use it otherwise. -->
				
		<form action="BookTags" method="post" class="form-horizontal">
		  <div class="form-group">
		    <label for="tag_name" class="col-sm-4 control-label">Tag to Add : </label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="tag_name" name="tag_name" placeholder="Tag" required="required" />
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="isbn" class="col-sm-4 control-label">ISBN : </label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="isbn" name="isbn" placeholder="ISBN" required="required" />
		    </div>
		  </div>
		   <input type="hidden" id="action" name="action" value="add">
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <button type="submit" class="btn btn-info">Add Tag</button>
		    </div>
		  </div>
		</form>	
		<br><BR>
		<form action="BookTags" method="post" class="form-horizontal">
		  <div class="form-group">
		    <label for="tag_name" class="col-sm-4 control-label">Tag to Remove : </label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="tag_name" name="tag_name" placeholder="Tag" required="required" />
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="isbn" class="col-sm-4 control-label">ISBN : </label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="isbn" name="isbn" placeholder="ISBN" required="required" />
		    </div>
		  </div>
		   <input type="hidden" id="action" name="action" value="remove">
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <button type="submit" class="btn btn-info">Remove Tag</button>
		    </div>
		  </div>
		</form>	

	  </div>
	</header>



	<!-- Footer -->
	<jsp:include page="footer.jsp" />
	