  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  
  <div id="advertisementModal" class="modal fade" role="dialog">
  <div class="modal-dialog modal-dialog-centered my-modal rounded">

    <!-- Modal content-->
    <div class="modal-content rounded">
       <div class=" modal-headers border-0">
       <div class="row mx-auto mw-100">
       <p class="col-11 elss-ad-link mb-0 text-center pt-3 px-2 pr-0">Begin your New Year with <span class="word-highlighter">ELSS</span> investment for tax-free returns and save upto <span class="word-highlighter">Rs. 1.5 Lakhs*</span>.</p>
       <button type="button" class="col-1 close px-0 text-center mt-n4 mt-md-n2" data-dismiss="modal">&times;</button>
       </div>
      </div>
      
      
      <div class="modal-body px-0 py-0">
      <div class="row mx-auto mw-00">
     
      <a href="https://www.freemi.in/elss/elss-mutual-fund/" class="col-md-11  mx-auto">
       
        <img src="https://resources.freemi.in/loans/resources/images/elss/elss-final-banner.png" alt="ELSS Mutual Fund" class="elss-banner-ads img-fluid">
    
      </div>
       <div class="col-7 col-md-4 cust-advertise-btn text-center mx-auto p-2 rounded">
       <div class="rounded py-1" style="border:1px dashed orange;">Invest Now</div>
       </div>
       
       <div class="col-12 small-text text-center text-muted pt-3">*T&amp;C Applied</div>
         </a>
      </div>
    
    </div>

  </div>
</div>



<script defer="defer">
//Start
//sessionStorage.getItem('key');
if (sessionStorage.getItem("advertisement") !== 'true') {
// sessionStorage.setItem('key', 'value'); pair
sessionStorage.setItem("advertisement", "true");
// Calling the bootstrap modal
 if(window.location.href.indexOf("elss") > -1) {
		$('#advertisementModal').modal('hide');
}
else {
		setTimeout( function() { 
			$('#advertisementModal').modal('show'); 
		}, 10000);
	}
}

//End
</script>