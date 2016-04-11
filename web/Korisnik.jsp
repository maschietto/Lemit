<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Korisnik"%>
<%@page import="model.Katalog"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Korisnik</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/main.css">
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/jssor.slider.mini.js"></script>
        <script src="assets/js/main.js"></script>
    </head>
    <body>
        <%@include file="jspf/header_admin.jspf"%>     
        <%@include file="jspf/poruka.jspf"%>
        <%@include file="jspf/footer.jspf"%>
                
        
          <%  
               Korisnik logged = (Korisnik) session.getAttribute("korisnik");
              // List<Katalog> katalog = (List<Katalog>) request.getAttribute("katalog");
              
             %>
 
        
        <br>
        <br>
        
        <!-- Use a container to wrap the slider, the purpose is to enable slider to always fit width of the wrapper while window resize -->
        <div class="container">
            <!-- Jssor Slider Begin -->
            <!-- To move inline styles to css file/block, please specify a class name for each element. --> 
            <!-- ================================================== -->
            <div id="slider1_container" style="display: none; position: relative; margin: 0 auto; width: 1140px; height: 442px; overflow: hidden;">

                <!-- Loading Screen -->
                <div u="loading" style="position: absolute; top: 0px; left: 0px;">
                    <div style="filter: alpha(opacity=70); opacity:0.7; position: absolute; display: block;

                background-color: #000; top: 0px; left: 0px;width: 100%; height:100%;">
                    </div>
                    <div style="position: absolute; display: block; background: url(assets/img/loading.gif) no-repeat center center;

                top: 0px; left: 0px;width: 100%;height:100%;">
                    </div>
                </div>

                <!-- Slides Container -->
                <div u="slides" style="cursor: move; position: absolute; left: 0px; top: 0px; width: 1140px; height: 442px;
            overflow: hidden;">
                    <div>
                        <img u="image" src2="assets/img/01.jpg" />
                    </div>
                    <div>
                        <img u="image" src2="assets/img/02.jpg" />
                    </div>
                    <div>
                        <img u="image" src2="assets/img/03.jpg" />
                    </div>
                    <div>
                        <img u="image" src2="assets/img/04.jpg" />
                    </div>
                </div>

                <!--#region Bullet Navigator Skin Begin -->
                <!-- Help: http://www.jssor.com/development/slider-with-bullet-navigator-jquery.html -->
                <style>
                    /* jssor slider bullet navigator skin 05 css */
                    /*
                    .jssorb05 div           (normal)
                    .jssorb05 div:hover     (normal mouseover)
                    .jssorb05 .av           (active)
                    .jssorb05 .av:hover     (active mouseover)
                    .jssorb05 .dn           (mousedown)
                    */
                    .jssorb05 {
                        position: absolute;
                    }
                    .jssorb05 div, .jssorb05 div:hover, .jssorb05 .av {
                        position: absolute;
                        /* size of bullet elment */
                        width: 16px;
                        height: 16px;
                        background: url(assets/img/b05.png) no-repeat;
                        overflow: hidden;
                        cursor: pointer;
                    }
                    .jssorb05 div { background-position: -7px -7px; }
                    .jssorb05 div:hover, .jssorb05 .av:hover { background-position: -37px -7px; }
                    .jssorb05 .av { background-position: -67px -7px; }
                    .jssorb05 .dn, .jssorb05 .dn:hover { background-position: -97px -7px; }
                </style>
                <!-- bullet navigator container -->
                <div u="navigator" class="jssorb05" style="bottom: 16px; right: 6px;">
                    <!-- bullet navigator item prototype -->
                    <div u="prototype"></div>
                </div>
                <!--#endregion Bullet Navigator Skin End -->

                <!--#region Arrow Navigator Skin Begin -->
                <!-- Help: http://www.jssor.com/development/slider-with-arrow-navigator-jquery.html -->
                <style>
                    /* jssor slider arrow navigator skin 11 css */
                    /*
                    .jssora11l                  (normal)
                    .jssora11r                  (normal)
                    .jssora11l:hover            (normal mouseover)
                    .jssora11r:hover            (normal mouseover)
                    .jssora11l.jssora11ldn      (mousedown)
                    .jssora11r.jssora11rdn      (mousedown)
                    */
                    .jssora11l, .jssora11r {
                        display: block;
                        position: absolute;
                        /* size of arrow element */
                        width: 37px;
                        height: 37px;
                        cursor: pointer;
                        background: url(assets/img/a11.png) no-repeat;
                        overflow: hidden;
                    }
                    .jssora11l { background-position: -11px -41px; }
                    .jssora11r { background-position: -71px -41px; }
                    .jssora11l:hover { background-position: -131px -41px; }
                    .jssora11r:hover { background-position: -191px -41px; }
                    .jssora11l.jssora11ldn { background-position: -251px -41px; }
                    .jssora11r.jssora11rdn { background-position: -311px -41px; }
                </style>
                <!-- Arrow Left -->
                <span u="arrowleft" class="jssora11l" style="top: 123px; left: 8px;">
                </span>
                <!-- Arrow Right -->
                <span u="arrowright" class="jssora11r" style="top: 123px; right: 8px;">
                </span>
                <!--#endregion Arrow Navigator Skin End -->
            </div>
            <!-- Jssor Slider End -->
        </div>
        
        
        
        <div class="alert alert-warning" style="text-align: center">
         <a href="#" class="close" data-dismiss="alert"></a>
       <h3> Postovani Korisnice <%=logged.getNazivKorisnika()%> dobrodosli na sistem za uredjivanje nabavnog procesa firme LeMIT D.O.O.</h3></div>
       
    
    </body>
</html>
