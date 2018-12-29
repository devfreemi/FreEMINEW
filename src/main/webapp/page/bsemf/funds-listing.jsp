<div class="row" style="max-width: 100%; margin: auto;">
	<c:forEach items="${mffunds }" var="funds">
		<div class="col-md-2 col-lg-2" style="margin-bottom: 10px;">
			<div class="card" style="width: 100%;">
				<div class="card-body">
					<h5 class="card-title">${funds.fundName }</h5>
					<h6 class="card-subtitle mb-2 text-muted">High Risk</h6>
					<p class="card-text">Some quick example text to build on the
						card title and make up the bulk of the card's content.</p>
					<a href="#" class="card-link">Card link</a> <a href="#"
						class="card-link">Another link</a>
				</div>
			</div>
		</div>

	</c:forEach>
</div>