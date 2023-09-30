var digitregexp = /^([6-9]{1})[0-9]{9}$/;
var otpregexp = /^([0-9]{6})$/;
var letterregexp = /^[a-z A-Z]+$/;
var emailregexp =
  /^([0-9a-zA-Z]([-_.]*[0-9a-zA-Z]+)*)@([0-9a-zA-Z]([-_.]*[0-9a-zA-Z]+)*)[.]([a-zA-Z]{2,9})$/;

var otpVerified = false;
var otpsendingdisabled = false;
var otpverifydisabled = false;
var timervar;
var getUrl = window.location;
var baseurl = getUrl.origin; //or
var baseurl = getUrl.pathname.split("/")[1];

$(document).ready(function () {
  // For UI
  $(".mobile").hide();
  $(".phone-btn").click(function () {
    $(".mobile").show();
    $(".phone-btn").hide();
  });
  $("#verify-otp-btn").prop("disabled", true);
  $("#otpVerifiedBox").hide();
  $("#displayboxOTP").hide();
  validateForm();
  var otpverified = document.getElementById("otpverified").value;
  console.log("Data- " + otpverified);
  if (otpverified == "Y") {
    $("#send-otp-btn").prop("disabled", true);
    $("#send-otp-btn").html('<i class="fas fa-check"></i>');
    $("#send-otp-btn").removeClass("btn-deep-orange");
    $("#send-otp-btn").addClass("btn-success");
    otpVerified = true;
    otpsendingdisabled = true;
    otpverifydisabled = true;
  }
  $("#msgName").html("");
  $("#msgPass").html("");
  $("#msgEmail").html("");
  $("#msgMobile").html("");
});

function validateForm() {
  //	var username = document.forms["registerForm"]["username"].value;
  var fname = document.getElementById("fnameid").value;
  var lname = document.getElementById("lnameid").value;
  var registermobile = document.getElementById("registermobile").value;
  var useremail = document.getElementById("useremail").value;
  var password = document.getElementById("registerpassword").value;
  var otpverified = document.getElementById("otpverified").value;

  // var mobdes = document.getElementById("mobico");
  // var namedes = document.getElementById("userico");
  // var emaildes = document.getElementById("mailico");
  // var passdes = document.getElementById("passico");

  var formInvalid = true;

  var cond1 = false,
    cond2 = false,
    cond3 = false,
    cond4 = false,
    cond5 = false;

  if (
    fname == "" ||
    !letterregexp.test(fname) ||
    lname == "" ||
    !letterregexp.test(lname)
  ) {
    $("#msgName").html("Please Enter Valid Name.");
    return false;
  } else {
    $("#msgName").html("");
    cond1 = true;
    console.log(cond1);
  }

  if (password == undefined || password == "" || password.length < 8) {
    $("#msgPass").html("Please Enter Valid Password! Minimum 8 Character.");
    return false;
  } else {
    $("#msgPass").html("");
    cond2 = true;
    console.log(cond2);
  }

  if (useremail == "" || !emailregexp.test(useremail)) {
    $("#msgEmail").html("Please Enter Valid Email!");
    return false;
  } else {
    $("#msgEmail").html("");
    cond3 = true;
    console.log(cond3);
  }

  if (registermobile == "" || !digitregexp.test(registermobile)) {
    $("#msgMobile").html("Please Enter Valid Mobile Number!");
    return false;
  } else {
    cond4 = true;
    $("#msgMobile").html("");
    console.log(cond4);
  }

  if (otpverified == "Y") {
    cond5 = true;
    console.log("cond5" + cond5);
  } else {
    cond5 = false;
    $("#msgMobile").html("Please Verify Mobile Number!");
    console.log("cond5" + cond5);
    return false;
  }

  if (cond1 && cond2 && cond3 && cond4 && cond5) {
    document.getElementById("registerSubmit").disabled = false;
    return true;
  } else {
    document.getElementById("registerSubmit").disabled = false;
    return false;
  }
}

var input = document.getElementById("registerpassword");
input.addEventListener("keyup", function (event) {
  // If "caps lock" is pressed, display the warning text
  if (event.getModifierState("CapsLock")) {
    $("#msg2").html("Caps lock ON!");
  } else {
    $("#msg2").html("");
  }
});

// $("#registerSubmit").click(function () {
//   if (validateForm()) {
//     console.log("Form Valid");
//     $("#registerSubmit").prop("disabled", true);
//     $("#registerSubmit").html("Processing <i class='fas fa-spinner fa-spin'>");
//     return true;
//   } else {
//     console.log("Form Not Valid");
//     return false;
//   }
// });

$("form").submit(function () {
  if (validateForm()) {
    console.log("Form Valid");
    $("#registerSubmit").prop("disabled", true);
    $("#registerSubmit").html("Processing <i class='fas fa-spinner fa-spin'>");
    return true;
  } else {
    console.log("Form Not Valid");
    return false;
  }
});

$("#send-otp-btn").click(function () {
  var registermobile = document.getElementById("registermobile").value;
  var sessionid = document.getElementById("sessionid").value;
  var modal = document.querySelector(".modal");
  if (registermobile == "" || !digitregexp.test(registermobile)) {
    $("#displaymsg").text("Please enter a valid Indian mobile no.");
    return false;
  } else {
    $("#otpblock").modal();
    console.log("Mobile no valid. Trigger SMS");
    var jsonObjects = {
      mobile: registermobile,
      module: window.location.pathname,
      submodule: "REGISTRATION",
      sessionid: sessionid,
    };

    if (!otpsendingdisabled) {
      console.log("Trigger otp call");
      var request;
      request = $.ajax({
        url: basepathdata + "/api/send-otp",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(jsonObjects),
        async: true,
        datatype: "json",
        beforeSend: function () {
          otpsendingdisabled = true;
          $("#otptext").text("Sending..");
          $("#send-otp-btn").prop("disabled", true);
        },
      });

      request.done(function (msg, textStatus, xhr) {
        var data = JSON.parse(msg);
        console.log("Data- " + data + "-> " + data.statuscode);
        if (data.statuscode == "0") {
          toastr.success(data.msg);

          $("#otptext").text("Sent");
          $("#send-otp-btn").prop("disabled", true);
          // $("#otpblock").removeClass("d-none");
          // $("#otpblock").addClass("d-block");
          $("#otpblock").modal();

          otpsendingdisabled = true;
          timervar = setTimeout(function () {
            console.log("Re-enable OTP");
            otpsendingdisabled = false;
            $("#otptext").text("Resend");
            $("#send-otp-btn").prop("disabled", false);
          }, 1 * 30 * 1000);
          var timeLeft = 29;
          var timerId = setInterval(countdown, 1000);

          function countdown() {
            if (timeLeft == 0) {
              clearTimeout(timerId);
              $("#otptext").text("Resend");
              $("#send-otp-btn").prop("disabled", false);
              $("#timeCount").show();
              $("#otpblock").modal("hide");
            } else {
              $("#timeCount").show();
              $("#otptext").text("Sent");
              $("#send-otp-btn").prop("disabled", true);
              timeLeft--;
              if (timeLeft < 10) {
                // console.log(`0${timeLeft}`);
                $("#timeShow").html(`00:0${timeLeft}`);
              } else {
                $("#timeShow").html(`00:${timeLeft}`);
              }
            }
          }
        } else {
          $("#otpblock").modal("hide");
          $("#displayboxOTP").show();
          $("#displaymsgOTP").html("Maximum Limit Reached!");
          console.log("Not sent");
          toastr.warning("OTP could not be sent to " + registermobile, "");
          otpsendingdisabled = false;
          $("#otptext").text("Get OTP");
          $("#send-otp-btn").prop("disabled", false);
          $("#msgMobile").html("Maximum Limit Reached!");
        }
        console.log("data- " + data + " - " + textStatus + " -" + xhr);
      });

      request.fail(function (xhr, textStatus) {
        toastr.error("OTP sending error! Please try again.", "");
        otpsendingdisabled = false;
        $("#otptext").text("Get OTP");
        $("#send-otp-btn").prop("disabled", false);
        $("#otpblock").modal("hide");
      });
    } else {
      console.log("OTP disabled..");
      $("#send-otp-btn").prop("disabled", true);
      if (otpVerified) {
        alert("Mobile no verified. Proceed with submission.");
      }
    }
  }
});

$(".otp__field__6").keyup(function () {
  var otpinfo = document.getElementById("otpbox").value;
  var registermobile = document.getElementById("registermobile").value;

  if (otpinfo != "" && otpregexp.test(otpinfo)) {
    if (registermobile != "" && digitregexp.test(registermobile)) {
      $("#verify-otp-btn").prop("disabled", false);
    } else {
      $("#displaymsg").text("Mobile no is required first.");
    }
  } else {
    $("#verify-otp-btn").prop("disabled", true);
  }
});

$("#verify-otp-btn").click(function () {
  var registermobile = document.getElementById("registermobile").value;
  var otpinfo = document.getElementById("otpbox").value;
  var sessionid = document.getElementById("sessionid").value;

  if (registermobile == "" || !digitregexp.test(registermobile)) {
    $("#displaymsg").text("Please enter a valid Indian mobile no.");
  } else if (otpinfo == "" || !otpregexp.test(otpinfo)) {
    $("#displaymsg").text("Please enter OTP coorectly");
  } else {
    console.log("Mobile no valid. Trigger SMS");

    var jsonObjects = {
      mobile: registermobile,
      otpinfo: otpinfo,
      module: window.location.pathname,
      submodule: "REGISTRATION",
      sessionid: sessionid,
    };

    if (!otpverifydisabled) {
      //			$(".otp-val").show();

      //alert(state);
      console.log("Trigger otp verify");
      var request;
      request = $.ajax({
        url: basepathdata + "/api/verify-otp",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(jsonObjects),
        async: true,
        datatype: "json",
        beforeSend: function () {
          otpverifydisabled = true;
          $("#otpverify").text("Checking..");
          $("#verify-otp-btn").prop("disabled", true);
        },
      });

      request.done(function (data, textStatus, xhr) {
        if (data == "SUCCESS") {
          //					const options= { positionClass:'toast-center' };
          //					toastr.success("OTP has been sent to "+ registermobile, '' );

          $("#otpverify").text("Verified");
          $("#verify-otp-btn").prop("disabled", true);
          otpverifydisabled = true;
          otpsendingdisabled = true;
          clearTimeout(timervar);
          $("#otpblock").modal("hide");
          $("#send-otp-btn").prop("disabled", true);
          $("#send-otp-btn").hide();
          $("#otpVerifiedBox").show();
          $("#otpVerifiedBox").prop("disabled", true);
          $("#otptextVerified").html('<i class="fas fa-check"></i>');
          $("#otptextVerified").removeClass("border-danger");
          $("#otptextVerified").removeClass("text-danger");
          $("#otptextVerified").addClass("border-success");
          $("#otptextVerified").addClass("text-success");
          document.getElementById("otpverified").value = "Y";
          validateForm();
        } else {
          console.log("Not verified");
          //					const options= { positionClass:'toast-center' };
          toastr.error(data);
          otpverifydisabled = false;
          $("#otpverify").text("Verify");
          $("#verify-otp-btn").prop("disabled", false);
          $("#send-otp-btn").prop("disabled", true);
          document.getElementById("otpverified").value = "N";
        }
        console.log("data- " + data + " - " + textStatus + " -" + xhr);
      });

      request.fail(function (xhr, textStatus) {
        toastr.error("OTP sending error! Please try again.", "");
        otpverifydisabled = false;
        $("#otptext").text("Verify");
        $("#verify-otp-btn").prop("disabled", false);
        document.getElementById("otpverified").value = "N";
      });
    } else {
      console.log("OTP verification disabled..");
    }
  }
});
