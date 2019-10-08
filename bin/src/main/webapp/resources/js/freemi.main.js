	$(document).ready(function() {
		$(".loader").fadeOut("slow");
		 $("label[name='sipm']").hide();
		
		// $('#birthdayhrf').removeAttr("href");
		 $("#birthdayhrf").addClass('disabled').removeAttr("href");
		 $("#marriagehrf").addClass('disabled').removeAttr("href");
		 $("#taxsavinghrf").addClass('disabled').removeAttr("href");
		 $("#annivhrf").addClass('disabled').removeAttr("href");
		 $("#partieshrf").addClass('disabled').removeAttr("href");
		 $("#retirementhrf").addClass('disabled').removeAttr("href");
		 $("#childeduhrf").addClass('disabled').removeAttr("href");
		 $("#travelhrf").addClass('disabled').removeAttr("href");
		 $("#plhrf").addClass('disabled').removeAttr("href");
		 $("#weedgfthrf").addClass('disabled').removeAttr("href");
		 $("#officeexprf").addClass('disabled').removeAttr("href");
		 $("#othershrf").addClass('disabled').removeAttr("href");
		
// alert("Load")
   	});
	 $('#eventdate').change(function() {
		 var eventdate = new Date($("#eventdate").val());
			eventdate = eventdate.toDateString("yyyy-mm-dd");
			var today = new Date().toDateString("yyyy-mm-dd");
		    //console.log(eventdate+"::;"+today);
			var day=daysBetween(new Date(today),new Date(eventdate));
			var month = Math.floor(day/30);
			if(month>0){
				//console.log(month);
				 document.getElementById("final-sip-amount").innerHTML ="";
				 document.getElementById("final-installment-amount").innerHTML = "";
				 calculate(month);
			}
		 
	 });
	
	function calculate(month){
		
		 var investmenttype = $("#investmenttype").val();
		 //console.log(investmenttype);
		 if(investmenttype == 'SIPT'){
			 calculateInstallmentAmount(month);
		 }else if(investmenttype == 'SIPM'){
			 calculateMaturityAmount(month);
		 }else{
			
			 
		 }
	}
	     function calculateMaturityAmount(t){
	    	 /*
	       var p = document.getElementById("principalMA").value;
	      // var t = document.getElementById("timeMA").value;
	       var n = document.getElementById("compoundMA").value;
	       var r = document.getElementById("interestMA").value;
	       */
	       
	       var p = $("#amount_sip").val();
	       // var t = document.getElementById('timeIA').value;
	        var n = $("#inputGroupSelect01").val();
	        var r = 6.5;//document.getElementById('interestIA').value;

	       /*console.log(p);
	       console.log(t);
	       console.log(n);
	       console.log(r);*/

	       var amount_array = [];
	       var x = calculate_x(r,n);
	       
	       for (var i = t; i>=1; i--){
	         a = p*Math.pow(x,n*calculate_months_in_year(i));
	         //console.log(a);
	         amount_array.push(a);
	       }
	       var maturity_amount = amount_array.reduce(function(previousValue, currentValue, index, array) {
	         return previousValue + currentValue;
	       });
	       //console.log(maturity_amount);
	       document.getElementById("final-sip-amount").innerHTML = p*t
	       document.getElementById("final-installment-amount").innerHTML = Math.round(maturity_amount);
	     }

	     // var x = (1+r/n)
	     function calculate_x(r,n){
	       var x =  1+(r/100)/n;
	       //console.log(x);
	       return x;
	     }
	     // var y = (1+r/n)^nt
	     // function calculate_y(x,n,t){
	     //  return Math.pow(x,n*calculate_months_in_year(t));
	     // }

	     // var t_in_y = t/12; time expressed in year
	     function calculate_months_in_year(t){
	       return t/12;
	     }

	     function clearMAForm(){
	       var form = document.getElementById("maCalculator");
	       form.reset();
	       // document.getElementById("final-maturity-amount").innerHTML = '<i class="fa fa-inr "></i>';
	     }
	     function clearIAForm(){
	       var form = document.getElementById("iaCalculator");
	       form.reset();
	       // document.getElementById("final-installment-amount").innerHTML = '<i class="fa fa-inr "></i>';
	     }

	     function calculateInstallmentAmount(t ){

	       var a = document.getElementById('amount_sip').value;
	       console.log("amount_sip="+a);
	      // var t = document.getElementById('timeIA').value;
	       var n = document.getElementById('inputGroupSelect01').value;
	       var r = 6.5;//document.getElementById('interestIA').value;
	       var x = calculate_x(r,n);
	       var z = 0; 
	       for (var i =t; i>=1; i--){
	         z = z + Math.pow(x,n*calculate_months_in_year(i));
			   console.log(z);
	       }

	       var principal_amount = Math.round(a/z);
	       document.getElementById("final-installment-amount").innerHTML = a;// Math.round(principal_amount);
	       document.getElementById("final-sip-amount").innerHTML = Math.round(principal_amount);
//	      console.log(principal_amount)

	     }
	     function daysBetween( date1, date2 ) {   //Get 1 day in milliseconds   
	    	 var one_day=1000*60*60*24;    // Convert both dates to milliseconds
	    	 var date1_ms = date1.getTime();   
	    	 var date2_ms = date2.getTime();    // Calculate the difference in milliseconds  
	    	 var difference_ms = date2_ms - date1_ms;        // Convert back to days and return   
	    	 return Math.round(difference_ms/one_day); 
	   } 
	  $('#investmenttype').click(function() {	
		  var selecttype = $(this).val();
		  if(selecttype=="SIPM"){
			  $("label[name='sipt']").hide();
			  $("label[name='sipm']").show();
		  }else{
			  $("label[name='sipm']").hide();
			  $("label[name='sipt']").show();
		  }
		//  alert(selecttype);
		  
	  });
	$("input[name='scheme']").click(function() {
		 //alert("Click Radio button");
		 $("#birthdayhrf").removeClass("disabled").attr("href", "#");
		 $("#marriagehrf").removeClass("disabled").attr("href", "#");
		 $("#taxsavinghrf").removeClass("disabled").attr("href", "#");
		 $("#annivhrf").removeClass("disabled").attr("href", "#");
		 $("#partieshrf").removeClass("disabled").attr("href", "#");
		 $("#retirementhrf").removeClass("disabled").attr("href", "#");
		 $("#childeduhrf").removeClass("disabled").attr("href", "#");
		 $("#travelhrf").removeClass("disabled").attr("href", "#");
		 $("#plhrf").removeClass("disabled").attr("href", "#");
		 $("#weedgfthrf").removeClass("disabled").attr("href", "#");
		 $("#officeexprf").removeClass("disabled").attr("href", "#");
		 $("#othershrf").removeClass("disabled").attr("href", "#");
		
	 });
	
	  $('#birthdayhrf').click(function() {		
		  var radioValue = $("input[name='scheme']:checked").val();
		 var hrefVal="/products/registry-mutual-funds/registry-wish?category=family&type=birthday&schemeId="+radioValue;
		 
		 $(this).attr("href", hrefVal);
	    //alert("hrefVal="+hrefVal);
	  });
	  
	  $('#marriagehrf').click(function() {		
		  var radioValue = $("input[name='scheme']:checked").val();
		 var hrefVal="/products/registry-mutual-funds/registry-wish?category=family&type=marriage&schemeId="+radioValue;
		//alert("hrefVal="+hrefVal);
		 $(this).attr("href", hrefVal);
	    //alert("hrefVal="+hrefVal);
	  });
	  
	  $('#taxsavinghrf').click(function() {		
		  var radioValue = $("input[name='scheme']:checked").val();
		 var hrefVal="/products/tax-calculator/?schemeId="+radioValue;
		//alert("hrefVal="+hrefVal);
		 $(this).attr("href", hrefVal);
	    //alert("hrefVal="+hrefVal);
	  });

	  $('#annivhrf').click(function() {		
		 var radioValue = $("input[name='scheme']:checked").val();
		 var hrefVal="/products/registry-mutual-funds/registry-wish?category=family&type=marriage&schemeId="+radioValue;
		//alert("hrefVal="+hrefVal);
		 $(this).attr("href", hrefVal);
	    //alert("hrefVal="+hrefVal);
	  });
	  
	  $('#partieshrf').click(function() {		
		  var radioValue = $("input[name='scheme']:checked").val();
		 var hrefVal="/products/registry-mutual-funds/registry-wish?category=family&type=marriage&schemeId="+radioValue;
		//alert("hrefVal="+hrefVal);
		 $(this).attr("href", hrefVal);
	    //alert("hrefVal="+hrefVal);
	  });
	  
	  $('#retirementhrf').click(function() {		
		  var radioValue = $("input[name='scheme']:checked").val();
		 var hrefVal="/products/registry-mutual-funds/registry-wish?category=family&type=marriage&schemeId="+radioValue;
		//alert("hrefVal="+hrefVal);
		 $(this).attr("href", hrefVal);
	    //alert("hrefVal="+hrefVal);
	  });
	  
	  $('#childeduhrf').click(function() {		
		  var radioValue = $("input[name='scheme']:checked").val();
		 var hrefVal="/products/registry-mutual-funds/registry-wish?category=family&type=marriage&schemeId="+radioValue;
		//alert("hrefVal="+hrefVal);
		 $(this).attr("href", hrefVal);
	    //alert("hrefVal="+hrefVal);
	  });
	  $('#travelhrf').click(function() {		
		  var radioValue = $("input[name='scheme']:checked").val();
		 var hrefVal="/products/registry-mutual-funds/registry-wish?category=family&type=marriage&schemeId="+radioValue;
		//alert("hrefVal="+hrefVal);
		 $(this).attr("href", hrefVal);
	    //alert("hrefVal="+hrefVal);
	  });
	  $('#plhrf').click(function() {		
		  var radioValue = $("input[name='scheme']:checked").val();
		 var hrefVal="/products/registry-mutual-funds/registry-wish?category=family&type=marriage&schemeId="+radioValue;
		//alert("hrefVal="+hrefVal);
		 $(this).attr("href", hrefVal);
	    //alert("hrefVal="+hrefVal);
	  });
	  $('#weedgfthrf').click(function() {		
		  var radioValue = $("input[name='scheme']:checked").val();
		 var hrefVal="/products/registry-mutual-funds/registry-wish?category=family&type=marriage&schemeId="+radioValue;
		//alert("hrefVal="+hrefVal);
		 $(this).attr("href", hrefVal);
	    //alert("hrefVal="+hrefVal);
	  });
	  $('#officeexprf').click(function() {		
		  var radioValue = $("input[name='scheme']:checked").val();
		 var hrefVal="/products/registry-mutual-funds/registry-wish?category=family&type=marriage&schemeId="+radioValue;
		//alert("hrefVal="+hrefVal);
		 $(this).attr("href", hrefVal);
	    //alert("hrefVal="+hrefVal);
	  });
	  $('#othershrf').click(function() {		
		  var radioValue = $("input[name='scheme']:checked").val();
		 var hrefVal="/products/registry-mutual-funds/registry-wish?category=family&type=marriage&schemeId="+radioValue;
		//alert("hrefVal="+hrefVal);
		 $(this).attr("href", hrefVal);
	    //alert("hrefVal="+hrefVal);
	  });
	  
	  
	/***
	 * wow.js Start
	 */
	(function() {
		  var MutationObserver, Util, WeakMap, getComputedStyle, getComputedStyleRX,
		    bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; },
		    indexOf = [].indexOf || function(item) { for (var i = 0, l = this.length; i < l; i++) { if (i in this && this[i] === item) return i; } return -1; };

		  Util = (function() {
		    function Util() {}

		    Util.prototype.extend = function(custom, defaults) {
		      var key, value;
		      for (key in defaults) {
		        value = defaults[key];
		        if (custom[key] == null) {
		          custom[key] = value;
		        }
		      }
		      return custom;
		    };

		    Util.prototype.isMobile = function(agent) {
		      return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(agent);
		    };

		    Util.prototype.createEvent = function(event, bubble, cancel, detail) {
		      var customEvent;
		      if (bubble == null) {
		        bubble = false;
		      }
		      if (cancel == null) {
		        cancel = false;
		      }
		      if (detail == null) {
		        detail = null;
		      }
		      if (document.createEvent != null) {
		        customEvent = document.createEvent('CustomEvent');
		        customEvent.initCustomEvent(event, bubble, cancel, detail);
		      } else if (document.createEventObject != null) {
		        customEvent = document.createEventObject();
		        customEvent.eventType = event;
		      } else {
		        customEvent.eventName = event;
		      }
		      return customEvent;
		    };

		    Util.prototype.emitEvent = function(elem, event) {
		      if (elem.dispatchEvent != null) {
		        return elem.dispatchEvent(event);
		      } else if (event in (elem != null)) {
		        return elem[event]();
		      } else if (("on" + event) in (elem != null)) {
		        return elem["on" + event]();
		      }
		    };

		    Util.prototype.addEvent = function(elem, event, fn) {
		      if (elem.addEventListener != null) {
		        return elem.addEventListener(event, fn, false);
		      } else if (elem.attachEvent != null) {
		        return elem.attachEvent("on" + event, fn);
		      } else {
		        return elem[event] = fn;
		      }
		    };

		    Util.prototype.removeEvent = function(elem, event, fn) {
		      if (elem.removeEventListener != null) {
		        return elem.removeEventListener(event, fn, false);
		      } else if (elem.detachEvent != null) {
		        return elem.detachEvent("on" + event, fn);
		      } else {
		        return delete elem[event];
		      }
		    };

		    Util.prototype.innerHeight = function() {
		      if ('innerHeight' in window) {
		        return window.innerHeight;
		      } else {
		        return document.documentElement.clientHeight;
		      }
		    };

		    return Util;

		  })();

		  WeakMap = this.WeakMap || this.MozWeakMap || (WeakMap = (function() {
		    function WeakMap() {
		      this.keys = [];
		      this.values = [];
		    }

		    WeakMap.prototype.get = function(key) {
		      var i, item, j, len, ref;
		      ref = this.keys;
		      for (i = j = 0, len = ref.length; j < len; i = ++j) {
		        item = ref[i];
		        if (item === key) {
		          return this.values[i];
		        }
		      }
		    };

		    WeakMap.prototype.set = function(key, value) {
		      var i, item, j, len, ref;
		      ref = this.keys;
		      for (i = j = 0, len = ref.length; j < len; i = ++j) {
		        item = ref[i];
		        if (item === key) {
		          this.values[i] = value;
		          return;
		        }
		      }
		      this.keys.push(key);
		      return this.values.push(value);
		    };

		    return WeakMap;

		  })());

		  MutationObserver = this.MutationObserver || this.WebkitMutationObserver || this.MozMutationObserver || (MutationObserver = (function() {
		    function MutationObserver() {
		      if (typeof console !== "undefined" && console !== null) {
		        console.warn('MutationObserver is not supported by your browser.');
		      }
		      if (typeof console !== "undefined" && console !== null) {
		        console.warn('WOW.js cannot detect dom mutations, please call .sync() after loading new content.');
		      }
		    }

		    MutationObserver.notSupported = true;

		    MutationObserver.prototype.observe = function() {};

		    return MutationObserver;

		  })());

		  getComputedStyle = this.getComputedStyle || function(el, pseudo) {
		    this.getPropertyValue = function(prop) {
		      var ref;
		      if (prop === 'float') {
		        prop = 'styleFloat';
		      }
		      if (getComputedStyleRX.test(prop)) {
		        prop.replace(getComputedStyleRX, function(_, _char) {
		          return _char.toUpperCase();
		        });
		      }
		      return ((ref = el.currentStyle) != null ? ref[prop] : void 0) || null;
		    };
		    return this;
		  };

		  getComputedStyleRX = /(\-([a-z]){1})/g;

		  this.WOW = (function() {
		    WOW.prototype.defaults = {
		      boxClass: 'wow',
		      animateClass: 'animated',
		      offset: 0,
		      mobile: true,
		      live: true,
		      callback: null,
		      scrollContainer: null
		    };

		    function WOW(options) {
		      if (options == null) {
		        options = {};
		      }
		      this.scrollCallback = bind(this.scrollCallback, this);
		      this.scrollHandler = bind(this.scrollHandler, this);
		      this.resetAnimation = bind(this.resetAnimation, this);
		      this.start = bind(this.start, this);
		      this.scrolled = true;
		      this.config = this.util().extend(options, this.defaults);
		      if (options.scrollContainer != null) {
		        this.config.scrollContainer = document.querySelector(options.scrollContainer);
		      }
		      this.animationNameCache = new WeakMap();
		      this.wowEvent = this.util().createEvent(this.config.boxClass);
		    }

		    WOW.prototype.init = function() {
		      var ref;
		      this.element = window.document.documentElement;
		      if ((ref = document.readyState) === "interactive" || ref === "complete") {
		        this.start();
		      } else {
		        this.util().addEvent(document, 'DOMContentLoaded', this.start);
		      }
		      return this.finished = [];
		    };

		    WOW.prototype.start = function() {
		      var box, j, len, ref;
		      this.stopped = false;
		      this.boxes = (function() {
		        var j, len, ref, results;
		        ref = this.element.querySelectorAll("." + this.config.boxClass);
		        results = [];
		        for (j = 0, len = ref.length; j < len; j++) {
		          box = ref[j];
		          results.push(box);
		        }
		        return results;
		      }).call(this);
		      this.all = (function() {
		        var j, len, ref, results;
		        ref = this.boxes;
		        results = [];
		        for (j = 0, len = ref.length; j < len; j++) {
		          box = ref[j];
		          results.push(box);
		        }
		        return results;
		      }).call(this);
		      if (this.boxes.length) {
		        if (this.disabled()) {
		          this.resetStyle();
		        } else {
		          ref = this.boxes;
		          for (j = 0, len = ref.length; j < len; j++) {
		            box = ref[j];
		            this.applyStyle(box, true);
		          }
		        }
		      }
		      if (!this.disabled()) {
		        this.util().addEvent(this.config.scrollContainer || window, 'scroll', this.scrollHandler);
		        this.util().addEvent(window, 'resize', this.scrollHandler);
		        this.interval = setInterval(this.scrollCallback, 50);
		      }
		      if (this.config.live) {
		        return new MutationObserver((function(_this) {
		          return function(records) {
		            var k, len1, node, record, results;
		            results = [];
		            for (k = 0, len1 = records.length; k < len1; k++) {
		              record = records[k];
		              results.push((function() {
		                var l, len2, ref1, results1;
		                ref1 = record.addedNodes || [];
		                results1 = [];
		                for (l = 0, len2 = ref1.length; l < len2; l++) {
		                  node = ref1[l];
		                  results1.push(this.doSync(node));
		                }
		                return results1;
		              }).call(_this));
		            }
		            return results;
		          };
		        })(this)).observe(document.body, {
		          childList: true,
		          subtree: true
		        });
		      }
		    };

		    WOW.prototype.stop = function() {
		      this.stopped = true;
		      this.util().removeEvent(this.config.scrollContainer || window, 'scroll', this.scrollHandler);
		      this.util().removeEvent(window, 'resize', this.scrollHandler);
		      if (this.interval != null) {
		        return clearInterval(this.interval);
		      }
		    };

		    WOW.prototype.sync = function(element) {
		      if (MutationObserver.notSupported) {
		        return this.doSync(this.element);
		      }
		    };

		    WOW.prototype.doSync = function(element) {
		      var box, j, len, ref, results;
		      if (element == null) {
		        element = this.element;
		      }
		      if (element.nodeType !== 1) {
		        return;
		      }
		      element = element.parentNode || element;
		      ref = element.querySelectorAll("." + this.config.boxClass);
		      results = [];
		      for (j = 0, len = ref.length; j < len; j++) {
		        box = ref[j];
		        if (indexOf.call(this.all, box) < 0) {
		          this.boxes.push(box);
		          this.all.push(box);
		          if (this.stopped || this.disabled()) {
		            this.resetStyle();
		          } else {
		            this.applyStyle(box, true);
		          }
		          results.push(this.scrolled = true);
		        } else {
		          results.push(void 0);
		        }
		      }
		      return results;
		    };

		    WOW.prototype.show = function(box) {
		      this.applyStyle(box);
		      box.className = box.className + " " + this.config.animateClass;
		      if (this.config.callback != null) {
		        this.config.callback(box);
		      }
		      this.util().emitEvent(box, this.wowEvent);
		      this.util().addEvent(box, 'animationend', this.resetAnimation);
		      this.util().addEvent(box, 'oanimationend', this.resetAnimation);
		      this.util().addEvent(box, 'webkitAnimationEnd', this.resetAnimation);
		      this.util().addEvent(box, 'MSAnimationEnd', this.resetAnimation);
		      return box;
		    };

		    WOW.prototype.applyStyle = function(box, hidden) {
		      var delay, duration, iteration;
		      duration = box.getAttribute('data-wow-duration');
		      delay = box.getAttribute('data-wow-delay');
		      iteration = box.getAttribute('data-wow-iteration');
		      return this.animate((function(_this) {
		        return function() {
		          return _this.customStyle(box, hidden, duration, delay, iteration);
		        };
		      })(this));
		    };

		    WOW.prototype.animate = (function() {
		      if ('requestAnimationFrame' in window) {
		        return function(callback) {
		          return window.requestAnimationFrame(callback);
		        };
		      } else {
		        return function(callback) {
		          return callback();
		        };
		      }
		    })();

		    WOW.prototype.resetStyle = function() {
		      var box, j, len, ref, results;
		      ref = this.boxes;
		      results = [];
		      for (j = 0, len = ref.length; j < len; j++) {
		        box = ref[j];
		        results.push(box.style.visibility = 'visible');
		      }
		      return results;
		    };

		    WOW.prototype.resetAnimation = function(event) {
		      var target;
		      if (event.type.toLowerCase().indexOf('animationend') >= 0) {
		        target = event.target || event.srcElement;
		        return target.className = target.className.replace(this.config.animateClass, '').trim();
		      }
		    };

		    WOW.prototype.customStyle = function(box, hidden, duration, delay, iteration) {
		      if (hidden) {
		        this.cacheAnimationName(box);
		      }
		      box.style.visibility = hidden ? 'hidden' : 'visible';
		      if (duration) {
		        this.vendorSet(box.style, {
		          animationDuration: duration
		        });
		      }
		      if (delay) {
		        this.vendorSet(box.style, {
		          animationDelay: delay
		        });
		      }
		      if (iteration) {
		        this.vendorSet(box.style, {
		          animationIterationCount: iteration
		        });
		      }
		      this.vendorSet(box.style, {
		        animationName: hidden ? 'none' : this.cachedAnimationName(box)
		      });
		      return box;
		    };

		    WOW.prototype.vendors = ["moz", "webkit"];

		    WOW.prototype.vendorSet = function(elem, properties) {
		      var name, results, value, vendor;
		      results = [];
		      for (name in properties) {
		        value = properties[name];
		        elem["" + name] = value;
		        results.push((function() {
		          var j, len, ref, results1;
		          ref = this.vendors;
		          results1 = [];
		          for (j = 0, len = ref.length; j < len; j++) {
		            vendor = ref[j];
		            results1.push(elem["" + vendor + (name.charAt(0).toUpperCase()) + (name.substr(1))] = value);
		          }
		          return results1;
		        }).call(this));
		      }
		      return results;
		    };

		    WOW.prototype.vendorCSS = function(elem, property) {
		      var j, len, ref, result, style, vendor;
		      style = getComputedStyle(elem);
		      result = style.getPropertyCSSValue(property);
		      ref = this.vendors;
		      for (j = 0, len = ref.length; j < len; j++) {
		        vendor = ref[j];
		        result = result || style.getPropertyCSSValue("-" + vendor + "-" + property);
		      }
		      return result;
		    };

		    WOW.prototype.animationName = function(box) {
		      var animationName, error;
		      try {
		        animationName = this.vendorCSS(box, 'animation-name').cssText;
		      } catch (error) {
		        animationName = getComputedStyle(box).getPropertyValue('animation-name');
		      }
		      if (animationName === 'none') {
		        return '';
		      } else {
		        return animationName;
		      }
		    };

		    WOW.prototype.cacheAnimationName = function(box) {
		      return this.animationNameCache.set(box, this.animationName(box));
		    };

		    WOW.prototype.cachedAnimationName = function(box) {
		      return this.animationNameCache.get(box);
		    };

		    WOW.prototype.scrollHandler = function() {
		      return this.scrolled = true;
		    };

		    WOW.prototype.scrollCallback = function() {
		      var box;
		      if (this.scrolled) {
		        this.scrolled = false;
		        this.boxes = (function() {
		          var j, len, ref, results;
		          ref = this.boxes;
		          results = [];
		          for (j = 0, len = ref.length; j < len; j++) {
		            box = ref[j];
		            if (!(box)) {
		              continue;
		            }
		            if (this.isVisible(box)) {
		              this.show(box);
		              continue;
		            }
		            results.push(box);
		          }
		          return results;
		        }).call(this);
		        if (!(this.boxes.length || this.config.live)) {
		          return this.stop();
		        }
		      }
		    };

		    WOW.prototype.offsetTop = function(element) {
		      var top;
		      while (element.offsetTop === void 0) {
		        element = element.parentNode;
		      }
		      top = element.offsetTop;
		      while (element = element.offsetParent) {
		        top += element.offsetTop;
		      }
		      return top;
		    };

		    WOW.prototype.isVisible = function(box) {
		      var bottom, offset, top, viewBottom, viewTop;
		      offset = box.getAttribute('data-wow-offset') || this.config.offset;
		      viewTop = (this.config.scrollContainer && this.config.scrollContainer.scrollTop) || window.pageYOffset;
		      viewBottom = viewTop + Math.min(this.element.clientHeight, this.util().innerHeight()) - offset;
		      top = this.offsetTop(box);
		      bottom = top + box.clientHeight;
		      return top <= viewBottom && bottom >= viewTop;
		    };

		    WOW.prototype.util = function() {
		      return this._util != null ? this._util : this._util = new Util();
		    };

		    WOW.prototype.disabled = function() {
		      return !this.config.mobile && this.util().isMobile(navigator.userAgent);
		    };

		    return WOW;

		  })();

		}).call(this);




/**
 *  wos.js end
 */

/**
 * common.js start
 * 
 */
	$(window).scroll(function() {
	    if ($(this).scrollTop() > 50 ) {
	        $('.scrolltop:hidden').stop(true, true).fadeIn();
	    } else {
	        $('.scrolltop').stop(true, true).fadeOut();
	    }
	});
	$(function(){$(".scroll").click(function(){$("html,body").animate({scrollTop:$(".thetop").offset().top},"1000");return false})});
	
	/**
	 * common.js end
	 * 
	 */

/**
 * home.js start
 */

	$(document).ready(function(){
	$("#campaignsignup").on("tap click",function(e){
		
		e.preventDefault();
		$.get("/products/campaignsignup.do",
		{
			mobile: $("#signupmob").val(),
			location: $("#location").val()
		},		
		function(result){
			$('#exampleModalCenter').modal('hide');
			
		});
	});
	});


	function formValidation(){
//		console.log("mobile validation;");
		var digitregexp = /^[6-9][0-9]{9}$/;
		var mobile = document.getElementById("signupmob").value;
		
		if(mobile == "" || !digitregexp.test(mobile)){
			document.getElementById("signupmob").style.borderLeft = '2px solid red';
		}else{
			document.getElementById("signupmob").style.borderLeft = '2px solid green';
			document.getElementById("campaignsignup").disabled = false;
		}
		
	}


	/*$(document).ready(function(){
		$("#campaignsignup").click(function(){
			var url = "/products/campaignsignup.do";
			var arr = {mobile: $("#signupmob").val(), location: $("#location").val()}
			$.ajax({
				  type: "POST",
				  url: url,
				  data: arr,
				  dataType: 'json'
				});
		});
		});*/
	
	
	/**
	 * home.js end
	 */
