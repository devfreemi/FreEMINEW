<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="toggle_btns">
      <a href="" class="btn btn-warning"><img src="<c:url value="/resources/images/ic_1.png"/>" alt="Investment" /></a>
      <a href="" class="btn btn-warning"><img src="<c:url value="/resources/images/ic_2.png"/>" alt="Group Insurence" /></a>
      <a href="" class="btn btn-warning"><img src="<c:url value="/resources/images/ic_3.png"/>" alt="Loan" /></a>
    </div>
    <!-- <header class="dark_blue wow fadeInDown" data-wow-duration="2s"> -->

    <section class="top_menu">  
      <div class="container" style="display: inline-block;">
          <div class="row">
              <nav class="navbar navbar-expand-lg navbar-light">
                <a class="navbar-brand" href="#"><img src="<c:url value="/resources/images/freemi.png"/>" alt="Smart Investment" /></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"><i class="fa fa-window-minimize" aria-hidden="true"></i></span>
                  <span class="navbar-toggler-icon"><i class="fa fa-window-minimize" aria-hidden="true"></i></span>
                  <span class="navbar-toggler-icon"><i class="fa fa-window-minimize" aria-hidden="true"></i></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                  <ul class="navbar-nav ml-auto mr-auto">
                    <li class="nav-item active">
                      <a class="nav-link" href="/freemi/freemi">FreEMI <span class="sr-only">(current)</span></a>                    
                      </li>
                      <li class="nav-item">
                      <a class="nav-link" href="/freemi/fsecure">F-Secure <span class="sr-only"></span></a>                    
                      </li>
                    <li class="nav-item dropdown">
          
                         <a class="nav-link" href="/freemi/registry" >Registry <span class="sr-only"></span></a>  
                      <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="#">Family</a>
                        <a class="dropdown-item" href="#">Friends</a>
                        <a class="dropdown-item" href="#">Official</a>                      </div>
                    </li>
                     <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Calculator                      </a>
                      <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="#">Tax Calculator</a>
                        <a class="dropdown-item" href="#">MF Return Calculator</a>
                        <a class="dropdown-item" href="#">EMI Calculator</a>                      </div>
                    </li>
                    <!--<li class="nav-item">
                      <a class="nav-link" href="#">About</a>
                    </li>-->
                    <li class="nav-item">
                      <a class="nav-link" href="/freemi/blogs">Blogs</a>                    </li>
                   <li class="nav-item">
                      <a class="nav-link" href="/freemi/contact">Contact Us</a>                    </li>
                  </ul>
                </div>
                <ul class="login_top ml-auto">
                  <li><a href="/freemi/register">Sign Up</a></li>
                  <li><a href="/freemi/login">Log In</a></li>
                  
                </ul>
               
              </nav>
               
          </div>
          
      </div>   
      <div style="display: inline-block;" id="google_translate_element"></div>     
      <div class="loader"></div>
    </section>
   