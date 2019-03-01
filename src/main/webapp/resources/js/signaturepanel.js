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
	var submitBtn = document.getElementById("sig-submitBtn");
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

		/* var aofformimg = document.getElementById("aofformsignature");
				aofformimg.setAttribute("src", dataUrl); */
		$("#signature1").val(dataUrl);
		submitSign();

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

	/* console.log("signature- "+ $("#sig-dataUrl").text()); */
	$
	.post(
			"/products/mutual-funds/uploadsign",
			/* $.post("/products/api/saveloanquery", */

			{
				sign1 : $("#sig-dataUrl").text(),
				sign2 : ""
			},
			function(data, status) {
				/* alert("Data: " + data + "\nStatus: " + status); */
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
}

function initiateAOFUpload() {

	console.log("signature- "+ $("#mobdata").val());
	$
	.get(
			"/products/mutual-funds/uploadsignedaof",
			/* $.post("/products/api/saveloanquery", */

			{
				mobile : $("#mobdata").val()
			},
			function(data, status) {
				/* alert("Data: " + data + "\nStatus: " + status); */
				console.log(data);
				if (data == 'SUCCESS') {

					$("#signuploadstatus")
					.text(
					"Your AOF uploaded successfully. Registration process complete.");
					$("#aofuploadbtn").hide();
					$("#purchasecon").removeAttr('hidden');
					move(100);

				}
				if (data == 'INTERNAL_ERROR') {
					$('#exampleModal1').modal('hide');
					$("#signuploadstatus")
					.text(
					"Failed to upload your AOF. Kindly contact Admin.");

				}

				if (data == 'SESSION_MOB_MISMATCH') {
					$('#exampleModal1').modal('hide');
					$("#signuploadstatus")
					.text(
					"Session data mismatch. Kindly contact admin");

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
						"Failed to make request. Please try again.");
					});
	/* 	}); */
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
			$("#statusp").text('Invest profile status '+ width + '%');
		}
	}
}