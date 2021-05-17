<base href="/products">
<meta name="viewport" content="width=device-width, initial-scale=1.0">  
<link rel="dns-prefetch" href="https://www.freemi.in">
<link rel="dns-prefetch" href="https://resources.freemi.in">
<meta name="theme-color" content="#38d2ed">
<meta name="Copyright" content="Copyright 2018 @ freemi.in" />
<meta name="author" content="https://www.freemi.in" />
<link rel="icon" type="image/x-icon" href="/products/resources/images/favicon.ico">

<!-- Font Awesome -->
<link
  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
  rel="stylesheet"
/>
<!-- Google Fonts -->
<!-- MDB -->
<link
  href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.3.0/mdb.min.css"
  rel="stylesheet"
/>
<!-- McAfee Secure logo -->
<!-- <script type="text/javascript" src="https://cdn.ywxi.net/js/1.js" defer="defer"></script> -->

<jsp:include page="./GoogleHeadTag.jsp"></jsp:include>
<link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">

<!-- MDB -->
<script
  type="text/javascript"
  src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.3.0/mdb.min.js"
></script>

<style>
@font-face {
  font-family: 'sans-serif';
  font-display: swap;
}

body{
	font-family: 'Roboto', sans-serif;
	font-weight: 400;
}
</style>
<script>
function clearSessionData() {
    window.sessionStorage.clear();
    $.post( "/products/closewindow", function( data ) {
    	  console.log(data);
    	});
}
document.addEventListener("DOMContentLoaded", function() {
    let e = [].slice.call(document.querySelectorAll("img.lazy"));
    let n = !1;
    const t = function() {
        !1 === n && (n = !0, setTimeout(function() {
            e.forEach(function(n) {
                n.getBoundingClientRect().top <= window.innerHeight && n.getBoundingClientRect().bottom >= 0 && "none" !== getComputedStyle(n).display && (n.src = n.dataset.src, n.classList.remove("lazy"), 0 === (e = e.filter(function(e) {
                    return e !== n
                })).length && (document.removeEventListener("click", t), document.removeEventListener("scroll", t), window.removeEventListener("resize", t), window.removeEventListener("orientationchange", t)))
            }), n = !1
        }, 200))
    };
    document.addEventListener("click", t), document.addEventListener("scroll", t), window.addEventListener("resize", t), window.addEventListener("orientationchange", t)
});
</script>

