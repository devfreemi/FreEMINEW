 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <section class="dark_blue text-center bann">
      <div id="carousel-2" class="carousel slide carousel-fade" data-ride="carousel" data-interval="6000">
        <!--<ol class="carousel-indicators">
            <li data-target="#carousel-2" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-2" data-slide-to="1"></li>
            <li data-target="#carousel-2" data-slide-to="2"></li>
        </ol>-->
        <div class="carousel-inner" role="listbox">
            <div class="carousel-item active">
                 <img src="<c:url value="/resources/images/bann_1.jpg"/>" alt="responsive image" class="d-block img-fluid" />
                    <div class="carousel-caption">
                        <div>
                            <h1>E-KYC</h1>
                              <h2>Now, Investments formalities are Just click away with Aadhaar Number                        
                              </h2>
                            <p>
                              <a href="/freemi/register" class="btn btn-warning">Register Now</a>       
                              </p>                     
                            
                        </div>
                    </div>
            </div>
            <!-- /.carousel-item -->          
           

        </div>
        <!-- /.carousel-inner -->
        <a class="carousel-control-prev" href="#carousel-2" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carousel-2" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
      </div>
      <!-- /.carousel -->
      <button type="button" class="btn btn-warning r_btn" id="click"><span><img src="<c:url value="/resources/images/arw_dwn.png" />" alt="" /></span></button>
      </section>  
