<%--
  Created by IntelliJ IDEA.
  User: Vladok
  Date: 26-Apr-18
  Time: 1:02 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Blink</title>

    <link rel="stylesheet" href="../../resources/css/foundation.css">
    <link rel="stylesheet" href="../../resources/css/booknow.css">
    <link rel="stylesheet" href="../../resources/css/contacts.css">
    <link rel="stylesheet" href="../../resources/css/footer.css">
    <link rel="stylesheet" type="text/css" href="../../resources/css/jquery.datetimepicker.min.css"/>
    <link rel="stylesheet" href="../../resources/css/logo&book.css">
    <link rel="stylesheet" href="../../resources/css/menu.css">
    <link rel="stylesheet" href="../../resources/css/services.css">
    <link rel="stylesheet" href="../../resources/css/style.css">
    <link rel="stylesheet" href="../../resources/css/gallery.css">
    <link rel="stylesheet" href="../../resources/foundation-icons/foundation-icons.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- jQuery JavaScript -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.4.min.js"></script>

</head>
<body>

<!-- top-bar-->
<div class="top-bar-container" data-sticky-container>
    <div class="sticky sticky-topbar" data-sticky data-options="anchor: page; marginTop: 0; stickyOn: small;">
        <div class="top-bar">
            <div class="top-bar-left">
                <ul class="menu menu-hover-lines">
                    <li class="hide-for-small-only" data-open="mobile-ios-modal-1"><a>Book Now</a>
                    </li>
                    <li class="show-for-small-only" data-open="mobile-ios-modal-1"><a><i class="fi-crown"></i></a>
                    </li>
                </ul>
            </div>
            <div class="top-bar-right">
                <ul class="menu menu-hover-lines">
                    <li class="hide-for-small-only about-click"><a>About Us</a></li>
                    <li class="show-for-small-only"><a><i class="fi-crown"></i></a></li>
                    <li class="hide-for-small-only services-click"><a>Services</a></li>
                    <li class="show-for-small-only"><a><i class="fi-torsos-all-female"></i></a></li>
                    <li class="hide-for-small-only gallery-click"><a>Gallery</a></li>
                    <li class="show-for-small-only"><a><i class="fi-photo"></i></a></li>
                    <li class="hide-for-small-only contacts-click"><a>Contacts</a></li>
                    <li class="show-for-small-only"><a><i class="fi-address-book"></i></a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<!-- logo block -->

<div class="logo-section">

</div>

<!-- about us -->

<div class="about-us" style="box-sizing: content-box">
    <div class="about-us-text">
        <h5><b>Who we are and what we do?</b></h5>
        <p>Hey, we are the beauty space for girls, where they can do: manicure, pedicure, make-up and brows.</p>
        <p>We love our job and you can join us clicking on book now and visiting us on the date you choose.</p>
    </div>
</div>

<!-- services -->

<div class="services">
    <div class="orbit clean-hero-slider" role="region" aria-label="Services" data-orbit>
        <div class="orbit-wrapper">
            <div class="orbit-controls">
                <button class="orbit-previous"><span class="show-for-sr">Previous Slide</span><i
                        class="fi-arrow-left"></i></button>
                <button class="orbit-next"><span class="show-for-sr">Next Slide</span><i class="fi-arrow-right"></i>
                </button>
            </div>
            <ul class="orbit-container">
                <li class="orbit-slide">
                    <figure class="orbit-figure">
                        <img class="orbit-image" src="../../resources/css/image/man.jpg" alt="image alt text">
                        <figcaption class="orbit-caption">
                            <h3>Manicure</h3>
                            <p>Manicure, designs - what else can girl wish?</p>
                            <a class="button book-click" data-open="mobile-ios-modal-1">Book now</a>
                        </figcaption>
                    </figure>
                </li>
                <li class="orbit-slide">
                    <figure class="orbit-figure">
                        <img class="orbit-image" src="../../resources/css/image/make.jpg" alt="image alt text">
                        <figcaption class="orbit-caption">
                            <h3>Make-up</h3>
                            <p>Express make up, cocktail, evening or artistic - you wish - we do</p>
                            <a class="button book-click" data-open="mobile-ios-modal-1">Book now</a>
                        </figcaption>
                    </figure>
                </li>
                <li class="orbit-slide">
                    <figure class="orbit-figure">
                        <img class="orbit-image" src="../../resources/css/image/brow.jpg" alt="image alt text">
                        <figcaption class="orbit-caption">
                            <h3>Brows</h3>
                            <p>Perfect correction and dyeing for your brows</p>
                            <a class="button book-click" data-open="mobile-ios-modal-1">Book now</a>
                        </figcaption>
                    </figure>
                </li>

            </ul>
        </div>
    </div>
</div>

<!-- book now-->
<div class="reveal mobile-ios-modal" id="mobile-ios-modal-1" data-reveal>
    <div class="mobile-ios-modal-inner">
        <div class="login-box">
            <div class="login-box-form-section">
                <h4 class="login-box-title">Book now</h4>
                <div class="services-menu">
                    <div class="services-menu-inside">
                        <button class="button button-rounded-hover nails-service-click" id="nails-service">Nails
                        </button>
                        <button class="button button-rounded-hover brows-service-click" id="brows-service">Brows
                        </button>
                        <button class="button button-rounded-hover makeup-service-click" id="makeup-service">Make Up
                        </button>
                    </div>
                </div>
                <div class="menu menu-email-check">
                    <input class="login-box-input" type="email" id="client-email" placeholder="E-mail"/>
                    <input class="login-box-submit-button" type="submit" id="email_submit" value="Get Code"/>
                </div>
                <div class="menu menu-code-check">
                    <input class="login-box-input " type="text" id="id-client-code" placeholder="Code" disabled/>
                    <input class="login-box-submit-button" type="submit" id="code_submit" value="Submit" disabled/>
                </div>
                <div class="menu menu-date-check date-input-box">
                    <span class="input-with-but">
                        <input class="login-box-input date-input" type="text" id="filter-date"
                               placeholder="Click to choose date" disabled/>
                    <button id="confirm-date" disabled><i class="fi-check"></i></button>
                        </span>
                    <input class="login-box-input date-input" type="text" id="filter-time"
                           placeholder="Click to choose time" disabled/>
                </div>
                <div class="menu menu-data-check">
                    <input class="login-box-input noButton" type="text" id="client-name"
                           placeholder="Write your name" disabled/>
                </div>
                <div class="menu menu-data-check">
                    <input class="login-box-input" type="text" id="client-phone"
                           placeholder="Write your phone number" disabled/>
                    <input class="login-box-submit-button" type="submit" id="submit-service"
                           value="Submit" disabled/>
                </div>
                <div class="edit-question">
                    <a data-open="mobile-ios-modal-2">Want to edit your booking? Click here!</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- edit booking -->
<div class="reveal mobile-ios-modal" id="mobile-ios-modal-2" data-reveal>
    <div class="mobile-ios-modal-inner">
        <div class="login-box">
            <div class="login-box-form-section">
                <h4 class="login-box-title">Edit bookings</h4>
                <div class="menu menu-email-check">
                    <input class="login-box-input" type="email" id="client-email-edit" placeholder="E-mail"/>
                    <input class="login-box-submit-button-edit" type="submit" id="email_submit-edit"
                           value="Get Bookings"/>
                </div>
                <div class="booking-list" id="booking-list">

                </div>
                <div class="edit-question">
                    <a data-open="mobile-ios-modal-1">Don`t have any bookings yet? Book now!</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- gallery -->
<div class="gallery">
    <div class="gallery-inside">
        <img class="gallery-photo" src="../../resources/css/image/brow_gal-01.png">
        <img class="gallery-photo" src="../../resources/css/image/man_gall-01.png">
        <img class="gallery-photo" src="../../resources/css/image/make_gal-01.png">
    </div>
</div>


<!-- contacts -->
<div class="contacts">
    <div class="google-maps" id="googleMap"></div>
    <div class="contact-info">
        <h3>CONTACTS</h3>
        <div class="contacts-text">
            <div class="contact-address contact-inside">
                <span class="contact-text-big">Address</span>
                <span class="contacts-text-small">Bulvarno-Kudriavska Street, 36</span>
            </div>
            <div class="contact-phone contact-inside">
                <span class="contact-text-big">Phone</span>
                <span class="contacts-text-small">+380 63 101 33 08</span>
            </div>
        </div>
    </div>
</div>

<!-- footer -->
<div class="footer">
    <div class="footer-inside">
        <div class="rounded-social-buttons">
            <a class="social-button facebook" href="https://www.facebook.com/blinkbeautyspace"></a>
            <a class="social-button instagram" href="https://www.instagram.com/blinkbeautyspace"></a>
        </div>
        <div class="footer-text">Copyright 2018 - Blink Beauty Space</div>
    </div>
</div>

<!-- include before </body> tag -->

<script type="text/javascript"
        src="http://maps.googleapis.com/maps/api/js?key=AIzaSyA51K1_zpFZmD_yD_JpZfqVzXcNds-CsSE"></script>

<script src="../../resources/js/vendor/what-input.js"></script>
<script src="../../resources/js/vendor/foundation.js"></script>
<script>
    $(document).foundation();
</script>

<script type="text/javascript" src="../../resources/js/app.js"></script>

<script src="../../resources/js/datatimepicker/jquery.datetimepicker.full.js"></script>

</body>
</html>