var signapplicant="sign1";
var signature1="";
var signature2="";
var sigImage = document.getElementById("sig-image");


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
		if (e.target == canvas || e.target == canvas2) {
			e.preventDefault();
		}
	}, { passive: false });
	document.body.addEventListener("touchend", function(e) {
		if (e.target == canvas || e.target == canvas2) {
			e.preventDefault();
		}
	}, { passive: false });
	document.body.addEventListener("touchmove", function(e) {
		if (e.target == canvas || e.target == canvas2) {
			e.preventDefault();
		}
	}, { passive: false });

	(function drawLoop() {
		requestAnimFrame(drawLoop);
		renderCanvas();
	})();

	function clearCanvas() {
		canvas.width = canvas.width;
	}

	console.log("Checkoing signagture 1...")

	var clearBtn = document.getElementById("sig-clearBtn");

	clearBtn.addEventListener("click", function(e) {
		clearCanvas();
		ctx.strokeStyle = "#305ab3";
		ctx.lineWidth = 2;
	}, false);



	/*---------------------- 2nd canvas ----------------------------------------*/
	console.log("2nd canvas")
	var canvas2 = document.getElementById("sig-canvas2");
	var ctx2 = canvas2.getContext("2d");
	ctx2.strokeStyle = "#305ab3";
	ctx2.lineWidth = 2;

	var drawing2 = false;
	var mousePos2 = {
			x : 0,
			y : 0
	};
	var lastPos2 = mousePos2;

	canvas2.addEventListener("mousedown", function(e) {
		drawing2 = true;
		lastPos2 = getMousePos2(canvas2, e);
	}, false);

	canvas2.addEventListener("mouseup", function(e) {
		drawing2 = false;
	}, false);

	canvas2.addEventListener("mousemove", function(e) {
		mousePos2 = getMousePos2(canvas2, e);
	}, false);

	// Add touch event support for mobile
	canvas2.addEventListener("touchstart", function(e) {

	}, false);

	canvas2.addEventListener("touchmove", function(e) {
		var touch = e.touches[0];
		var me = new MouseEvent("mousemove", {
			clientX : touch.clientX,
			clientY : touch.clientY
		});
		canvas2.dispatchEvent(me);
	}, false);

	canvas2.addEventListener("touchstart", function(e) {
		mousePos2 = getTouchPos2(canvas, e);
		var touch = e.touches[0];
		var me = new MouseEvent("mousedown", {
			clientX : touch.clientX,
			clientY : touch.clientY
		});
		canvas2.dispatchEvent(me);
	}, false);

	canvas2.addEventListener("touchend", function(e) {
		var me = new MouseEvent("mouseup", {});
		canvas2.dispatchEvent(me);
	}, false);



	function getMousePos2(canvasDom, mouseEvent) {
		var rect = canvasDom.getBoundingClientRect();
		return {
			x : mouseEvent.clientX - rect.left,
			y : mouseEvent.clientY - rect.top
		}
	}

	function getTouchPos2(canvasDom, touchEvent) {
		var rect = canvasDom.getBoundingClientRect();
		return {
			x : touchEvent.touches[0].clientX - rect.left,
			y : touchEvent.touches[0].clientY - rect.top
		}
	}
	

	function renderCanvas2() {
		if (drawing2) {
			ctx2.moveTo(lastPos.x, lastPos.y);
			ctx2.lineTo(mousePos.x, mousePos.y);
			ctx2.stroke();
			lastPos2 = mousePos2;
		}
	}
	

	function clearCanvas2() {
		canvas2.width = canvas2.width;
	}

	console.log("Checking 2nd signature signagture...")

	var clearBtn2 = document.getElementById("sig-clearBtn2");

	clearBtn2.addEventListener("click", function(e) {
		clearCanvas2();
		ctx2.strokeStyle = "#305ab3";
		ctx2.lineWidth = 2;
	}, false);

	/* ----------------------- End of 2nd canvas initialization ------------------------------------ */



})();


/*---------------------- 2nd canvas ----------------------------------------*/
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


	console.log("2nd canvas")
	var canvas2 = document.getElementById("sig-canvas2");
	var ctx2 = canvas2.getContext("2d");
	ctx2.strokeStyle = "#305ab3";
	ctx2.lineWidth = 2;

	var drawing2 = false;
	var mousePos2 = {
			x : 0,
			y : 0
	};
	var lastPos2 = mousePos2;

	canvas2.addEventListener("mousedown", function(e) {
		drawing2 = true;
		lastPos2 = getMousePos2(canvas2, e);
	}, false);

	canvas2.addEventListener("mouseup", function(e) {
		drawing2 = false;
	}, false);

	canvas2.addEventListener("mousemove", function(e) {
		mousePos2 = getMousePos2(canvas2, e);
	}, false);

	// Add touch event support for mobile
	canvas2.addEventListener("touchstart", function(e) {

	}, false);

	canvas2.addEventListener("touchmove", function(e) {
		var touch = e.touches[0];
		var me = new MouseEvent("mousemove", {
			clientX : touch.clientX,
			clientY : touch.clientY
		});
		canvas2.dispatchEvent(me);
	}, false);

	canvas2.addEventListener("touchstart", function(e) {
		mousePos2 = getTouchPos2(canvas, e);
		var touch = e.touches[0];
		var me = new MouseEvent("mousedown", {
			clientX : touch.clientX,
			clientY : touch.clientY
		});
		canvas2.dispatchEvent(me);
	}, false);

	canvas2.addEventListener("touchend", function(e) {
		var me = new MouseEvent("mouseup", {});
		canvas2.dispatchEvent(me);
	}, false);


	function getMousePos2(canvasDom, mouseEvent) {
		var rect = canvasDom.getBoundingClientRect();
		return {
			x : mouseEvent.clientX - rect.left,
			y : mouseEvent.clientY - rect.top
		}
	}

	function getTouchPos2(canvasDom, touchEvent) {
		var rect = canvasDom.getBoundingClientRect();
		return {
			x : touchEvent.touches[0].clientX - rect.left,
			y : touchEvent.touches[0].clientY - rect.top
		}
	}

	function renderCanvas2() {
		if (drawing2) {
			ctx2.moveTo(lastPos2.x, lastPos2.y);
			ctx2.lineTo(mousePos2.x, mousePos2.y);
			ctx2.stroke();
			lastPos2 = mousePos2;
		}
	}

	(function drawLoop() {
		requestAnimFrame(drawLoop);
		renderCanvas2();
	})();

	function clearCanvas2() {
		canvas2.width = canvas2.width;
	}

	console.log("Checking 2nd signature signagture...")

	var clearBtn2 = document.getElementById("sig-clearBtn2");

	clearBtn2.addEventListener("click", function(e) {
		clearCanvas2();
		ctx2.strokeStyle = "#305ab3";
		ctx2.lineWidth = 2;
	}, false);

})();

/* ----------------------- End of 2nd canvas initialization ------------------------------------ */

window.onload = function() {

	var c = document.getElementById("sig-canvas");
	var ctx = c.getContext("2d");
	var image = new Image();
	image.onload = function() {
		ctx.drawImage(image, 0, 0);
	};
	image.src=document.getElementById("signature1").value;
	
	var c2 = document.getElementById("sig-canvas2");
	var ctx2 = c2.getContext("2d");
	var image2 = new Image();
	image2.onload = function() {
		ctx2.drawImage(image2, 0, 0);
	};
	image2.src=document.getElementById("signature2").value;




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