 
 <footer class="footer wow fadeInDownBig" data-wow-duration="4s">
      <section class="dark_blue">
        <div class="container">
            <form>
            <div class="row padd_60 top_foot">
                <div class="col-md-12 text-center">
                <h2>SEND US A MESSAGE</h2>
                <p>Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Vivamus suscipit tortor eget felis porttitor volutpat. Curabitur aliquet quam id dui posuere.</p>
                </div>
                <div class="col-md-4">
                  <div class="form-group">
                    <label for="exampleFormControlInput1" class="sr-only">Email address</label>
                    <input type="Name" class="form-control" id="exampleFormControlInput1" placeholder="Your Name">
                  </div>
                </div>
                <div class="col-md-4">
                  <div class="form-group">
                    <label for="exampleFormControlInput1" class="sr-only">Email address</label>
                    <input type="email" class="form-control" id="exampleFormControlInput1" placeholder="Your Mail">
                  </div>
                </div>
                <div class="col-md-4">
                  <div class="form-group">
                    <label for="exampleFormControlInput1" class="sr-only">Email address</label>
                    <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Subject">
                  </div>
                </div>
                <div class="col-md-12">
                  <div class="form-group">
                    <label for="exampleFormControlTextarea1" class="sr-only">Example textarea</label>
                    <textarea class="form-control" id="exampleFormControlTextarea1" rows="1" placeholder="Enter Message Here..."></textarea>
                  </div>
                </div>  
                <div class="col-md-12 ml-auto text-center">
                  <input type="submit" class="btn btn-warning" value="SEND MESSAGE">
                </div>
            </div>
          </form>   
        </div>
      </section>
      <section class="dark_bg">
        <div class="container">
            <div class="row padd_25 btm_foot">
                <div class="col-md-12 text-center">
                  <p>© Copyright 2018. All Rights Reserved.</p>
                </div>
                <div class="col-md-12 text-center">
                  <ul>
                    <li><a href=""><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                    <li><a href=""><i class="fa fa-dribbble" aria-hidden="true"></i></a></li>
                    <li><a href=""><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                    <li><a href=""><i class="fa fa-google-plus" aria-hidden="true"></i></a></li> 
                  </ul>
                </div>
            </div>

        </div>
      </section>
    </footer>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
   <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="${contextPath}/resources/js/freemi.main.js"></script>
    <script src="${contextPath}/resources/owlcarousel/owl.carousel.js"></script>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script>
      wow = new WOW(
      {
        animateClass: 'animated',
        offset:       100,
        callback:     function(box) {
          console.log("WOW: animating <" + box.tagName.toLowerCase() + ">")
          }
        }
      );
      wow.init();
      document.getElementById('moar').onclick = function() {
        var section = document.createElement('section');
        section.className = 'section--purple wow fadeInDown';
        this.parentNode.insertBefore(section, this);
      };

    
    </script>
    <script>
      var owl = $('.client_sec_full .owl-carousel');
      owl.owlCarousel({
      loop:true,
      autoplay:true,
      autoplayTimeout:3000,
      autoplayHoverPause:true,
      margin:10,
      nav:true,
      responsive:{
        0:{
          items:1
        },
        320:{
          items:1
        },
        480:{
          items:2
        },
        600:{
          items:3
        },
        767:{
          items:3
        },
        1000:{
          items:6
        }
      }
        });
    </script>
    <script>
      var owl = $('.testimonials_sec_full .owl-carousel');
      owl.owlCarousel({
      loop:true,
      autoplay:true,
      autoplayTimeout:3000,
      autoplayHoverPause:true,
      margin:10,
      nav:true,
      responsive:{
        0:{
          items:1
        },
        600:{
          items:1
        },
        767:{
          items:1
        },
        1000:{
          items:1
        }
      }
        });
    </script>
  </body>
</html>