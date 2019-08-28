	var signapplicant="sign1";
	var signature1="";
	var signature2="";
	var sigImage = document.getElementById("sig-image");;
	function getmodal(applicant){
		/* clearCanvas(); */
		/* console.log("Modal requested- "+ applicant); */
		signapplicant = applicant;
		var dataUrl;
		if(signapplicant === 'sign1'){
		dataUrl = signature1;
		
		}else if(signapplicant === 'sign2'){
			dataUrl = signature2;
		
		}else{
			dataUrl = "/products/resources/images/invest/sign1.png";
			}

		/* console.log("dataurl length- "+ dataUrl.length) */
		if(dataUrl.length === 0){
			sigImage.setAttribute("src", "/products/resources/images/invest/sign1.png");
			$("#signid").text("Not set yet");
		}else{
			sigImage.setAttribute("src", dataUrl);
			
			if(signapplicant === 'sign1'){
				$("#signid").text("Applicant 1");
			}else if(signapplicant === 'sign2'){
				$("#signid").text("Applicant 2")
			}else{
				$("#signid").text("Invalid");
			}
	}
		$('#exampleModal1').modal('show');
		
		}

		(function() {
			window.requestAnimFrame = (function(callback) {
				return window.requestAnimationFrame
						|| window.webkitRequestAnimationFrame
						|| window.mozRequestAnimationFrame
						|| window.oRequestAnimationFrame
						|| window.msRequestAnimaitonFrame
						|| function(callback) {
							window.setTimeout(callback, 1000 / 60);
						};
			})();

			var canvas = document.getElementById("sig-canvas");
			var ctx = canvas.getContext("2d");
			ctx.strokeStyle = "#305ab3";
			ctx.lineWidth = 2;

			var drawing = false;
			var mousePos = {
				x : 0,
				y : 0
			};
			var lastPos = mousePos;

			canvas.addEventListener("mousedown", function(e) {
				drawing = true;
				lastPos = getMousePos(canvas, e);
			}, false);

			canvas.addEventListener("mouseup", function(e) {
				drawing = false;
			}, false);

			canvas.addEventListener("mousemove", function(e) {
				mousePos = getMousePos(canvas, e);
			}, false);

			// Add touch event support for mobile
			canvas.addEventListener("touchstart", function(e) {

			}, false);

			canvas.addEventListener("touchmove", function(e) {
				var touch = e.touches[0];
				var me = new MouseEvent("mousemove", {
					clientX : touch.clientX,
					clientY : touch.clientY
				});
				canvas.dispatchEvent(me);
			}, false);

			canvas.addEventListener("touchstart", function(e) {
				mousePos = getTouchPos(canvas, e);
				var touch = e.touches[0];
				var me = new MouseEvent("mousedown", {
					clientX : touch.clientX,
					clientY : touch.clientY
				});
				canvas.dispatchEvent(me);
			}, false);

			canvas.addEventListener("touchend", function(e) {
				var me = new MouseEvent("mouseup", {});
				canvas.dispatchEvent(me);
			}, false);

			function getMousePos(canvasDom, mouseEvent) {
				var rect = canvasDom.getBoundingClientRect();
				return {
					x : mouseEvent.clientX - rect.left,
					y : mouseEvent.clientY - rect.top
				}
			}

			function getTouchPos(canvasDom, touchEvent) {
				var rect = canvasDom.getBoundingClientRect();
				return {
					x : touchEvent.touches[0].clientX - rect.left,
					y : touchEvent.touches[0].clientY - rect.top
				}
			}

			function renderCanvas() {
				if (drawing) {
					ctx.moveTo(lastPos.x, lastPos.y);
					ctx.lineTo(mousePos.x, mousePos.y);
					ctx.stroke();
					lastPos = mousePos;
				}
			}

			// Prevent scrolling when touching the canvas
			document.body.addEventListener("touchstart", function(e) {
				if (e.target == canvas) {
					e.preventDefault();
				}
			}, false);
			document.body.addEventListener("touchend", function(e) {
				if (e.target == canvas) {
					e.preventDefault();
				}
			}, false);
			document.body.addEventListener("touchmove", function(e) {
				if (e.target == canvas) {
					e.preventDefault();
				}
			}, false);

			(function drawLoop() {
				requestAnimFrame(drawLoop);
				renderCanvas();
			})();

			function clearCanvas() {
				canvas.width = canvas.width;
			}

			// Set up the UI
			var sigText = document.getElementById("sig-dataUrl");
			var sigImage = document.getElementById("sig-image");
			var clearBtn = document.getElementById("sig-clearBtn");
			/* var submitBtn = document.getElementById("sig-submitBtn"); */
			var submitBtn = document.getElementById("set-signature");
			clearBtn.addEventListener("click", function(e) {
				clearCanvas();
				sigText.innerHTML = "NA";
				sigImage.setAttribute("src",
						"/products/resources/images/invest/sign1.png");

				/* var aofformimg = document.getElementById("aofformsignature");
				aofformimg.setAttribute("src", ""); */
				$("#signature1").val("");
				ctx.strokeStyle = "#305ab3";
				ctx.lineWidth = 2;
			}, false);
			submitBtn.addEventListener("click", function(e) {
				var dataUrl = canvas.toDataURL();
				sigText.innerHTML = dataUrl;
				sigImage.setAttribute("src", dataUrl);

				if(signapplicant === 'sign1'){
				/* console.log("Set 1st applicant sign"); */
				signature1 = dataUrl;
				}else if(signapplicant === 'sign2'){
					/* console.log("Set 1st applicant sign"); */
					signature2 = dataUrl;
				}else{
					/* console.log("invialid signature no.") */
					}
				
				/* var aofformimg = document.getElementById("aofformsignature");
				aofformimg.setAttribute("src", dataUrl); */
				$("#signature1").val(dataUrl);
				/* submitSign(); */
				if(signapplicant === 'sign1'){
					$("#signid").text("Applicant 1");
				}else if(signapplicant === 'sign2'){
					$("#signid").text("Applicant 2")
				}else{
					$("#signid").text("Invalid");
				}

			}, false);

		})();

		window.onload = function() {
			/* var dwn = document.getElementById('btndownload'), canvas = document
					.getElementById('sig-canvas'), context =
			// Event handler for download
			dwn.onclick = function() {
				download(canvas, 'myimage.png');
			} */

		}

		function download(canvas, filename) {
			/// create an "off-screen" anchor tag
			var lnk = document.createElement('a'), e;

			/// the key here is to set the download attribute of the a tag
			lnk.download = filename;

			/// convert canvas content to data-uri for link. When download
			/// attribute is set the content pointed to by link will be
			/// pushed as "download" in HTML5 capable browsers
			lnk.href = canvas.toDataURL("image/png;base64");

			/// create a "fake" click-event to trigger the download
			if (document.createEvent) {
				e = document.createEvent("MouseEvents");
				e.initMouseEvent("click", true, true, window, 0, 0, 0, 0, 0,
						false, false, false, false, 0, null);

				lnk.dispatchEvent(e);
			} else if (lnk.fireEvent) {
				lnk.fireEvent("onclick");
			}
		}

		/* $("button").click(function(){ */
		function submitSign() {
			/* console.log("Signatuer: " + $("#sig-dataUrl").text());
		    console.log("Signatuer length: " + $("#sig-dataUrl").text().length); */

			if(signature1.length <=1594){
				$("#signmsg").text("Applicant 1 has not signed yet.");
				return false;
			}
			
			if(signature1 === signature2 ){
					$("#signmsg").text("Both applicant signature cannot be same!");
					return false;
				}
			$("#signmsg").text("");
			/* console.log("signature- "+ $("#sig-dataUrl").text()); */
			/* $
					.post(
							"/products/mutual-funds/uploadsign",

							{
								sign1 : $("#sig-dataUrl").text(),
								sign2 : ""
							},
							function(data, status) {
								console.log(data);
								$('#exampleModal1').modal('hide');
								if (data == 'SUCCESS') {

									$("#signuploadstatus")
											.text(
													"Your AOF form is signed and ready for upload.");
									$("#aofuploadbtn").removeAttr('hidden');
									move(75);

								}
								if (data == 'REQUEST_DENIED') {
									$('#exampleModal1').modal('hide');
									$("#signuploadstatus")
											.text(
													"Session lost. Kindly login to complete registration");

								}

							})
					.fail(
							function(response) {
								$('#exampleModal1').modal('hide');
								$("#signuploadstatus")
										.text(
												"Failed to submit your signature. Please try again.");
							});
			
			 */
			
			// AJAX based
			var mobileData = { mobile : $("#mobdata").val() }
			
			request = $.ajax({
			url: "/products/mutual-funds/uploadsign",
			method: "POST",
			data: 
			{
				sign1 : signature1,
				sign2 : signature2,
				savesign: signapplicant,
				referrer: window.location.href
			},
			async: true,
			datatype: "json",
			beforeSend: function() {
				$("#signtxt").hide();
				$("#signingtxt").show();
				$("#sig-submitBtn").prop("disabled", true);
				
		    }
		});

		request.done(function(data) {
			console.log(data);
			$('#exampleModal1').modal('hide');
			if (data == 'SUCCESS') {

				$("#signuploadstatus").text("Your AOF form is signed and ready for upload.");
				$("#aofuploadbtn").removeAttr('hidden');
				$("#signuploadstatus").css("color", "green");	
				move(75);

			}
			else if (data == 'REQUEST_DENIED') {
				$('#exampleModal1').modal('hide');
				$("#signuploadstatus").text("Session lost. Kindly login to complete registration");
				$("#signuploadstatus").css("color", "red");	

			}
			else if (data == 'APP2_SIGN_REQUIRED') {
				$('#exampleModal1').modal('hide');
				$("#signuploadstatus").text("2nd applicant signature required for joint holder");
				$("#signuploadstatus").css("color", "red");	

			}
			else if (data == 'INTERNAL_ERROR') {
				$('#exampleModal1').modal('hide');
				$("#signuploadstatus").text("Failed to process your AOF form. Kindly contact Admin.");
				$("#signuploadstatus").css("color", "red");	

			}

			else if (data == 'BOTH_SIGN_SAME') {
				$('#exampleModal1').modal('hide');
				$("#signuploadstatus").text("Both applicant signature found to be same. Please set different signature.");
				$("#signuploadstatus").css("color", "red");	
			}else{
				$('#exampleModal1').modal('hide');
				$("#signuploadstatus").text(data);
				$("#signuploadstatus").css("color", "red");
			}

		});

		request.fail(function(jqXHR, textStatus) {
			$('#exampleModal1').modal('hide');
			$("#signuploadstatus").text("Failed to submit your signature. Please try again.");
		});
		
		request.always(function(msg){
			$("#signingtxt").hide();
			$("#signtxt").show();
			$("#sig-submitBtn").prop("disabled", false);
		});
			
			
			// ----------------------------------------
			
		}

		function initiateAOFUpload() {

			console.log("mobile- " + $("#mobdata").val());
			
			
			/* $
					.get(
							"/products/mutual-funds/uploadsignedaof",

							{
								mobile : $("#mobdata").val()
							},
							function(data, status) {
								console.log(data);
								if (data == 'SUCCESS') {

									$("#signuploadstatus")
											.text(
													"Your AOF uploaded successfully. Registration process complete.");
									$("#signuploadstatus")
											.css("color", "green");
									$("#aofuploadbtn").hide();
									$("#purchasecon").removeAttr('hidden');

									move(100);

								} else if (data == 'INTERNAL_ERROR') {
									$('#exampleModal1').modal('hide');
									$("#signuploadstatus")
											.text(
													"Failed to upload your AOF. Kindly contact Admin.");
									$("#signuploadstatus").css("color", "red");	

								}

								else if (data == 'SESSION_MOB_MISMATCH') {
									$('#exampleModal1').modal('hide');
									$("#signuploadstatus")
											.text(
													"Session data mismatch. Kindly contact admin");
									$("#signuploadstatus").css("color", "red");
								} else if (data == 'REQUEST_DENIED') {
									$('#exampleModal1').modal('hide');
									$("#signuploadstatus")
											.text(
													"Session lost. Kindly login to complete registration");
									$("#signuploadstatus").css("color", "red");
								} else {
									$('#exampleModal1').modal('hide');
									$("#signuploadstatus").text(data);
									$("#signuploadstatus").css("color", "red");
								}

							})
					.fail(
							function(response) {
								$('#exampleModal1').modal('hide');
								$("#signuploadstatus")
										.text(
												"Failed to make request. Please try again.");
							});
			
			 */
			
			
			// AJAX based
			var mobileData = $("#mobdata").val();
			
			request = $.ajax({
			url: "/products/mutual-funds/uploadsignedaof?mobile="+mobileData,
			method: "GET",
			async: true,
			beforeSend: function() {
				disableButon();
		    }
		});

		request.done(function(data) {
			if (data == 'SUCCESS') {

				$("#signuploadstatus")
						.text("Your AOF uploaded successfully. Registration process complete.");
				$("#signuploadstatus").css("color", "green");
				$("#aofuploadbtn").hide();
				$("#purchasecon").removeAttr('hidden');

				move(100);

			} else if (data == 'INTERNAL_ERROR') {
				$('#exampleModal1').modal('hide');
				$("#signuploadstatus").text("Failed to upload your AOF. Kindly contact Admin.");
				$("#signuploadstatus").css("color", "red");	

			}

			else if (data == 'SESSION_MOB_MISMATCH') {
				$('#exampleModal1').modal('hide');
				$("#signuploadstatus")
						.text(
								"Session data mismatch. Kindly contact admin");
				$("#signuploadstatus").css("color", "red");
			} else if (data == 'REQUEST_DENIED') {
				$('#exampleModal1').modal('hide');
				$("#signuploadstatus")
						.text(
								"Session lost. Kindly login to complete registration");
				$("#signuploadstatus").css("color", "red");
			} else {
				$('#exampleModal1').modal('hide');
				$("#signuploadstatus").text(data);
				$("#signuploadstatus").css("color", "red");
			}

		});

		request.fail(function(jqXHR, textStatus) {
			$('#exampleModal1').modal('hide');
			$("#signuploadstatus")
					.text("Failed to make request. Please try again.");
		});
		
		request.always(function(msg){
			enableButton();
		});
			
			// -------------------
			
			
			
			
		}

		function move(value) {
			var elem = document.getElementById("myBar");
			var val = document.getElementById("statusp");
			var width = 50;
			var id = setInterval(frame, 10);
			function frame() {
				/* if (width >= 100) { */
				if (width >= value) {
					clearInterval(id);
				} else {
					width++;
					elem.style.width = width + '%';
					$("#statusp").text('Invest profile status ' + width + '%');
				}
			}
		}

		function aoffileUpload() {
			console.log($("inputGroupFile04").val());
			
			var file = document.getElementById("input-file-now").files[0];
			if (file) {
			    var reader = new FileReader();
			    reader.readAsText(file, "UTF-8");
			    reader.onload = function (evt) {
			        document.getElementById("fileContents").innerHTML = evt.target.result;
			    }
			    reader.onerror = function (evt) {
			        document.getElementById("fileContents").innerHTML = "error reading file";
			    }
			}
		}
		
		function disableButon(){
			$("#uploadtxt").hide();
			$("#uploadingtxt").show();
			$("#aofuploadbtn").prop("disabled", true);
			
		}
		
		function enableButton(){
			$("#uploadingtxt").hide();
			$("#uploadtxt").show();
			$("#aofuploadbtn").prop("disabled", false);	
		}