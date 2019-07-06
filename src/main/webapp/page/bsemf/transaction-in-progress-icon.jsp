<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="display_progress" class="progress_tag" style="display: none;">
	<h5>Please wait while we process your request...</h5>
	<div style="text-align: center;">
		<img src="<c:url value="${contextPath}/resources/images/invest/transacting.svg"/>"class="img-fluid" style=" height: 8rem;">
	</div>
	<h6>Do not Refresh the page</h6>
</div>