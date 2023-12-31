//console.log = function(){};
var mfdatapulled = false;
var mfdatainprogress = false;
var loanhandler = false;
var statusobj;

function AdditionalPurchase(folio, code, type, rtaAgent, productCode) {
  if (folio == "NEW") {
    alert("You cannot make additional purhcase until portfolio is assigned!");
  } else {
    window.location.assign(
      window.location.href +
        "/additional-purchase?p=" +
        btoa(
          folio + "|" + code + "|" + type + "|" + rtaAgent + "|" + productCode
        )
    );
  }
}

function MFRedeem(folio, code, type, rtaAgent, productCode) {
  if (folio == "NEW") {
    alert(
      "No portfolio number to redeem your investment! Please wait for portfolio number."
    );
  } else {
    window.location.assign(
      window.location.href +
        "/funds-redeem?r=" +
        btoa(
          folio + "|" + code + "|" + type + "|" + rtaAgent + "|" + productCode
        )
    );
  }
  //	window.location.assign(window.location.href+"/funds-redeem?r="+btoa(folio+"|"+code+"|"+type));
}

function completePendingPayments() {
  request = $.ajax({
    url: "/products/api/mutual-funds/pending-payments",
    method: "POST",
    data: {
      mobile: "NA",
    },
    async: true,
    datatype: "json",
    contentType: "application/json",
    beforeSend: function () {
      showtaskProgress("pendinglink");
    },
  });

  request.done(function (data) {
    if (data == "NO_SESSION") {
      alert("Your session has expired. Kindly login again.");
    } else if (data == "SERVICE_DISABLED") {
      alert("Services are currently disabled. Kindly try again later.");
    } else if (data == "NO_REGISTERED") {
      alert(
        "You need to complete MF account registration to avail this service. Kindly contact admin if you are registered and facing issue."
      );
    } else if (data == "UNAVAILABLE" || data == "INTERNAL_ERROR") {
      alert("Unable to process the request. Kindly contact admin");
    } else {
      window.location.href = data;
    }
  });

  request.fail(function (jqXHR, textStatus) {
    alert("Failed to process your request!");
  });

  request.always(function (msg) {
    completetaskProgress("pendinglink", "COMPLETE PENDING PAYMENTS");
  });
}

function cancelOrder(schemeCode, orderno, type, category, transactionId) {
  console.log("Cancel order request...");
  if (type == "SIP_COMMNTED") {
    alert("SIP order cancel process will be available soon. ");
  } else if (type == "LUMPSUM" || type == "SIP") {
    window.location.assign(
      "/products/my-dashboard/cancel-order?ref=" +
        btoa(
          schemeCode +
            "|" +
            orderno +
            "|" +
            type +
            "|" +
            category +
            "|" +
            transactionId
        )
    );
  } else {
    alert("Invalid type of investment");
  }
}

function getbseOrderPaymentStatus(clientId, orderNo) {
  //	console.log("Order staus for id- " + clientId + " : " + orderNo);
  $.get(
    "/products/mutual-funds/orderpaymentStatus",
    {
      client: clientId,
      order: orderNo,
    },
    function (data, status) {
      console.log(data);
      $("#exampleModal1").modal("hide");
      if (data == "NO_SESSION") {
        alert("Invalid request");
      } else if (data == "REQUEST_DENIED") {
        alert("Session not found!");
      } else {
        alert("Status of order no: " + orderNo + "\n" + data);
      }
    }
  ).fail(function (response) {
    /* $('#exampleModal1').modal('hide');
		$("#signuploadstatus")
				.text(
						"Failed to submit your signature. Please try again."); */
    /* alert(response); */
    alert("Failed to get status for order- " + orderNo);
  });
}

/*$(document).ready(function(){
	$(".box-style2").hover(
			function(){
				console.log("animated")
				$(this).addClass("animated pulse");
			});
});

$(document).ready(function(){
	$(".box-style2").blur(
			function(){
				console.log("remove animated");
				$(this).removeClass("animated pulse");
			});
});*/

function getMFPortfolioData(mobile, profileStatus) {
  console.log("Get Profile Data as requested- " + mobile);

  if (profileStatus === "PROFILE_READY") {
    if (!mfdatapulled) {
      let checkexpiretime = window.sessionStorage.getItem("expiremfdata");
      //			console.log(checkexpiretime);
      if (checkexpiretime != null && new Date() > new Date(checkexpiretime)) {
        console.log("Time expired");
        window.sessionStorage.removeItem("mftata");
        window.sessionStorage.removeItem("expiremfdata");
      } else {
        //				console.log("Within time or null");
      }

      $("#mfdatatbody2").children().remove();

      var storedmfdata = sessionStorage.getItem("mftata");
      //			console.log("storedmfdata- "+ storedmfdata);
      if (storedmfdata == null && mfdatainprogress == false) {
        request = $.ajax({
          url: "/products/api/mf/getmfprofileData",
          method: "POST",
          data: {
            mobile: mobile,
          },
          async: true,
          datatype: "json",
          beforeSend: function () {
            showprogress("msgmfapi");
            mfdatainprogress = true;
          },
        });

        request.done(function (msg) {
          if (msg == "NO_SESSION") {
            $("#msgmfapi").text(
              "Session expired. Login to your account again."
            );
          } else if (msg == "REQUEST_DENIED") {
            $("#msgmfapi").text(
              "Request Denied. Please try again or contact admin."
            );
          } else if (msg == "NO_DATA") {
            $("#msgmfapi").html(
              '<a class="col-md-3 col-lg-4 col-8" href="/mutual-funds"><button class="btn btn-sm btn-load-more"><img src="https://resources.freemi.in/products/resources/images/invest/investment4.png" class="img-fluid" style="height: 2rem;"> Start Investing</button> </a>'
            );
          } else {
            $("#msgmfapi").text("");
            $("#msgmfapi").html("");

            if (msg.length > 0) {
              createmfdataView(msg);

              if (typeof Storage !== "undefined") {
                window.sessionStorage.setItem("mftata", msg);
                window.sessionStorage.setItem(
                  "expiremfdata",
                  new Date(new Date().getTime() + 60000 * 60)
                );
              }
            } else {
              $("#msgmfapi").html(
                '<a class="col-md-3 col-lg-4 col-8" href="/mutual-funds"><button class="btn btn-sm btn-load-more"><img src="https://resources.freemi.in/products/resources/images/invest/investment4.png" class="img-fluid" style="height: 2rem;"> Start Investing</button> </a>'
              );
            }
          }
        });

        request.fail(function (jqXHR, textStatus) {
          $("#msgmfapi").text(
            "Failed to fetch your data. Please try after sometime"
          );
        });

        request.always(function (msg) {
          console.log("MF data fetch complete.");
          mfdatainprogress = false;
        });
      } else {
        console.log("Populate stored data");
        $("#msgmfapi").html("");
        createmfdataView(storedmfdata);
      }
    }
  } else {
    $("#msgmfapi").html(
      '<a class="col-md-3 col-lg-4 col-8" href="/products/mutual-funds/register?mf=01"><button class="btn btn-info"> Complete you Mutual Fund Account registrastion</button></a>'
    );
  }
}

function createmfdataView(result) {
  //	console.log("Tranasaction Data- " +result.length + " -> " + result);
  mfdatapulled = true;
  var data = JSON.parse(result);
  var objectLenght = Object.keys(data).length;
  console.log(objectLenght);

  // table.setAttribute("class", "animated fadeIn");
  var htmlElements = "";
  var htmlModal = "";
  for (var i = 0; i < data.length; i++) {
    // var i = objectLenght;
    for (var j = 0; j < data[i].karvyFolioList.length; j++) {
      /*    console.log(jsonData[i]); */

      var x = data[i];
      //		console.log(x);
      var fundName = x.fundName;
      var folioNumber = x.folioNumber;
      if (
        Number(data[i].karvyFolioList[j].marketValue) >
        Number(data[i].karvyFolioList[j].invAmount)
      ) {
        var textClass = "text-success";
        var iconIndication = "&nbsp;<i class='fas fa-caret-up'></i>";
      } else if (
        Number(data[i].karvyFolioList[j].marketValue) <
        Number(data[i].karvyFolioList[j].invAmount)
      ) {
        var textClass = "text-danger";
        var iconIndication = '&nbsp;<i class="fas fa-caret-down"></i>';
      } else {
        var textClass = "text-muted";
        var iconIndication = "";
      }
      htmlElements +=
        '<div class="card mb-3"><div class="row card-body hover-zoom all-hover"><div class="col-md-12 col-12 px-0 my-0 text-right"><a data-toggle="modal" data-target="#selectfundmodal" data-folio="' +
        data[i].karvyFolioList[j].folioNumber +
        '" data-fund="' +
        data[i].karvyFolioList[j].fundDescription +
        '" data-icon="' +
        data[i].amcicon +
        '" data-retclass="' +
        textClass +
        '" data-marketval="' +
        convertNumberToIndianFormat(
          Number(data[i].karvyFolioList[j].marketValue).toFixed(2)
        ) +
        '" data-investval="' +
        convertNumberToIndianFormat(
          Number(data[i].karvyFolioList[j].invAmount).toFixed(2)
        ) +
        '" data-returnval="' +
        Number(
          Number(data[i].karvyFolioList[j].marketValue) -
            Number(data[i].karvyFolioList[j].invAmount)
        )
          .toFixed(2)
          .replace(/\d(?=(\d{3})+\.)/g, "$&,") +
        '" data-returnpercentage="' +
        Number(
          (Number(
            Number(data[i].karvyFolioList[j].marketValue) -
              Number(data[i].karvyFolioList[j].invAmount)
          ) /
            Number(data[i].karvyFolioList[j].invAmount)) *
            100
        ).toFixed(2) +
        '" data-indicate="' +
        iconIndication +
        '" data-folionumber="' +
        data[i].karvyFolioList[j].folioNumber +
        '" data-unit="' +
        data[i].karvyFolioList[j].units +
        '" data-nav="' +
        data[i].karvyFolioList[j].nav +
        '" data-navdate="' +
        data[i].karvyFolioList[j].navdate +
        '" data-bsemfschemecode="' +
        data[i].karvyFolioList[j].bsemfschemeCode +
        '" data-trasanctiontype="' +
        data[i].karvyFolioList[j].trasanctionType +
        '" data-rtaagent="' +
        data[i].karvyFolioList[j].rtaAgent +
        '" data-channelproductcode="' +
        data[i].karvyFolioList[j].channelProductCode +
        '" data-toggle="modal"><i class="fas fa-ellipsis-h"></i></a></div><div class="col-md-1 col-2 px-0 mb-md-2 mb-1"><img class="my-0 rounded fund-icon-all" src="https://resources.freemi.in/products/resources/images/partnerlogo/mf/' +
        data[i].amcicon +
        '" alt="' +
        data[i].fundName +
        '"></div><div class="col-md-7 col-6 px-2 mb-md-2 mb-1"><p class="text-left mb-0 fund-name-all mt-0 mt-md-2">' +
        data[i].karvyFolioList[j].fundDescription +
        '</p></div><div class="col-md-4 col-4 mb-md-2 mb-1"><div class="row justify-content-center"><div class="col-md-12 col-12 px-0"><p class="mb-0 text-right fw-bold return-text">Current<br>₹ ' +
        convertNumberToIndianFormat(
          Number(data[i].karvyFolioList[j].marketValue).toFixed(2)
        ) +
        '</p></div></div></div><div class="col-md-12 col-12 px-0 pt-2 border-top"><div class="row justify-content-center"><div class="col-6"><p class="mb-0 fw-bold return-text">Invested Amount<br>₹ ' +
        Number(data[i].karvyFolioList[j].invAmount)
          .toFixed(2)
          .replace(/\d(?=(\d{3})+\.)/g, "$&,") +
        '</p></div><div class="col-6 text-right"><p class="mb-0 fw-bold">Return<br> <span class="' +
        textClass +
        '">₹ ' +
        Number(
          Number(data[i].karvyFolioList[j].marketValue) -
            Number(data[i].karvyFolioList[j].invAmount)
        )
          .toFixed(2)
          .replace(/\d(?=(\d{3})+\.)/g, "$&,") +
        iconIndication +
        "</span></p></div></div></div></div></div></div>";
      $("#selectfundmodal").on("show.bs.modal", function (event) {
        // $("#modalBody").html($(event.relatedTarget).data("folio"));
        $("#modalHeader").html($(event.relatedTarget).data("fund"));
        $("#modalInvAmt").html($(event.relatedTarget).data("investval"));
        $("#modalMarketamt").html($(event.relatedTarget).data("marketval"));
        $("#modalReturnamt").html($(event.relatedTarget).data("returnval"));
        $("#modalfolioNumber").html($(event.relatedTarget).data("folionumber"));
        $("#modalUnit").html($(event.relatedTarget).data("unit"));
        $("#modalNav").html($(event.relatedTarget).data("nav"));
        $("#modalNavDate").html($(event.relatedTarget).data("navdate"));
        $(".returnClass").addClass($(event.relatedTarget).data("retclass"));
        $("#investButton").html(
          '<a class="btn btn-primary col-12 col-md-10" onclick="AdditionalPurchase(\'' +
            $(event.relatedTarget).data("folionumber") +
            "','" +
            $(event.relatedTarget).data("bsemfschemecode") +
            "','" +
            $(event.relatedTarget).data("trasanctiontype") +
            "','" +
            $(event.relatedTarget).data("rtaagent") +
            "','" +
            $(event.relatedTarget).data("channelproductcode") +
            '\')" type="button"> Invest More</a>'
        );
        var dataunitVal = $(event.relatedTarget).data("unit");
        if (dataunitVal === 0) {
          btnDisabled = "disabled";
        } else {
          btnDisabled = "";
        }
        $("#redeemButton").html(
          '<a class="btn btn-outline-dark py-2 col-12 ' +
            btnDisabled +
            '" type="button" id="redemBtn" onclick="MFRedeem(\'' +
            $(event.relatedTarget).data("folionumber") +
            "','" +
            $(event.relatedTarget).data("bsemfschemecode") +
            "','" +
            $(event.relatedTarget).data("trasanctiontype") +
            "','" +
            $(event.relatedTarget).data("rtaagent") +
            "','" +
            $(event.relatedTarget).data("channelproductcode") +
            "')\"> Redeem</a>"
        );
        $("#modalReturnPer").html(
          $(event.relatedTarget).data("returnpercentage")
        );
        $("#modalindicate").html($(event.relatedTarget).data("indicate"));

        if ($(event.relatedTarget).data("icon") != undefined) {
          document.getElementById("amcicondisplay").src =
            "https://resources.freemi.in/products/resources/images/partnerlogo/mf/" +
            $(event.relatedTarget).data("icon");
          $("amcicondisplay").css("background", "white");
        } else {
          document.getElementById("amcicondisplay").src =
            "https://resources.freemi.in/products/resources/images/partnerlogo/mf/default-amc.png";
          $("amcicondisplay").css("background", "white");
        }
      });
      $(document).ready(function () {
        $("#selectfundmodal").on("hidden.bs.modal", function (event) {
          // $(".modal-body").html("Where did he go?!?!?!");
          $(".returnClass").addClass("text-default");
          $(".returnClass").removeClass("text-success");
          $(".returnClass").removeClass("text-muted");
          $(".returnClass").removeClass("text-danger");
        });
      });
    }
  }
  var tableFolio = document.getElementById("mfdatatbody2");
  tableFolio.innerHTML = htmlElements;
}

function getMfData(profileStatus, pan, mobile) {
  console.log(
    "Request received to get invested data - " + pan + " -> " + profileStatus
  );
  $("#mffetchmsg").text("");
  let checkexpiretime = window.sessionStorage.getItem("expiremfbaldata");
  //	console.log(checkexpiretime);
  if (checkexpiretime != null && new Date() > new Date(checkexpiretime)) {
    console.log("Time expired");
    window.sessionStorage.removeItem("mfbalance");
    window.sessionStorage.removeItem("expiremfbaldata");
  } else {
    //		console.log("Within time or null");
  }

  if (profileStatus === "PROFILE_READY") {
    var storeddata = sessionStorage.getItem("mfbalance");
    //		console.log("storeddata- "+ storeddata);
    if (storeddata == null) {
      request = $.ajax({
        url: "/products/api/mf/getmfportfoliototal",
        method: "POST",
        data: {
          mobile: mobile,
          pan: pan,
        },
        async: true,
        datatype: "json",
        beforeSend: function () {
          displayMfFetchProgress();
        },
      });
      request.done(function (msg) {
        //				console.log(msg);
        if (msg == "NO_SESSION") {
          $("#inval").html("0");
          $("#marketval").html("0");
          $("#mffetchmsg").text("Invalid session. Kindly login again.");
        } else if (msg === "REQUEST_DENIED" || msg === "PAN_INVALID") {
          $("#inval").html("0");
          $("#marketval").html("0");
          $("#mffetchmsg").text("Invalid request.");
        } else if (msg === "NO_DATA") {
          $("#inval").html("0");
          $("#marketval").html("0");
        } else {
          if (typeof Storage !== "undefined") {
            window.sessionStorage.setItem("mfbalance", msg);
            window.sessionStorage.setItem(
              "expiremfbaldata",
              new Date(new Date().getTime() + 60000 * 60)
            );
          } /*else {
					console.log("Unsupported");
					}*/
          var data = JSON.parse(msg);
          if (msg.invvalue != "null") {
            /*$("#inval").text(Number(data.invvalue).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,'));
						$("#marketval").text(Number(data.marketvalue).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,'));*/
            var result;
            result = convertNumberToIndianFormat(
              Number(data.invvalue).toFixed(2)
            );
            $("#inval").text(result);
            result = convertNumberToIndianFormat(
              Number(data.marketvalue).toFixed(2)
            );
            $("#marketval").text(result);
            var returnInv = data.invvalue;
            var returnMarket = data.marketvalue;
            returnVal = Number(returnMarket - returnInv);
            resultRet = convertNumberToIndianFormat(
              Number(returnVal).toFixed(2)
            );
            resultRetPer = (returnVal / returnInv) * 100;
            resultRetPer = resultRetPer.toFixed(2);
            $("#resultRet").text(resultRet);
            $("#resultRetPer").text(resultRetPer + "%");
          }
        }
      });

      request.fail(function (jqXHR, textStatus) {
        //				console.log("Failed to fetch your data. Please try after sometime: " + textStatus);
        $("#inval").html("NaN");
        $("#marketval").html("NaN");
        $("#mffetchmsg").text("*Failed to display your data");
      });

      request.always(function (msg) {});
    } else {
      //			console.log("Read from session-");
      var data = JSON.parse(storeddata);
      if (storeddata.invvalue != "null") {
        var result;
        result = convertNumberToIndianFormat(Number(data.invvalue).toFixed(2));
        $("#inval").text(result);
        result = convertNumberToIndianFormat(
          Number(data.marketvalue).toFixed(2)
        );
        $("#marketval").text(result);
        var returnInv = data.invvalue;
        var returnMarket = data.marketvalue;
        returnVal = Number(returnMarket - returnInv);
        resultRet = convertNumberToIndianFormat(Number(returnVal).toFixed(2));
        resultRetPer = (returnVal / returnInv) * 100;
        resultRetPer = resultRetPer.toFixed(2);
        $("#resultRet").text(resultRet);
        $("#resultRetPer").text(resultRetPer + "%");
      }
    }
  } else {
    //		console.log("Profile not ready..");
  }
}

function displayMfFetchProgress() {
  $("#inval").html('<i class="fas fa-spinner fa-spin"></i>');
  $("#marketval").html('<i class="fas fa-spinner fa-spin"></i>');
  $("#resultRet").html('<i class="fas fa-spinner fa-spin"></i>');
  $("#resultRetPer").html('<i class="fas fa-spinner fa-spin"></i>');
}

function showtaskProgress(elementid) {
  $("#" + elementid).html('PROCESSING <i class="fas fa-spinner fa-spin"></i>');
  $("#" + elementid).attr("disabled", "disabled");
}

function completetaskProgress(elementid, msg) {
  $("#" + elementid).html(msg);
  $("#" + elementid).removeAttr("disabled");
}

function convertNumberToIndianFormat(value) {
  //	console.log("Called- "+ value);
  var res;
  var x = value;
  x = x.toString();
  var afterPoint = "";
  if (x.indexOf(".") > 0) afterPoint = x.substring(x.indexOf("."), x.length);
  x = Math.floor(x);
  x = x.toString();
  var lastThree = x.substring(x.length - 3);
  var otherNumbers = x.substring(0, x.length - 3);
  if (otherNumbers != "") lastThree = "," + lastThree;
  var res =
    otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ",") + lastThree + afterPoint;
  //	console.log(res);
  return res;
}

$(document).ready(function () {
  $("#sipcancelform").submit(function (e) {
    e.preventDefault();
    console.log("Submitted");

    request = $.ajax({
      url: "/products/api/mutual-funds/cancel-sip",
      method: "POST",
      data: JSON.stringify({
        orderno: $("#orderno").val(),
        clientid: $("#formclientid").val(),
        transactionid: $("#siptransactionid").val(),
      }),
      async: true,
      datatype: "json",
      contentType: "application/json",
      beforeSend: function () {
        $("#cancelsubmitbtn").html(
          'Processing <i class="fas fa-spinner fa-spin"></i>'
        );
        $("#cancelsubmitbtn").attr("disabled", "disabled");
      },
    });
    request.done(function (msg) {
      //			console.log(msg);
      if (msg == "SUCCESS") {
        $("#cancelresponse").text("SIP Cancelled Successfully");
      }
      if (msg == "NO_SESSION") {
        $("#cancelresponse").text("Invalid session. Kindly login again.");
      } else if (msg === "REQUEST_DENIED" || msg === "PAN_INVALID") {
        $("#cancelresponse").text("Invalid request.");
      } else if (msg === "NO_DATA") {
        $("#cancelresponse").text("Invalid data");
      } else {
        $("#cancelresponse").text(msg);
      }
    });

    request.fail(function (jqXHR, textStatus) {
      $("#cancelresponse").text("Failed to process your request.");
    });

    request.always(function (msg) {
      $("#cancelsubmitbtn").html("Cancel SIP");
      $("#cancelsubmitbtn").removeAttr("disabled");
    });
  });
});

function getfdbalance(mobileno, pan) {
  fdapifundcall(mobileno, pan, "BALANCE");
}

function calculatefdtotals(result) {
  console.log("Call received to generate balance view.." + result);
  var msg = result;
  if (msg != "undefined" || msg != undefined) {
    if (msg === "NO_SESSION") {
      $("#balancecheckid").html(
        "Refresh Balance  &nbsp;&nbsp;<i class='fas fa-redo-alt'></i>"
      );
      $("#fdfetchmsg").text("Invalid session. Kindly login again.");
    } else if (msg === "NO_PAN") {
      $("#balancecheckid").html(
        "Refresh Balance  &nbsp;&nbsp;<i class='fas fa-redo-alt'></i>"
      );
      $("#fdfetchmsg").text("Invalid request. ");
    } else if (msg === "NO_DATA") {
      $("#balancecheckid").html(
        "Refresh Balance  &nbsp;&nbsp;<i class='fas fa-redo-alt'></i>"
      );
      $("#fdfetchmsg").text("No savings found!");
      $("#balancecheckid").html(
        "Refresh Balance  &nbsp;&nbsp;<i class='fas fa-redo-alt'></i>"
      );
    } else if (
      msg === "INAVLID_REQUEST" ||
      msg === "INTERNAL_ERROR" ||
      msg === "API_ERROR"
    ) {
      $("#balancecheckid").html(
        "Refresh Balance  &nbsp;&nbsp;<i class='fas fa-redo-alt'></i>"
      );
      $("#fdfetchmsg").text("Request could not be processed.");
    } else {
      $("#fdfetchmsg").text("");

      var data = JSON.parse(result);
      console.log("Tranasaction Data- " + data.length + " -> " + data);
      let amount = 0;
      let matured = 0;
      for (var i = 0; i < data.length; i++) {
        var x = data[i];
        if (x.DEP_STATUS_CODE == "L") {
          amount += x.PRINC_AMT;
          matured += x.MATU_AMT;
        }
      }

      // $("#balancedata").html("<div class='col-12 mx-auto'><div class='row animated fadeIn'><div class='col-6'>" +
      // 		"<h6>TOTAL FD SAVINGS</h6></div><div class='col-6'><h5><i class='fas fa-rupee-sign'> </i> <span id='totalfdprincipal'>"+convertNumberToIndianFormat(amount)+" </span></h5></div></div>" +
      // 		"<div class='row animated fadeIn'><div class='col-6'>" +
      // 		"<h6>TOTAL MATURITY</h6></div><div class='col-6'><h5><i class='fas fa-rupee-sign'> </i> <span id='totalfdmaurity'>"+convertNumberToIndianFormat(matured)+"</span></h5></div></div></div>");
      $("#totalfdprincipal").text(convertNumberToIndianFormat(amount));
      $("#totalfdmaurity").text(convertNumberToIndianFormat(matured));
    }
  } else {
    $("#balancecheckid").html(
      "Refresh Balance  &nbsp;&nbsp;<i class='fas fa-redo-alt'></i>"
    );
    $("#fdfetchmsg").text("Unable to process request.");
  }
}

function getfixeddepositdata(mobileno, pan) {
  console.log("Call receivedfor FD fetch...");

  fdapifundcall(mobileno, pan, "CREATEVIEW");

  /*	console.log("Response from api- "+ msg);
		if(msg=="NO_SESSION"){
			$("#fdfetch").html("Session expired. Please login again");
		}else if(msg==="NO_PAN"){
		}else if(msg==="NO_DATA"){
			$("#fdfetch").html("<a href=\"/fixed-deposit\"><button class=\"btn btn-sm\"><img src=\"https://resources.freemi.in/products/resources/images/invest/investment4.png\" class=\"img-fluid\" style=\"height: 2rem;\"> Start Saving</button> </a>");
		}else if(msg==="INAVLID_REQUEST"){
			$("#fdfetch").html("Failed to process request. Please contact admin if issue persist.");
		}else if(msg==="INTERNAL_ERROR" || msg==="API_ERROR"){
			$("#fdfetch").html("Error processing request. Please try again after sometime");
		}else{
			let result1=JSON.stringify(msg);
			if (typeof(Storage) !== "undefined") {
				window.sessionStorage.setItem("fdfolios",result1);
			} 
			console.log(msg);

			createfddataView(result1);
		}
	 */
}

function fdapifundcall(mobileno, pan, postcallfunc) {
  var apiresponse;
  var fdtempdata = sessionStorage.getItem("fdfolios");
  if (fdtempdata == null) {
    request = $.ajax({
      url: "/products/api/fixed-deposit/get-fd-portfolios",
      method: "POST",
      data: {
        mobile: mobileno,
        pan: pan,
      },
      async: true,
      datatype: "json",
      beforeSend: function () {
        if (postcallfunc === "BALANCE") {
          $("#balancecheckid").html(
            'Processing <i class="fas fa-spinner fa-spin"></i>'
          );
        } else {
          showprogress("fdfetch");
        }
      },
    });
    request.done(function (msg) {
      console.log("RECEIVED- " + msg);
      apiresponse = msg;
      //let result1=JSON.stringify(msg);
      if (
        msg == "NO_SESSION" ||
        msg === "NO_PAN" ||
        msg === "NO_DATA" ||
        msg === "INAVLID_REQUEST" ||
        msg === "INTERNAL_ERROR" ||
        msg === "API_ERROR"
      ) {
        console.log("Response not success..");
      } else {
        apiresponse = JSON.stringify(msg);
        if (typeof Storage !== "undefined") {
          window.sessionStorage.setItem("fdfolios", apiresponse);
        }
      }

      if (postcallfunc === "BALANCE") {
        calculatefdtotals(apiresponse);
      } else if (postcallfunc === "CREATEVIEW") {
        createfddataView(apiresponse);
      }
    });
    request.fail(function (jqXHR, textStatus) {
      console.log(
        "Failed to fetch your data. Please try after sometime: " + textStatus
      );
      apiresponse = "API_ERROR";
    });

    request.always(function (msg) {
      console.log("Request complete..");
      if (postcallfunc === "BALANCE") {
        $("#balancecheckid").html(
          "Refresh Balance  &nbsp;&nbsp;<i class='fas fa-sync-alt'></i>"
        );
      } else {
        //			progresscomplete("fdfetch");
      }
    });
  } else {
    apiresponse = fdtempdata;

    if (postcallfunc === "BALANCE") {
      calculatefdtotals(apiresponse);
    } else if (postcallfunc === "CREATEVIEW") {
      createfddataView(apiresponse);
    }
  }
}

function createfddataView(result) {
  console.log("Request received to generate folio view...");
  $("#fddatabody").children().remove();

  if (result == "NO_SESSION") {
    $("#fdMsg").html("Session expired. Please login again");
    $("#fdfetch").html("");
  } else if (result === "NO_PAN") {
    $("#fdMsg").html(
      "PAN record not present. Please contact admin if you carried out transaction."
    );
    $("#fdfetch").html("");
  } else if (result === "NO_DATA") {
    $("#fdMsg").html("No savings record found.");
    $("#fdfetch").html(
      '<a href="/fixed-deposit/"><button class="btn btn-load-more">Start Saving <i class="fas fa-wallet"></i></button> </a>'
    );
  } else if (result === "INAVLID_REQUEST") {
    $("#fdfetch").html(
      "Failed to process request. Please contact admin if issue persist."
    );
  } else if (result === "INTERNAL_ERROR" || result === "API_ERROR") {
    $("#fdfetch").html(
      "Error processing request. Please try again after sometime"
    );
  } else {
    var data = JSON.parse(result);
    console.log("Tranasaction Data- " + data.length + " -> " + data);

    var table = document.getElementById("fddatabody");
    table.setAttribute("class", "animated fadeIn");
    console.log("Total records- " + data.length + " -> " + data);
    for (var i = 0; i < data.length; i++) {
      /*    console.log(jsonData[i]); */

      var x = data[i];

      var row = table.insertRow();
      var cell1 = row.insertCell(0);
      var cell2 = row.insertCell(1);
      var cell3 = row.insertCell(2);
      var cell4 = row.insertCell(3);
      var cell5 = row.insertCell(4);
      var cell6 = row.insertCell(5);
      var cell7 = row.insertCell(6);
      var cell8 = row.insertCell(7);

      //			Add some text to the new cells:

      cell1.innerHTML =
        '<img src="https://resources.freemi.in/products/resources/images/invest/mahindra-finance-icon.png" class="img-fluid mr-2" >';
      cell2.innerHTML = x.FOLIO;
      cell3.innerHTML = x.FDR_NO;
      cell4.innerHTML = x.APL_NO;
      cell5.innerHTML = convertNumberToIndianFormat(x.PRINC_AMT);
      cell6.innerHTML =
        convertNumberToIndianFormat(x.MATU_AMT) +
        "<br>" +
        "<small class='text-muted'>(On " +
        x.MATU_DATE +
        ")</small>";
      cell7.innerHTML = x.DEP_STATUS_DESC;
      cell8.innerHTML =
        '<span class="text-danger" type="button" style="font-size: 12px; color: #238019; font-weight: 600;" data-toggle=\'modal\' ' +
        "data-fdrno='" +
        x.FDR_NO +
        "'" +
        "data-appl='" +
        x.APL_NO +
        "'" +
        "data-folio='" +
        x.FOLIO +
        "'" +
        "data-invname='" +
        x.INVESTOR_NAME +
        "'" +
        "data-fdrdate='" +
        x.FDR_DATE +
        "'" +
        "data-principal='" +
        x.PRINC_AMT +
        "'" +
        "data-maturity='" +
        x.MATU_AMT +
        "'" +
        "data-maturitydate='" +
        x.MATU_DATE +
        "'" +
        "data-schemetype='" +
        x.SCHEMETYPE_CODE +
        "'" +
        "data-tenure='" +
        x.PERIOD_MM +
        "'" +
        "data-intrate='" +
        x.INT_RATE +
        "'" +
        "data-renewaleligibility='" +
        x.RENEWAL_ELIGIBILITY +
        "'" +
        "data-comments='" +
        x.CONDITIONAL_REASON +
        "'" +
        "' data-target='#fddetailsmodal'> Details</button>";
    }

    progresscomplete("fdfetch");
  }
}

/* ------------------------------------------------------------------------------------------------- */
function gethdfcloanrequestlist(mobileno, pan) {
  console.log("Call receivedfor FD fetch...");
  $("#loanreqmsg").html("");
  loanapilistcall(mobileno, pan, "CREATEVIEW");
}

function loanapilistcall(mobileno, pan, postcallfunc) {
  var apiresponse;
  var loantempdata = sessionStorage.getItem("loanrequestlist");
  if (loantempdata == null) {
    request = $.ajax({
      url: "/products/api/loan/get-hdfc-loan",
      method: "POST",
      data: {
        mobile: mobileno,
        pan: pan,
      },
      async: true,
      datatype: "json",
      beforeSend: function () {
        showprogress("loanfetch");
      },
    });
    request.done(function (msg) {
      apiresponse = msg;
      apiresponse = JSON.stringify(msg);
      //			console.log("RECEIVED- "+ apiresponse);
      createloandataView(msg);
    });
    request.fail(function (jqXHR, textStatus) {
      console.log(
        "Failed to fetch your data. Please try after sometime: " + textStatus
      );
      apiresponse = "API_ERROR";
    });

    request.always(function (msg) {
      console.log("Request complete..");
      //			progresscomplete("loanfetch");
    });
  } else {
    apiresponse = loantempdata;

    if (postcallfunc === "BALANCE") {
      //			calculatefdtotals(apiresponse);
    } else if (postcallfunc === "CREATEVIEW") {
      createloandataView(apiresponse);
    }
  }
}

function createloandataView(result) {
  console.log("Request received to display loan data");
  $("#loanlistbody").children().remove();

  if (result.status == "0") {
    $("#loanfetch").html(
      '<button class="btn btn-load-more" onclick="gethdfcloanrequestlist(reqid);">Fetch your Loan Requests</button>'
    );
    if (result.msg == "NO_SESSION") {
      $("#loanreqmsg").html("Session expired. Please login again");
    } else if (result.msg === "NO_PAN") {
      $("#loanreqmsg").html(
        "PAN record not present. Please contact admin if you carried out transaction."
      );
    } else if (result.msg === "NO_DATA") {
      $("#loanreqmsg").html(
        '<p>No loan request record.</p><a href="/fixed-deposit/"><button class="btn btn-sm btn-primary">Apply for HDFC Loan</button> </a>'
      );
    } else if (result.msg === "INAVLID_REQUEST") {
      $("#loanreqmsg").html(
        "Failed to process request. Please contact admin if issue persist."
      );
    } else if (result.msg === "SERVICE_CONN_FAILURE") {
      $("#loanreqmsg").html("Failed to fetch data. Please try after sometime.");
    } else if (result.msg === "INTERNAL_ERROR" || result.msg === "API_ERROR") {
      $("#loanreqmsg").html(
        "Error processing request. Please try again after sometime"
      );
    }
  } else {
    //		var data = JSON.parse(result);
    //		console.log("Tranasaction Data- " +data.data.length + " -> " + data.data);
    if (result.data != null && result.data.length > 0) {
      var table = document.getElementById("loanlistbody");
      table.setAttribute("class", "animated fadeIn");
      //		console.log("Total records- " + data.data.length + " -> "+ data.data);
      for (var i = 0; i < result.data.length; i++) {
        /*    console.log(jsonData[i]); */

        var x = result.data[i];
        var row = table.insertRow();
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);
        var cell5 = row.insertCell(4);
        var cell6 = row.insertCell(5);
        var cell7 = row.insertCell(6);
        var cell8 = row.insertCell(7);
        var cell9 = row.insertCell(8);
        //			Add some text to the new cells:

        cell1.innerHTML =
          '<img src="https://resources.freemi.in/products/resources/images/partnerlogo/loans/hdfc-bank.svg" class="img-fluid mr-2" style="height: 1rem" >';
        cell2.innerHTML = x[1];
        cell3.innerHTML = x[0];
        cell4.innerHTML = x[2];
        cell5.innerHTML = "&#8377;" + convertNumberToIndianFormat(x[3]);
        cell6.innerHTML = x[11];
        cell7.innerHTML = x[8];
        cell8.innerHTML = x[9];

        if (x[7] == "Y") {
          cell9.innerHTML =
            '<button class="btn btn-sm btn-success p-1 mt-0 loanact" type="button" style="font-size: 12px; width: 5rem;" data-action=\'STAT\' data-bank=\'HDFC\' data-refno=\'' +
            x[0] +
            "' data-ackno='" +
            x[2] +
            "' data-applno='" +
            x[11] +
            "'> Status</button>";
        } else if (x[7] == "Incomplete") {
          cell9.innerHTML =
            '<button class="btn btn-sm btn-primary p-1 mt-0 loanact" type="button" style="font-size: 12px; width: 5rem;" data-action=\'COMP\' data-bank=\'HDFC\' data-refno=\'' +
            x[0] +
            "' data-ackno='" +
            x[2] +
            "' data-applno='" +
            x[2] +
            "'> Complete</button>";
        } else {
          cell9.innerHTML = "";
        }
      }
      progresscomplete("loanfetch");
    } else {
      $("#loanfetch").html(
        '<a href="/hdfc-loan" class="btn btn-sm btn-primary"">Apply HDFC Loan</button>'
      );
      $("#loanreqmsg").html("No loan history. Apply for personal loan  bank.");
    }
  }
}

$(document.body).on("click", ".loanact", function () {
  //    alert($(this).data('action') + " - " + $(this).data('refno') + "- " + $(this).data('ackno'));
  if (!loanhandler) {
    loanhandler = true;
    console.log("Clikced- " + $(this));
    if ($(this).data("action") == "STAT") {
      getloanrequestcurrentstat(
        $(this),
        $(this).data("bank"),
        $(this).data("refno"),
        $(this).data("ackno"),
        $(this).data("appno"),
        reqid
      );
      //			getloanrequestcurrentstat($(this))
    } else if ($(this).data("action") == "COMP") {
      completeloanrequest(
        $(this),
        $(this).data("bank"),
        $(this).data("refno"),
        $(this).data("ackno"),
        $(this).data("appno"),
        reqid
      );
    } else {
      alert("Invalid action!");
    }
  } else {
    alert("Last action is still under progress");
  }
});

function getloanrequestcurrentstat(
  event,
  bank,
  loanref,
  ackno,
  appno,
  mobileno
) {
  //function getloanrequestcurrentstat(event){
  var apiresponse;
  var temptext;
  request = $.ajax({
    url: "/products/api/loan/get-loan-status",
    method: "POST",
    data: {
      mobile: mobileno,
      refno: loanref,
      ackno: ackno,
      appno: appno,
      bank: bank,
    },
    async: true,
    datatype: "json",
    beforeSend: function () {
      //			showprogress("loanfetch");
      temptext = event.text();
      console.log(temptext);
      event.html('Wait <i class="fas fa-spinner fa-spin"></i>');
    },
  });
  request.done(function (msg) {
    apiresponse = msg;
    //		statusobj=apiresponse;
    console.log("RECEIVED- " + apiresponse);
    createloanstatusview(msg);
  });
  request.fail(function (jqXHR, textStatus) {
    console.log(
      "Failed to fetch your data. Please try after sometime: " + textStatus
    );
    alert("Your request failed! Kindly try after sometime.");
    apiresponse = "API_ERROR";
  });

  request.always(function (msg) {
    console.log("Request complete..");
    event.html(temptext);
    loanhandler = false;
  });
}

function completeloanrequest(event, bank, loanref, ackno, appno, mobileno) {
  console.log("Complete loan request post submit");
  $("#completeloanreqmodal").modal("show");
  $("#mobno").val(mobileno);
  $("#reqbank").val(bank);
  $("#ackno").val(ackno);
  $("#applno").val(appno);
  $("#action").val("COMPLETE_REQUEST");
  $("#loanrefno").val(loanref);
  $("#loanapplystatus").val("Incomplete");
}

function createloanstatusview(msg) {
  $("#loanstatusmodal").modal("show");
  if (msg.applicationstatus == "Success") {
    if (msg.loanstatus != null) {
      $("#loancustomername").html(
        msg.loanstatus.getStatusEnquiryResponse.getStatusEnquiryResult.CUSTNAME
      );
      $("#loanapplno").html(msg.applicaitonid);
      $("#loanapplstatus").html(
        msg.loanstatus.getStatusEnquiryResponse.getStatusEnquiryResult.status
      );
    } else {
      $("loanstatusmsg").text(
        "Status data could not fetched. Please try after sometime"
      );
    }
  } else {
    $("loanstatusmsg").text("The application was not successful.");
  }
}

/* --------------------------------------------------------------------------------------------------------- */

function showprogress(elementid) {
  $("#" + elementid).html(
    '<div style="text-align: center;margin-bottom: 3rem;"><img alt="Fetching your portfolio" src="https://resources.freemi.in/products/resources/images/invest/progress2.gif">'
  );
}

function progresscomplete(elementid) {
  $("#" + elementid).html("");
}

function retryaofupload(mobile) {
  var token = $('input[name="_csrf"]').attr("value");
  var jsonObjects = JSON.stringify({
    action: "aof_upload",
    mobile: mobile,
    module: "MF",
    submodule: "REGISTRATION_RETRY_INCOMPLETE",
  });

  $.ajaxSetup({
    headers: { "X-CSRF-TOKEN": token },
  });

  request = $.ajax({
    url: "/products/mutual-funds/uploadsignedaof",
    method: "POST",
    data: jsonObjects,
    async: true,
    datatype: "json",
    contentType: "application/json",
    beforeSend: function () {
      //$("#retryaofupload").text("<span>Processing <i class=\"fas fa-spinner fa-spin\"></i></span>");
      //console.log("Before send");
      document.getElementById("retryaofupload").innerHTML =
        'Processing <i class="fas fa-spinner fa-spin"></i>';
      $("#retryaofupload").prop("disabled", true);
    },
  });

  request.done(function (data) {
    if (data.responseCode == 100) {
      $("#signuploadstatus").text(
        "Your AOF uploaded successfully. Registration process complete."
      );
      $("#signuploadstatus").css("color", "green");
      $("#aofuploadbtn").hide();
      $("#purchasecon").removeAttr("hidden");
    } else {
      $("#exampleModal1").modal("hide");
      $("#signuploadstatus").text(data.retrunMessage);
      $("#signuploadstatus").css("color", "red");
    }
  });

  request.fail(function (jqXHR, textStatus) {
    $("#exampleModal1").modal("hide");
    $("#signuploadstatus").text("Failed to make request. Please try again.");
  });

  request.always(function (msg) {
    $("#retryaofupload").html("Retry AOF Upload");
    $("#retryaofupload").prop("disabled", false);
  });
}

$("#fddetailsmodal").on("show.bs.modal", function (event) {
  //	console.log("Modal called")
  $("#nbscid").text("Mahindra Finance");
  if ($(event.relatedTarget).attr("data-fdrno") != undefined) {
    $("#fdrnoid").text($(event.relatedTarget).attr("data-fdrno"));
  }
  if ($(event.relatedTarget).attr("data-invname") != undefined) {
    $("#invnameid").text($(event.relatedTarget).attr("data-invname"));
  }
  if ($(event.relatedTarget).attr("data-fdrdate") != undefined) {
    $("#purchasedateid").text($(event.relatedTarget).attr("data-fdrdate"));
  }
  if ($(event.relatedTarget).attr("data-principal") != undefined) {
    $("#principalamntid").text(
      convertNumberToIndianFormat($(event.relatedTarget).attr("data-principal"))
    );
  }
  if ($(event.relatedTarget).attr("data-schemetype") != undefined) {
    let type = $(event.relatedTarget).attr("data-schemetype");
    if (type == "C") {
      $("#schemetypeid").text("Cumulative");
    } else if (type == "NC") {
      $("#schemetypeid").text("Non Cumulative");
    } else {
      $("#schemetypeid").text(type);
    }
  }
  if ($(event.relatedTarget).attr("data-tenure") != undefined) {
    $("#fdtenureid").text(
      $(event.relatedTarget).attr("data-tenure") + " months"
    );
  }
  if ($(event.relatedTarget).attr("data-intrate") != undefined) {
    $("#purchaserateid").text($(event.relatedTarget).attr("data-intrate"));
  }
  if ($(event.relatedTarget).attr("data-maturity") != undefined) {
    $("#maturityamountid").text(
      convertNumberToIndianFormat($(event.relatedTarget).attr("data-maturity"))
    );
  }
  if ($(event.relatedTarget).attr("data-maturitydate") != undefined) {
    $("#maturitydate").text($(event.relatedTarget).attr("data-maturitydate"));
  }
});
