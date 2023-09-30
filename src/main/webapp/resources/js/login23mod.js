var otpsubmit = false;
$(document).ready(function () {
  $("#timeCount").hide();
  var distance = 300000;
  var tokenexpired = false;
  var current_fs, next_fs, previous_fs; //fieldsets
  var opacity;

  $(".login").click(function (e) {
    e.preventDefault();
    var key = document.forms["msform"]["usermobile"].value;
    var type = $("input[name='loginmethod']:checked").val();
    var digitregexp = /^[6-9]{1}[0-9]{9}$/;

    if (!digitregexp.test(key)) {
      $("#loginmsg").text("Invalid mobile no.");
      return false;
    }

    $("#key").val(key);
  });
  $("#sendOTPbtn").click(function (event) {
    event.preventDefault();
    var key = document.forms["msform"]["usermobile"].value;
    var type = $("input[name='loginmethod']:checked").val();
    if (type == "OTP") {
      var otpsent = requestotp();
      if (otpsent) {
        var pwdlement = document.getElementById("pwd");
        if (typeof pwdlement != "undefined" && pwdlement != null) {
          pwdlement.remove();
        } else {
          // pwdlement.add();
        }
        var element = document.getElementById("otpbox");
        if (typeof element != "undefined" && element != null) {
        } else {
          $("#nextbtn").show();
          console.log("Otp Sent");
        }
      } else {
        $("#nextbtn").prop("disabled", false);
        return false;
      }
    }
  });

  $(".login").click(function () {
    $("#timeCount").hide();
    validatesubmit();
  });

  $(".radio-group .radio").click(function () {
    $(this).parent().find(".radio").removeClass("selected");
    $(this).addClass("selected");
  });

  $(".submit").click(function () {
    console.log("submit test");
    return false;
  });
});

function requestotp() {
  var requestpass = false;
  $("#loginmsg").text("");
  var digitregexp = /^[6-9]{1}[0-9]{9}$/;
  var mobile = document.forms["msform"]["usermobile"].value;
  //	var pass = document.forms["msform"]["userpassword"].value;
  var returnUrl = document.forms["msform"]["returnUrl"].value;
  //	var otploginChosen = $("#otplogin").is(":checked");
  //	var otpVal = document.forms["msform"]["otpVal"].value;
  var token = $('input[name="_csrf"]').attr("value");
  var type = $("input[name='loginmethod']:checked").val();
  var pass = "OTP_LOGIN";

  var otploginChosen = false;
  if (type == "OTP") {
    console.log("Tryt OTP");
    otploginChosen = true;
    otpsubmit = false;
    pass = "OTP_LOGIN";
  } else if (type == "PWD") {
    otploginChosen = false;
  }

  $.ajaxSetup({
    headers: { "X-CSRF-TOKEN": token },
  });
  var jsonObjects = {
    usermobile: mobile,
    userpassword: pass,
    otpVal: "NONE",
    otpLogin: otploginChosen,
    returnUrl: encodeURIComponent(returnUrl),
    otpSubmit: otpsubmit,
    _csrf: token,
  };
  console.log(JSON.stringify(jsonObjects));
  var request;
  request = $.ajax({
    url: "/products/login-otp",
    method: "POST",
    contentType: "application/json",
    data: JSON.stringify(jsonObjects),
    async: false,
    datatype: "json",
    beforeSend: function () {
      console.log("Test disable");

      $("#nextbtn").html('Processing <i class="fas fa-spinner fa-spin"></i>');
      //$("#nextbtn").prop('disabled',true);
      otpsending = true;
    },
  });

  request.done(function (data, textStatus, xhr) {
    console.log("Response received- " + JSON.stringify(data));
    if (data.statuscode == 0) {
      $("#loginmsg").text(data.msg);
      $("#nextbtn").show();
      $("#sendOTPbtn").hide();
      timer = setTimeout(function () {
        console.log("Re-enable OTP");
        otpsending = false;
        //				$("#"+sendbutton).prop('disabled',false);
        $("#sendOTPbtn").show();
        $("#nextbtn").hide();
        $("#sendbutton").html("Re-send OTP");
      }, 30000);
      var timeLeft = 29;
      var timerId = setInterval(countdown, 1000);

      function countdown() {
        if (timeLeft == 0) {
          clearTimeout(timerId);
          $("#sendbutton").html("Re-send OTP");
          $("#sendOTPbtn").show();
          $("#nextbtn").hide();
          $("#timeCount").show();
          // $("#timeCount").html("OTP expried. Please Resend OTP.");
        } else {
          $("#timeCount").show();
          $("#nextbtn").show();
          $("#sendOTPbtn").hide();
          timeLeft--;
          if (timeLeft < 10) {
            // console.log(`0${timeLeft}`);
            $("#timeShow").html(`00:0${timeLeft}`);
          } else {
            $("#timeShow").html(`00:${timeLeft}`);
          }
        }
      }
      requestpass = true;
    } else {
      console.log("failed....");
      $("#loginmsg").text(data.errormsg);
      requestpass = false;
    }
  });

  request.fail(function (xhr, textStatus) {
    //		alert("Request failed: "+textStatus+" "+ xhr.responseText);
    otpsending = false;
    $("#loginmsg").text("Request failed. Please retry!");
    //		location.reload();
    valid = false;
    //		$("#").html("Send OTP");
    requestpass = false;
  });
  request.always(function (xhr) {
    //		$("#").html("Send OTP");
  });
  console.log("Returning- " + requestpass);
  return requestpass;
}

function validatesubmit() {
  var requestpass = false,
    otpval,
    pass;
  $("#loginmsg").text("");
  var digitregexp = /^[6-9]{1}[0-9]{9}$/;
  var mobile = document.forms["msform"]["usermobile"].value;
  var returnUrl = document.forms["msform"]["returnUrl"].value;
  //	var otploginChosen = $("#otplogin").is(":checked");
  var token = $('input[name="_csrf"]').attr("value");
  var type = $("input[name='loginmethod']:checked").val();
  // var type = "PWD";
  var otploginChosen = false;
  if (type == "OTP") {
    otploginChosen = true;
    pass = "OTP_LOGIN";
    otpsubmit = true;
    otpval = document.forms["msform"]["otpVal"].value;
  } else if (type == "PWD") {
    console.log(type);
    var pass = document.forms["msform"]["userpassword"].value;
    var otpval = "NONE";
    otploginChosen = false;
    otpsubmit = false;
  }

  $.ajaxSetup({
    headers: { "X-CSRF-TOKEN": token },
  });
  var jsonObjects = {
    usermobile: mobile,
    userpassword: pass,
    otpVal: otpval,
    otpLogin: otploginChosen,
    returnUrl: encodeURIComponent(returnUrl),
    otpSubmit: otpsubmit,
    _csrf: token,
  };
  console.log(JSON.stringify(jsonObjects));
  var request;
  request = $.ajax({
    url: "/products/login-verify",
    method: "POST",
    contentType: "application/json",
    data: JSON.stringify(jsonObjects),
    async: false,
    datatype: "json",
    beforeSend: function () {
      console.log("Test disable");
      //			$("#nextBtn").html("Processing <i class=\"fas fa-spinner fa-spin\"></i>");

      //document.getElementById("sendotpbutton").innerHTML = "Processing <i class=\"fas fa-spinner fa-spin\"></i>";
      $("#login_account").html(
        'Processing <i class="fas fa-spinner fa-spin"></i>'
      );
      $("#login_account").prop("disabled", true);
      otpsending = true;
    },
  });

  request.done(function (data, textStatus, xhr) {
    console.log("Response received- " + JSON.stringify(data));
    if (data.statuscode == 0) {
      if (data.msg == "SUCCESS") {
        window.location.href = returnUrl;
        return true;
      } else {
        $("#loginmsg").text(data.errormsg);
        requestpass = false;
      }
    } else {
      console.log("failed..");
      $("#loginmsg").text(data.errormsg);
      requestpass = false;
    }
  });

  request.fail(function (xhr, textStatus) {
    otpsending = false;
    $("#loginmsg").text("Verification request failed. Please retry!");
    //		location.reload();
    valid = false;
    //		$("#").html("Send OTP");
    requestpass = false;
  });
  request.always(function (xhr) {
    //		$("#").html("Send OTP");
  });
  console.log("Returning- " + requestpass);
  return requestpass;
}
