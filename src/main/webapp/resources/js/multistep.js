 //Initialize tooltips
 $('.nav-tabs > li a[title]').tooltip();

 //Wizard
 $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {

     var $target = $(e.target);

     if ($target.hasClass('disabled')) {
         return false;
     }
 });

 $(".next-step").click(function (e) {
	 
	 if($("#redeemamount").val()){
		 var $active = $('.wizard .nav-tabs .nav-item .active');
	     var $activeli = $active.parent("li");

	     $($activeli).next().find('a[data-toggle="tab"]').removeClass("disabled");
	     $($activeli).next().find('a[data-toggle="tab"]').click();
	     
	     $("#redeemamountconf").text($("#redeemamount").val());
	     
	 }else{
		 $("#mandateField").text("Enter amount");
	 }
 });



 $(".prev-step").click(function (e) {

     var $active = $('.wizard .nav-tabs .nav-item .active');
     var $activeli = $active.parent("li");

     $($activeli).prev().find('a[data-toggle="tab"]').removeClass("disabled");
     $($activeli).prev().find('a[data-toggle="tab"]').click();

 });

 
/* $(".confirm-tab").click(function (e) {
	 
 });
 */
 
 function confirmTab(){
	 console.log("Cliecked")
	 $("#mandateField").text("Fields are mandatory");
	/* var $active = $('.wizard .nav-tabs .nav-item .active');
     var $activeli = $active.parent("li");

     $($activeli).next().find('a[data-toggle="tab"]').removeClass("disabled");
     $($activeli).next().find('a[data-toggle="tab"]').click();*/
	 
	 return true;
	 
 }
 
function validatePurchaseAmount(){
//	console.log(parseFloat($("#availableFund").val()) + " ->  " + parseFloat($("#redeemamount").val()));
	 if(parseFloat($("#redeemamount").val()) > parseFloat($("#availableFund").val())){
		 $("#invalidamnt").text("Amount more than fund avaliable!");
		 $("#nextbutton").attr("disabled", "disabled");
	 }else{
		 $("#invalidamnt").text("");
		 $("#nextbutton").removeAttr('disabled');
	 }
 }
 
 
 
 